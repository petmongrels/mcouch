package mcouch.core.rhino;

import mcouch.core.TestContext;
import org.junit.Assert;
import org.junit.Test;
import org.mozilla.javascript.NativeArray;

public class JavaScriptInterpreterTest {
    @Test
    public void defineNativeTypes() {
        Object array = TestContext.JAVA_SCRIPT_INTERPRETER.interpret("([\"foo\"])");
        Assert.assertEquals(array.getClass(), NativeArray.class);
    }
}