package mcouch.core.couch.indexing.query;

import mcouch.core.couch.indexing.View;
import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;

import java.util.ArrayList;
import java.util.List;

public class SimpleQuery implements IndexQuery {
    private View view;
    private final IndexKey indexKey;

    public SimpleQuery(View view, IndexKey indexKey) {
        this.view = view;
        this.indexKey = indexKey;
    }

    @Override
    public List<String> execute() {
        IndexEntry indexEntry = view.get(indexKey);
        return indexEntry == null ? new ArrayList<String>() : indexEntry.documentIds();
    }
}