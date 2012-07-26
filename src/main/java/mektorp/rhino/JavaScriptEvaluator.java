package mektorp.rhino;

import mektorp.jackson.JSONSerializer;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class JavaScriptEvaluator {
    public Object evalMap(String function, Object o) {
        Context context = Context.enter();
        ScriptableObject scope = context.initStandardObjects();
        String jsonedObject = JSONSerializer.toJson(o);
        return context.evaluateString(scope, String.format("(%s)(%s)", function, jsonedObject), "<cmd>", 1, null);
    }
}