package mcouch.ektorp;

import mcouch.core.couch.indexing.View;
import mcouch.core.couch.indexing.IndexKey;
import mcouch.core.couch.indexing.query.BetweenQuery;
import mcouch.core.couch.indexing.query.IndexQuery;
import mcouch.core.couch.indexing.query.SimpleQuery;
import org.ektorp.ComplexKey;
import org.ektorp.ViewQuery;

public class IndexQueryFactory {
    public static IndexQuery create(ViewQuery viewQuery, View view) {
        if (viewQuery.getKey() != null)
            return new SimpleQuery(view, new IndexKey(viewQuery.getKey().toString()));
        if (viewQuery.getStartKey() != null) {
            ComplexKey startKey = (ComplexKey) viewQuery.getStartKey();
            ComplexKey endKey = (ComplexKey) viewQuery.getEndKey();
            return new BetweenQuery(view, new IndexKey(startKey.toJson().toString()), new IndexKey(endKey.toJson().toString()));
        }
        throw new MCouchEktorpException("Cannot create IndexQuery for ViewQuery: " + viewQuery.toString());
    }
}