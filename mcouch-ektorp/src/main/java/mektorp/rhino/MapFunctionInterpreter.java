package mektorp.rhino;

import mektorp.jackson.JSONSerializer;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class MapFunctionInterpreter {
    private Context context;
    private ScriptableObject scope;

    public MapFunctionInterpreter(EmitFunction emitFunction) {
        context = Context.enter();
        scope = context.initStandardObjects();
        Object jsEmitFunctionRef = Context.javaToJS(emitFunction, scope);
        ScriptableObject.putProperty(scope, "javaEmitFunction", jsEmitFunctionRef);
    }

    public void interpret(String mapFunction, Object object) {
        String jsonedObject = JSONSerializer.toJson(object);
        String completeJS = String.format("%s (%s) (%s)", EmitFunction.EMIT_FUNCTION, mapFunction, jsonedObject);
        context.evaluateString(scope, completeJS, "<cmd>", 1, null);
    }
}