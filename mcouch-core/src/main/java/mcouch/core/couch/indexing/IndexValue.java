package mcouch.core.couch.indexing;

public class IndexValue {
    private final Object object;
    private final String docId;

    public IndexValue(Object object, String docId) {
        this.object = object;
        this.docId = docId;
    }

    public boolean isDocId() {
        return object instanceof String;
    }

    public String getDocId() {
        return docId;
    }

    public Object getObject() {
        return object;
    }
}