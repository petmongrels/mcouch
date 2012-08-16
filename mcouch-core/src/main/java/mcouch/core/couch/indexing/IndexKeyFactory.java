package mcouch.core.couch.indexing;

public class IndexKeyFactory {
    public static IndexKey create(String indexValue) {
        if (indexValue == null) return NullIndexKey.Instance;
        return new IndexKey(indexValue);
    }
}