package mcouch.core.couch.indexing.query;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;
import mcouch.core.couch.indexing.View;

import java.util.Map;

public class BetweenQuery implements IndexQuery {
    private final IndexKey startKey;
    private final IndexKey endKey;

    public BetweenQuery(String start, String end) {
        this.startKey = new IndexKey(start);
        this.endKey = new IndexKey(end);
    }

    @Override
    public Map<IndexKey, IndexEntry> execute(View view) {
        return view.itemsBetween(startKey, endKey);
    }
}