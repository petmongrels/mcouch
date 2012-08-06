package mcouch.core.http.request;

import mcouch.core.couch.database.Databases;
import org.apache.http.HttpResponse;

import java.net.URI;

public class CouchPutRequest implements CouchRequest {
    private URI uri;
    private String body;

    public CouchPutRequest(URI uri, String body) {
        this.uri = uri;
        this.body = body;
    }

    @Override
    public HttpResponse execute(Databases databases) {
        return null;
    }
}