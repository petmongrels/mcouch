package mcouch.core;

import mcouch.core.couch.database.Databases;
import mcouch.core.http.ClientConnectionManagerStub;
import mcouch.core.http.HttpParamsStub;
import mcouch.core.http.NotImplementedException;
import mcouch.core.http.request.CouchHttpRequestFactory;
import mcouch.core.http.request.CouchRequest;
import mcouch.core.rhino.JavaScriptInterpreter;
import mcouch.core.rhino.MapFunctionInterpreter;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import java.io.IOException;

public class InMemoryCouchDb implements HttpClient {
    private static Logger logger = Logger.getLogger(InMemoryCouchDb.class);
    private Databases databases;
    private CouchHttpRequestFactory couchHttpRequestFactory;

    public InMemoryCouchDb() {
        this(JavaScriptInterpreter.Instance, MapFunctionInterpreter.Instance);
    }

    public InMemoryCouchDb(JavaScriptInterpreter javaScriptInterpreter, MapFunctionInterpreter mapFunctionInterpreter) {
        this.databases = new Databases(javaScriptInterpreter, mapFunctionInterpreter);
        this.couchHttpRequestFactory = new CouchHttpRequestFactory(javaScriptInterpreter);
    }

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
        return execute((HttpRequestBase) httpUriRequest);
    }

    private HttpResponse execute(HttpRequestBase httpRequestBase) {
        logger.info(String.format("%s---%s", httpRequestBase.getURI().toString(), httpRequestBase.getMethod()));
        CouchRequest couchRequest = couchHttpRequestFactory.create(httpRequestBase);
        return couchRequest.execute(databases);
    }

    @Override
    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return execute((HttpRequestBase)httpRequest);
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

    public void createDatabase(String databaseName) {
        databases.create(databaseName);
    }
}