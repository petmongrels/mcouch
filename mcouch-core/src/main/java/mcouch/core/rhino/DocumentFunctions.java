package mcouch.core.rhino;

public class DocumentFunctions {
    private JavaScriptInterpreter javaScriptInterpreter;
    public static String UpdateExistingDocument = "(function updateExistingDocument(doc, rev){doc._rev = rev; return doc;}) (%s, \"%s\")";
    public static String GetDocumentId = "(function extractDocId(doc){return doc._id;}) (%s)";
    public static String UpdateNewDocument = "(function updateNewDoc(doc, id, rev){doc._id = id; doc._rev = rev; return doc;}) (%s, \"%s\", \"%s\")";

    public DocumentFunctions(JavaScriptInterpreter javaScriptInterpreter) {
        this.javaScriptInterpreter = javaScriptInterpreter;
    }

    public String updateExistingDocument(String json, String revision) {
        Object updatedDoc = javaScriptInterpreter.interpret(String.format(UpdateExistingDocument, json, revision));
        return javaScriptInterpreter.stringiFy(updatedDoc);
    }

    public String getDocumentId(String document) {
        return javaScriptInterpreter.interpret(String.format(GetDocumentId, document)).toString();
    }

    public String updateNewDocument(String document, String id, String revision) {
        Object updatedDoc = javaScriptInterpreter.interpret(String.format(UpdateNewDocument, document, id, revision));
        return javaScriptInterpreter.stringiFy(updatedDoc);
    }
}