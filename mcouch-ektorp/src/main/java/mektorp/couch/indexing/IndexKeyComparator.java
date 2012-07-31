package mektorp.couch.indexing;

import java.util.Comparator;

public class IndexKeyComparator implements Comparator<IndexKey> {
    @Override
    public int compare(IndexKey o1, IndexKey o2) {
        return o1.indexedValue().compareTo(o2.indexedValue());
    }
}