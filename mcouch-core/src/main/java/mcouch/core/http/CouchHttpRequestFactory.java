package mcouch.core.http;

import mcouch.core.http.request.CouchGetRequest;
import mcouch.core.http.request.CouchHeadRequest;
import mcouch.core.http.request.CouchRequest;
import org.apache.http.client.methods.HttpUriRequest;

public class CouchHttpRequestFactory {
    public static CouchRequest create(HttpUriRequest request) {
        switch (request.getMethod()) {
            case "HEAD" :
                return new CouchHeadRequest(request.getURI());
            case "GET" :
                return new CouchGetRequest(request.getURI(), request.getParams());
            default:
                throw new NotImplementedException();
        }
    }
}