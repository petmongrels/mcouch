package mcouch.core;

import mcouch.core.couch.database.Databases;
import mcouch.core.http.ClientConnectionManagerStub;
import mcouch.core.http.CouchHttpRequestFactory;
import mcouch.core.http.HttpParamsStub;
import mcouch.core.http.NotImplementedException;
import mcouch.core.http.request.CouchRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class CouchDb implements HttpClient {
    private Databases databases;

    @Override
    public HttpParams getParams() {
        return new HttpParamsStub();
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        return new ClientConnectionManagerStub();
    }

    @Override
    public HttpResponse execute(HttpUriRequest httpUriRequest) throws IOException {
        CouchRequest couchRequest = CouchHttpRequestFactory.create(httpUriRequest);
        return couchRequest.execute(databases);
    }

    @Override
    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return null;
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }
}