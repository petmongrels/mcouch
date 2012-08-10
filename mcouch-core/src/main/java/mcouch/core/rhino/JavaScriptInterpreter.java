package mcouch.core.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJSON;
import org.mozilla.javascript.ScriptableObject;

public class JavaScriptInterpreter {
    private Context context;
    private ScriptableObject scope;

    public JavaScriptInterpreter() {
        context = Context.enter();
        scope = context.initStandardObjects();
    }

    public void defineLink(Object javaObject, String jsVariableName) {
        Object jsEmitFunctionRef = Context.javaToJS(javaObject, scope);
        ScriptableObject.putProperty(scope, jsVariableName, jsEmitFunctionRef);
    }

    public Object interpret(String javaScriptCode) {
        return context.evaluateString(scope, javaScriptCode, "<cmd>", 1, null);
    }

    public String stringiFy(Object object) {
        return NativeJSON.stringify(context, scope, object, null, null).toString();
    }
}