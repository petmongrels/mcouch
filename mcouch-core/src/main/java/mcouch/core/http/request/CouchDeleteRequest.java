package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.http.StandardHttpResponse;
import mcouch.core.http.response.SuccessfulDocumentDeleteResponse;
import org.apache.http.HttpResponse;

public class CouchDeleteRequest implements CouchRequest {
    private CouchURI couchURI;

    public CouchDeleteRequest(CouchURI couchURI) {
        this.couchURI = couchURI;
    }

    @Override
    public HttpResponse execute(Databases databases) {
        Database database = databases.getDatabase(couchURI.databaseName());
        database.delete(couchURI.documentId());
        SuccessfulDocumentDeleteResponse documentDeleteResponse = new SuccessfulDocumentDeleteResponse(couchURI.getRev());
        return StandardHttpResponse.okWith(documentDeleteResponse);
    }
}