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

    public Object interpret(String function, Object o) {
        String jsonedObject = JSONSerializer.toJson(o);
        return context.evaluateString(scope, String.format("%s(%s)", function, jsonedObject), "<cmd>", 1, null);
    }
}