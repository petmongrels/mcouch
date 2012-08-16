package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.couch.view.ViewGroup;
import mcouch.core.http.StandardHttpResponse;
import mcouch.core.http.request.body.CouchRequestBody;
import mcouch.core.http.response.SuccessfulDocumentCreateResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;

public class CouchPutRequest implements CouchRequest {
    private CouchURI uri;
    private HttpPut request;

    public CouchPutRequest(HttpPut request) {
        this.request = request;
        uri = new CouchURI(request.getURI());
    }

    @Override
    public HttpResponse execute(Databases databases) {
        CouchRequestBody couchRequestBody = new CouchRequestBody(request);
        if (couchRequestBody.submittedJSON() == null && !databases.contains(uri.databaseName())) {
            databases.create(uri.databaseName());
            return StandardHttpResponse.OK;
        }

        Database database = databases.getDatabase(uri.databaseName());
        if (uri.isDocRequest()) {
            SuccessfulDocumentCreateResponse response = database.updateDocument(couchRequestBody.submittedJSON());
            return StandardHttpResponse.okWith(response);
        }

        if (couchRequestBody.submittedJSON() != null && database != null) {
            ViewGroup viewGroup = database.createViewGroup(uri.viewGroup(), couchRequestBody.submittedJSON());
            SuccessfulDocumentCreateResponse response = new SuccessfulDocumentCreateResponse(viewGroup.id(), viewGroup.definition().revision());
            return StandardHttpResponse.okWith(response);
        }
        throw new AssertionError();
    }
}