package mcouch.core.rhino;

import mcouch.core.couch.indexing.View;
import mcouch.core.jackson.JSONSerializer;

public class MapFunctionInterpreter {
    private EmitFunction emitFunction;
    private final JavaScriptInterpreter javaScriptInterpreter;

    public MapFunctionInterpreter(JavaScriptInterpreter javaScriptInterpreter) {
        emitFunction = new EmitFunction();
        this.javaScriptInterpreter = javaScriptInterpreter;
        javaScriptInterpreter.defineLink(emitFunction, "javaEmitFunction");
    }

    public void interpret(String mapFunction, Object object) {
        String jsonedObject = JSONSerializer.toJson(object);
        String completeJS = String.format("%s (%s) (%s)", EmitFunction.EMIT_FUNCTION, mapFunction, jsonedObject);
        javaScriptInterpreter.interpret(completeJS);
    }

    public EmitFunction emitFunction() {
        return emitFunction;
    }

    public void emitOn(View view) {
        emitFunction.currentIndex(view);
    }
}