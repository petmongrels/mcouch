package mektorp.couch.indexing.query;

import mektorp.couch.indexing.IndexEntry;
import mektorp.couch.indexing.IndexKey;

import java.util.*;

public class BetweenQuery implements IndexQuery {
    private final TreeMap<IndexKey, IndexEntry> items;
    private final IndexKey startKey;
    private final IndexKey endKey;

    public BetweenQuery(TreeMap<IndexKey, IndexEntry> items, IndexKey startKey, IndexKey endKey) {
        this.items = items;
        this.startKey = startKey;
        this.endKey = endKey;
    }

    @Override
    public List<String> execute() {
        SortedMap<IndexKey, IndexEntry> subMap = items.subMap(startKey, true, endKey, true);
        ArrayList<String> documentIds = new ArrayList<>();
        for (IndexKey indexKey : subMap.keySet()) {
            IndexEntry indexEntry = items.get(indexKey);
            documentIds.addAll(indexEntry.documentIds());
        }
        return documentIds;
    }
}