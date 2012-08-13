package mcouch.core.http.request;

import mcouch.core.TestContext;
import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.sample.SampleDocuments;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.Assert.assertEquals;

public class CouchHeadRequestTest {
    @Test
    public void databaseDoesntExist() throws URISyntaxException {
        CouchHeadRequest couchHeadRequest = request("/motech-tb-adherence/");
        Databases databases = new Databases();
        assertEquals(HttpStatus.SC_NOT_FOUND, executionStatus(couchHeadRequest, databases));
    }

    @Test
    public void databaseExists() throws URISyntaxException {
        CouchHeadRequest couchHeadRequest = request("/motech-tb-adherence/");
        Databases databases = new Databases(new Database("motech-tb-adherence", TestContext.MAP_FUNCTION_INTERPRETER, TestContext.JAVA_SCRIPT_INTERPRETER));
        assertEquals(HttpStatus.SC_OK, executionStatus(couchHeadRequest, databases));
    }

    @Test
    public void viewGroupExists() throws URISyntaxException {
        CouchHeadRequest couchHeadRequest = request("/motech-tb-adherence/_design/AdherenceLog");
        Database database = new Database("motech-tb-adherence", TestContext.MAP_FUNCTION_INTERPRETER, TestContext.JAVA_SCRIPT_INTERPRETER);
        database.createViewGroup("AdherenceLog", SampleDocuments.DesignDoc);
        Databases databases = new Databases(database);
        assertEquals(HttpStatus.SC_OK, executionStatus(couchHeadRequest, databases));
    }

    private int executionStatus(CouchHeadRequest couchHeadRequest, Databases databases) {
        HttpResponse httpResponse = couchHeadRequest.execute(databases);
        return httpResponse.getStatusLine().getStatusCode();
    }

    private CouchHeadRequest request(String uriString) throws URISyntaxException {
        URI uri = new URI(uriString);
        return new CouchHeadRequest(new CouchURI(uri));
    }
}