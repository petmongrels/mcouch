package mcouch.core.couch.indexing.query;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;
import mcouch.core.couch.indexing.View;

import java.util.NavigableMap;

public class BetweenQuery implements IndexQuery {
    private final IndexKey startKey;
    private final IndexKey endKey;

    public BetweenQuery(String start, String end) {
        this.startKey = new IndexKey(start);
        this.endKey = new IndexKey(end);
    }

    @Override
    public NavigableMap<IndexKey, IndexEntry> execute(View view) {
        return view.itemsBetween(startKey, endKey);
    }
}