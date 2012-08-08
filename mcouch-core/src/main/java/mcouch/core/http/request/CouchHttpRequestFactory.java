package mcouch.core.http.request;

import mcouch.core.http.NotImplementedException;
import mcouch.core.http.request.CouchGetRequest;
import mcouch.core.http.request.CouchHeadRequest;
import mcouch.core.http.request.CouchPutRequest;
import mcouch.core.http.request.CouchRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;

public class CouchHttpRequestFactory {
    public static CouchRequest create(HttpUriRequest request) {
        switch (request.getMethod()) {
            case "HEAD":
                return new CouchHeadRequest(new CouchURI(request.getURI(), request.getMethod()));
            case "GET":
                return new CouchGetRequest(new CouchURI(request.getURI(), request.getMethod()));
            case "PUT":
                return new CouchPutRequest((HttpPut) request);
            case "POST":
                return new CouchPostRequest((HttpPost) request);
            default:
                throw new NotImplementedException();
        }
    }
}