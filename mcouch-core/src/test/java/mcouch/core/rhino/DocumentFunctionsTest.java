package mcouch.core.rhino;

import mcouch.core.TestContext;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentFunctionsTest {
    @Test
    public void writeRevision() {
        String json = "{\"_id\": \"_design/AdherenceLog\"}";
        String changedJson = TestContext.DOCUMENT_FUNCTIONS.updateExistingDocument(json);
        assertTrue(changedJson.contains("_rev"));
    }

    @Test
    public void getFrom() {
        DocumentFunctions documentIdFunction = new DocumentFunctions(TestContext.JAVA_SCRIPT_INTERPRETER);
        assertEquals("457", documentIdFunction.getDocumentId("{\"_id\": \"457\"}"));
    }
}