package mcouch.core.couch.indexing.query;

import mcouch.core.couch.indexing.View;
import mcouch.core.couch.indexing.IndexKey;

import java.util.*;

public class BetweenQuery implements IndexQuery {
    private View view;
    private final IndexKey startKey;
    private final IndexKey endKey;

    public BetweenQuery(View view, IndexKey startKey, IndexKey endKey) {
        this.view = view;
        this.startKey = startKey;
        this.endKey = endKey;
    }

    @Override
    public List<String> execute() {
        return view.itemsBetween(startKey, endKey);
    }
}