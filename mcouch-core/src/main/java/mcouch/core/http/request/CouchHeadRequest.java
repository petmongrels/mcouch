package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.http.StandardHttpResponse;
import org.apache.http.HttpResponse;

import java.net.URI;
import java.util.StringTokenizer;

public class CouchHeadRequest implements CouchRequest {
    private URI uri;

    public CouchHeadRequest(URI uri) {
        this.uri = uri;
    }

    @Override
    public HttpResponse execute(Databases databases) {
        String uriPath = uri.getPath();
        StringTokenizer pathTokenizer = new StringTokenizer(uriPath, "/");
        String databaseName = pathTokenizer.nextToken();
        if (pathTokenizer.countTokens() == 0) {
            return responseBasedOnPresence(databases.contains(databaseName));
        } else if (databases.contains(databaseName) && pathTokenizer.countTokens() == 2 && pathTokenizer.nextToken().equals("_design")) {
            Database database = databases.getDatabase(databaseName);
            return responseBasedOnPresence(database.containsViewGroup(pathTokenizer.nextToken()));
        }
        return null;
    }

    private HttpResponse responseBasedOnPresence(boolean presence) {
        return presence ? StandardHttpResponse.OK : StandardHttpResponse.NOT_FOUND;
    }
}