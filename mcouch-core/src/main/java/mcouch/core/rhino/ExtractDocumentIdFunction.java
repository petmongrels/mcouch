package mcouch.core.rhino;

public class ExtractDocumentIdFunction {
    private JavaScriptInterpreter javaScriptInterpreter;

    public static String ExtractDocumentIdFunction = "(function extractDocId(doc){return doc._id;}) (%s)";

    public ExtractDocumentIdFunction(JavaScriptInterpreter javaScriptInterpreter) {
        this.javaScriptInterpreter = javaScriptInterpreter;
    }

    public String getFrom(String document) {
        return javaScriptInterpreter.interpret(String.format(ExtractDocumentIdFunction, document)).toString();
    }
}