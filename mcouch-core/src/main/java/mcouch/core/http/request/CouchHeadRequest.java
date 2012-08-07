package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.http.StandardHttpResponse;
import org.apache.http.HttpResponse;

import java.net.URI;

public class CouchHeadRequest implements CouchRequest {
    private CouchURI uri;

    public CouchHeadRequest(URI uri) {
        this.uri = new CouchURI(uri);
    }

    @Override
    public HttpResponse execute(Databases databases) {
        if (uri.viewGroup() == null) {
            return responseBasedOnPresence(databases.contains(uri.databaseName()));
        } else if (databases.contains(uri.databaseName()) && uri.viewGroup() != null) {
            Database database = databases.getDatabase(uri.databaseName());
            return responseBasedOnPresence(database.containsViewGroup(uri.viewGroup()));
        }
        return null;
    }

    private HttpResponse responseBasedOnPresence(boolean presence) {
        return presence ? StandardHttpResponse.OK : StandardHttpResponse.NOT_FOUND;
    }
}