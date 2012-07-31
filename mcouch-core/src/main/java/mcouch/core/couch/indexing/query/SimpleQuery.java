package mcouch.core.couch.indexing.query;

import mcouch.core.couch.indexing.Index;
import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SimpleQuery implements IndexQuery {
    private Index index;
    private final IndexKey indexKey;

    public SimpleQuery(Index index, IndexKey indexKey) {
        this.index = index;
        this.indexKey = indexKey;
    }

    @Override
    public List<String> execute() {
        IndexEntry indexEntry = index.get(indexKey);
        return indexEntry == null ? new ArrayList<String>() : indexEntry.documentIds();
    }
}