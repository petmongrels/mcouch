package mcouch.core.http.request;

import mcouch.core.couch.database.Databases;
import org.apache.http.HttpResponse;

public interface CouchRequest {
    public HttpResponse execute(Databases databases);
}
