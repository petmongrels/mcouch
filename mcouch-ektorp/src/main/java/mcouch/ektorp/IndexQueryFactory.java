package mcouch.ektorp;

import mcouch.core.couch.indexing.Index;
import mcouch.core.couch.indexing.IndexKey;
import mcouch.core.couch.indexing.query.BetweenQuery;
import mcouch.core.couch.indexing.query.IndexQuery;
import mcouch.core.couch.indexing.query.SimpleQuery;
import org.ektorp.ComplexKey;
import org.ektorp.ViewQuery;

public class IndexQueryFactory {
    public static IndexQuery create(ViewQuery viewQuery, Index index) {
        if (viewQuery.getKey() != null)
            return new SimpleQuery(index, new IndexKey(viewQuery.getKey().toString()));
        if (viewQuery.getStartKey() != null) {
            ComplexKey startKey = (ComplexKey) viewQuery.getStartKey();
            ComplexKey endKey = (ComplexKey) viewQuery.getEndKey();
            return new BetweenQuery(index, new IndexKey(startKey.toJson().toString()), new IndexKey(endKey.toJson().toString()));
        }
        throw new MektorpException("Cannot create IndexQuery for ViewQuery: " + viewQuery.toString());
    }
}