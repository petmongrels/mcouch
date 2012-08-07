package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.couch.view.ViewDefinition;
import mcouch.core.couch.view.ViewGroup;
import mcouch.core.couch.view.ViewsDefinition;
import mcouch.core.couch.indexing.View;
import mcouch.core.http.NotImplementedException;
import mcouch.core.http.StandardHttpLine;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;

import java.net.URI;

public class CouchGetRequest implements CouchRequest {
    private CouchURI uri;

    public CouchGetRequest(URI uri) {
        this.uri = new CouchURI(uri);
    }

    @Override
    public HttpResponse execute(Databases databases) {
        if (databases.contains(uri.databaseName()))
            throw new NotImplementedException();

        Database database = databases.getDatabase(uri.databaseName());
        BasicHttpResponse response = new BasicHttpResponse(StandardHttpLine.OK);
        if (uri.isGetViewDocRequest()) {
            ViewsDefinition viewsDefinition = database.viewGroup(uri.viewGroup()).definition();
            response.setEntity(new StringEntity(viewsDefinition.document(), ContentType.APPLICATION_JSON));
            return response;
        }
        if (uri.isExecuteViewRequest()) {
            database.executeView(uri.viewGroup(), uri.viewName());
        }
        return null;
    }
}