package mcouch.core.rhino;

import mcouch.core.TestContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExtractDocumentIdFunctionTest {
    @Test
    public void getFrom() {
        ExtractDocumentIdFunction extractDocumentIdFunction = new ExtractDocumentIdFunction(TestContext.JAVA_SCRIPT_INTERPRETER);
        assertEquals("457", extractDocumentIdFunction.getFrom("{\"_id\": \"457\"}"));
    }
}