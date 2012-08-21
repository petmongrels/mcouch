package mcouch.core.couch.reducers;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;

import java.util.Map;

public class CountReducer implements Reducer {
    @Override
    public int reduce(Map<IndexKey, IndexEntry> map) {
        return map.size();
    }
}