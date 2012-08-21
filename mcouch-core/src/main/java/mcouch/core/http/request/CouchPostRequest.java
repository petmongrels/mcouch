package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.http.StandardHttpResponse;
import mcouch.core.http.request.body.BulkRequest;
import mcouch.core.http.request.body.BulkRequestDoc;
import mcouch.core.http.request.body.CouchRequestBody;
import mcouch.core.http.request.bulk.BulkItemRequest;
import mcouch.core.http.request.bulk.BulkRequestItemFactory;
import mcouch.core.http.response.EnclosedResponseForBulkRequest;
import mcouch.core.http.response.SuccessfulDocumentCreateResponse;
import mcouch.core.jackson.JSONSerializer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class CouchPostRequest implements CouchRequest {
    private static Logger logger = Logger.getLogger(CouchPostRequest.class);
    private HttpPost request;
    private CouchURI uri;

    public CouchPostRequest(HttpPost request) {
        this.request = request;
        uri = new CouchURI(request.getURI());
    }

    @Override
    public HttpResponse execute(Databases databases) {
        CouchRequestBody couchRequestBody = new CouchRequestBody(request);
        Database database = databases.getDatabase(uri.databaseName());
        String submittedJSON = couchRequestBody.submittedJSON();
        if (logger.isDebugEnabled()) logger.debug(String.format("Submitted JSON %s", submittedJSON));
        if (submittedJSON == null || database == null)
            throw new AssertionError();

        if (uri.isBulkDocsRequest()) {
            BulkRequest bulkRequest = JSONSerializer.fromJson(submittedJSON, BulkRequest.class);
            ArrayList<EnclosedResponseForBulkRequest> response = new ArrayList<>(bulkRequest.docs.size());
            for (BulkRequestDoc bulkRequestDoc : bulkRequest.docs) {
                BulkItemRequest bulkItemRequest = BulkRequestItemFactory.create(bulkRequestDoc.json);
                EnclosedResponseForBulkRequest enclosedResponseForBulkRequest = bulkItemRequest.execute(database);
                response.add(enclosedResponseForBulkRequest);
            }
            return StandardHttpResponse.okWith(JSONSerializer.toJson(response));
        } else {
            SuccessfulDocumentCreateResponse response = database.addDocument(submittedJSON);
            return StandardHttpResponse.okWith(response);
        }
    }
}