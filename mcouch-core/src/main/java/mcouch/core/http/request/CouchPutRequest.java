package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.http.StandardHttpResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import java.io.StringWriter;

public class CouchPutRequest implements CouchRequest {
    private CouchURI uri;
    private HttpPut request;

    public CouchPutRequest(HttpPut request) {
        this.request = request;
        uri = new CouchURI(request.getURI(), request.getMethod());
    }

    @Override
    public HttpResponse execute(Databases databases) {
        CouchRequestBody couchRequestBody = new CouchRequestBody(request);
        if (couchRequestBody.submittedJSON() == null && !databases.contains(uri.databaseName())) {
            databases.create(uri.databaseName());
            return StandardHttpResponse.OK;
        }
        Database database = databases.getDatabase(uri.databaseName());
        if (couchRequestBody.submittedJSON() != null && database != null) {
            database.createViewGroup(uri.viewGroup(), couchRequestBody.submittedJSON());
            return StandardHttpResponse.OK;
        }
        throw new AssertionError();
    }
}