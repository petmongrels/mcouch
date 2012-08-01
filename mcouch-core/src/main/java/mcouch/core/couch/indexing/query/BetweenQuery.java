package mcouch.core.couch.indexing.query;

import mcouch.core.couch.IndexEntries;
import mcouch.core.couch.indexing.Index;
import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;

import java.util.*;

public class BetweenQuery implements IndexQuery {
    private Index index;
    private final IndexKey startKey;
    private final IndexKey endKey;

    public BetweenQuery(Index index, IndexKey startKey, IndexKey endKey) {
        this.index = index;
        this.startKey = startKey;
        this.endKey = endKey;
    }

    @Override
    public List<String> execute() {
        return index.itemsBetween(startKey, endKey);
    }
}