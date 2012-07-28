package mektorp.rhino;

import mektorp.couch.Index;

public class EmitFunction {
    private Index index;

    public static final String EMIT_FUNCTION = "function emit(one, two){return javaEmitFunction.emit(one, two);}";

    public void emit(String indexValue, String docId) {
        if (index == null) {
            throw new AssertionError("Not index set, which can be updated");
        }
        index.addOrUpdate(indexValue, docId);
    }

    public void currentIndex(Index index) {
        this.index = index;
    }
}