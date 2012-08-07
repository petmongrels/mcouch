package mcouch.core;

import mcouch.core.rhino.JavaScriptInterpreter;
import mcouch.core.rhino.MapFunctionInterpreter;

public class TestContext {
    public static final JavaScriptInterpreter JAVA_SCRIPT_INTERPRETER = new JavaScriptInterpreter();
    public static final MapFunctionInterpreter MAP_FUNCTION_INTERPRETER = new MapFunctionInterpreter(JAVA_SCRIPT_INTERPRETER);
}