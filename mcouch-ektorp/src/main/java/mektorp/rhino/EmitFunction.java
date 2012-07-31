package mektorp.rhino;

import mektorp.couch.indexing.Index;
import org.ektorp.ComplexKey;

public class EmitFunction {
    private Index index;

    public static final String EMIT_FUNCTION = "function emit(one, two){return javaEmitFunction.emit(one, two);}";

    public void emit(String indexValue, String docId) {
        if (index == null) {
            throw new AssertionError("Not index set, which can be updated");
        }
        index.addOrUpdate(indexValue, docId);
    }

    public void emit(String[] indexValues, String docId) {
        ComplexKey complexKey = ComplexKey.of(indexValues);
        if (index == null) {
            throw new AssertionError("Not index set, which can be updated");
        }
        index.addOrUpdate(complexKey.toJson().toString(), docId);
    }

    public void currentIndex(Index index) {
        this.index = index;
    }
}