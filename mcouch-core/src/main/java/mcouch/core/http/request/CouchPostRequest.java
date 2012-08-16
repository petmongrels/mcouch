package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.http.StandardHttpResponse;
import mcouch.core.http.request.body.BulkRequest;
import mcouch.core.http.request.body.CouchRequestBody;
import mcouch.core.http.request.body.EnclosedRequest;
import mcouch.core.http.response.EnclosedResponseForBulkRequest;
import mcouch.core.http.response.SuccessfulDocumentCreateResponse;
import mcouch.core.jackson.JSONSerializer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

import java.util.ArrayList;

public class CouchPostRequest implements CouchRequest {
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
        if (submittedJSON == null || database == null)
            throw new AssertionError();

        if (uri.isBulkDocsRequest()) {
            BulkRequest bulkRequest = JSONSerializer.fromJson(submittedJSON, BulkRequest.class);
            ArrayList<EnclosedResponseForBulkRequest> response = new ArrayList<>(bulkRequest.docs.size());
            for (EnclosedRequest enclosedRequest : bulkRequest.docs) {
                database.delete(enclosedRequest._id);
                EnclosedResponseForBulkRequest enclosedResponseForBulkRequest = new EnclosedResponseForBulkRequest(enclosedRequest._id, enclosedRequest._rev);
                response.add(enclosedResponseForBulkRequest);
            }
            return StandardHttpResponse.okWith(JSONSerializer.toJson(response));
        } else {
            SuccessfulDocumentCreateResponse response = database.addDocument(submittedJSON);
            return StandardHttpResponse.okWith(response);
        }
    }
}