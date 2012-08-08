package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.couch.view.ViewsDefinition;
import mcouch.core.http.NotImplementedException;
import mcouch.core.http.StandardHttpResponse;
import org.apache.http.HttpResponse;

public class CouchGetRequest implements CouchRequest {
    private CouchURI uri;

    public CouchGetRequest(CouchURI uri) {
        this.uri = uri;
    }

    @Override
    public HttpResponse execute(Databases databases) {
        if (databases.contains(uri.databaseName()))
            throw new NotImplementedException();

        Database database = databases.getDatabase(uri.databaseName());
        if (uri.isGetViewDocRequest()) {
            ViewsDefinition viewsDefinition = database.viewGroup(uri.viewGroup()).definition();
            return StandardHttpResponse.okWith(viewsDefinition.document());
        }
        if (uri.isExecuteViewRequest()) {
            String response = database.executeView(uri.viewGroup(), uri.viewName());
            return StandardHttpResponse.okWith(response);
        }
        return null;
    }
}