package mcouch.core.http;

import org.apache.http.HttpHost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.params.HttpParams;

public class HttpParamsStub implements HttpParams {
    private final HttpHost inmemoryHost = new HttpHost("inmemoryHost");

    @Override
    public Object getParameter(String s) {
        if (ClientPNames.DEFAULT_HOST.equals(s)) return inmemoryHost;
        throw new NotImplementedException();
    }

    @Override
    public HttpParams setParameter(String s, Object o) {
        throw new NotImplementedException();
    }

    @Override
    public HttpParams copy() {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeParameter(String s) {
        throw new NotImplementedException();
    }

    @Override
    public long getLongParameter(String s, long l) {
        throw new NotImplementedException();
    }

    @Override
    public HttpParams setLongParameter(String s, long l) {
        throw new NotImplementedException();
    }

    @Override
    public int getIntParameter(String s, int i) {
        throw new NotImplementedException();
    }

    @Override
    public HttpParams setIntParameter(String s, int i) {
        throw new NotImplementedException();
    }

    @Override
    public double getDoubleParameter(String s, double v) {
        throw new NotImplementedException();
    }

    @Override
    public HttpParams setDoubleParameter(String s, double v) {
        throw new NotImplementedException();
    }

    @Override
    public boolean getBooleanParameter(String s, boolean b) {
        throw new NotImplementedException();
    }

    @Override
    public HttpParams setBooleanParameter(String s, boolean b) {
        throw new NotImplementedException();
    }

    @Override
    public boolean isParameterTrue(String s) {
        throw new NotImplementedException();
    }

    @Override
    public boolean isParameterFalse(String s) {
        throw new NotImplementedException();
    }
}