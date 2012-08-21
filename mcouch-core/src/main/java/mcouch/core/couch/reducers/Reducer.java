package mcouch.core.couch.reducers;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;

import java.util.Map;

public interface Reducer {
    int reduce(Map<IndexKey, IndexEntry> map);
}