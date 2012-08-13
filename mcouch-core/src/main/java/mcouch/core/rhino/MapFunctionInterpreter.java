package mcouch.core.rhino;

import mcouch.core.couch.indexing.View;

public class MapFunctionInterpreter {
    private EmitFunction emitFunction;
    private final JavaScriptInterpreter javaScriptInterpreter;

    public MapFunctionInterpreter(JavaScriptInterpreter javaScriptInterpreter) {
        emitFunction = new EmitFunction();
        this.javaScriptInterpreter = javaScriptInterpreter;
        javaScriptInterpreter.defineLink(emitFunction, "javaEmitFunction");
    }

    public void interpret(String mapFunction, String document) {
        String completeJS = String.format("%s (%s) (%s)", EmitFunction.EMIT_FUNCTION, mapFunction, document);
        javaScriptInterpreter.interpret(completeJS);
    }

    public void emitOn(View view) {
        emitFunction.currentIndex(view);
    }
}