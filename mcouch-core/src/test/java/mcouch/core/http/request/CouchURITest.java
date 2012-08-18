package mcouch.core.http.request;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.Assert.assertEquals;

public class CouchURITest {
    @Test
    public void viewRequest() throws URISyntaxException {
        URI uri = new URI("/motech-whp/_design/Patient/_view/by_patientId?key=%22patient1%22");
        CouchURI couchURI = new CouchURI(uri);
        assertEquals("motech-whp", couchURI.databaseName());
        assertEquals("Patient", couchURI.viewGroup());
        assertEquals("by_patientId", couchURI.viewName());
        assertEquals("\"patient1\"", couchURI.getKey());
    }

    @Test
    public void getDocRequest() throws URISyntaxException {
        URI uri = new URI("/motech-whp/4574564835643");
        CouchURI couchURI = new CouchURI(uri);
        assertEquals(true, couchURI.isDocRequest());
    }
}