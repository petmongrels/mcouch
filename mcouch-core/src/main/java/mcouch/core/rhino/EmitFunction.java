package mcouch.core.rhino;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.View;

public class EmitFunction {
    private View view;
    public static final String EMIT_FUNCTION = "function emit(one, two){if (two) return javaEmitFunction.emit(one, two); return javaEmitFunction.emit(one)}";
    private String docId;

    public void emit(String docId) {
        assertIndexNotNull();
        view.addOrUpdate(docId, docId, docId);
    }

    public void emit(Object indexKey, Object value) {
        assertIndexNotNull();
        view.addOrUpdate(IndexEntry.fromJSObject(indexKey), value, docId);
    }

    private void assertIndexNotNull() {
        if (view == null) {
            throw new AssertionError("No view set, which can be updated");
        }
    }

    public void currentIndex(View view) {
        this.view = view;
    }

    public void currentDoc(String docId) {
        this.docId = docId;
    }
}