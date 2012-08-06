package mcouch.core.http;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;

import java.util.concurrent.TimeUnit;

public class ClientConnectionManagerStub implements ClientConnectionManager {
    @Override
    public SchemeRegistry getSchemeRegistry() {
        throw new NotImplementedException();
    }

    @Override
    public ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object o) {
        throw new NotImplementedException();
    }

    @Override
    public void releaseConnection(ManagedClientConnection managedClientConnection, long l, TimeUnit timeUnit) {
        throw new NotImplementedException();
    }

    @Override
    public void closeIdleConnections(long l, TimeUnit timeUnit) {
        throw new NotImplementedException();
    }

    @Override
    public void closeExpiredConnections() {
        throw new NotImplementedException();
    }

    @Override
    public void shutdown() {
    }
}