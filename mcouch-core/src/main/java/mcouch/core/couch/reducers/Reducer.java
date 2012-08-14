package mcouch.core.couch.reducers;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;

import java.util.NavigableMap;

public interface Reducer {
    int reduce(NavigableMap<IndexKey, IndexEntry> map);
}