package mektorp.couch.indexing.query;

import mektorp.MektorpException;
import mektorp.couch.indexing.IndexEntry;
import mektorp.couch.indexing.IndexKey;
import org.ektorp.ComplexKey;
import org.ektorp.ViewQuery;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.TreeMap;

public class IndexQueryFactory {
    public static IndexQuery create(TreeMap<IndexKey, IndexEntry> items, ViewQuery viewQuery) {
        if (viewQuery.getKey() != null)
            return new SimpleQuery(items, new IndexKey(viewQuery.getKey().toString()));
        if (viewQuery.getStartKey() != null) {
            ComplexKey startKey = (ComplexKey) viewQuery.getStartKey();
            ComplexKey endKey = (ComplexKey) viewQuery.getEndKey();
            return new BetweenQuery(items, new IndexKey(startKey.toJson().toString()), new IndexKey(endKey.toJson().toString()));
        }
        throw new MektorpException("Cannot create IndexQuery for ViewQuery: " + viewQuery.toString());
    }
}