package mcouch.core.couch.indexing;

import java.util.Comparator;

public class IndexKeyComparator implements Comparator<IndexKey> {
    @Override
    public int compare(IndexKey o1, IndexKey o2) {
        return indexedValue(o1).compareTo(indexedValue(o2));
    }

    private String indexedValue(IndexKey o1) {
        return o1.indexedValue() == null ? "" : o1.indexedValue();
    }
}