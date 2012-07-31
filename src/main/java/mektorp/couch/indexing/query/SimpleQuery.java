package mektorp.couch.indexing.query;

import mektorp.couch.indexing.IndexEntry;
import mektorp.couch.indexing.IndexKey;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SimpleQuery implements IndexQuery {
    private final TreeMap<IndexKey, IndexEntry> items;
    private final IndexKey indexKey;

    public SimpleQuery(TreeMap<IndexKey, IndexEntry> items, IndexKey indexKey) {
        this.items = items;
        this.indexKey = indexKey;
    }

    @Override
    public List<String> execute() {
        IndexEntry indexEntry = items.get(indexKey);
        return indexEntry == null ? new ArrayList<String>() : indexEntry.documentIds();
    }
}