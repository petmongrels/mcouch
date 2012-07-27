package mektorp.rhino;

import mektorp.jackson.JSONSerializer;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class MapFunctionInterpreter {
    private Context context;
    private ScriptableObject scope;

    public MapFunctionInterpreter(Indexer indexer) {
        context = Context.enter();
        scope = context.initStandardObjects();
        Object jsIndexerRef = Context.javaToJS(indexer, scope);
        ScriptableObject.putProperty(scope, "indexer", jsIndexerRef);
    }

    public void interpret(String function, Object object) {
        String jsonedObject = JSONSerializer.toJson(object);
        context.evaluateString(scope, String.format("%s(%s)", function, jsonedObject), "<cmd>", 1, null);
    }
}