package mcouch.core.http.request;

import mcouch.core.couch.database.Databases;
import org.apache.http.HttpResponse;
import org.apache.http.params.HttpParams;

import java.net.URI;

public class CouchGetRequest implements CouchRequest {
    private URI uri;
    private HttpParams params;

    public CouchGetRequest(URI uri, HttpParams params) {
        this.uri = uri;
        this.params = params;
    }

    @Override
    public HttpResponse execute(Databases databases) {
        return null;
    }
}