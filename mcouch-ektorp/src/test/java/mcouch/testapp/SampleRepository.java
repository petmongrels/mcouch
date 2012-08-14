package mcouch.testapp;

import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.support.View;

import java.util.List;

public class SampleRepository extends org.ektorp.support.CouchDbRepositorySupport<SampleEntity> {
    public SampleRepository(CouchDbConnector db) {
        super(SampleEntity.class, db);
        initStandardDesignDocument();
    }

    @View(name = "by_a", map = "function(doc) { if(doc.type == 'Sample' && doc.a) {emit(doc.a, doc._id)} }", reduce = "_count")
    public List<SampleEntity> findByA(String a) {
        ViewQuery find_by_a = createQuery("by_a").key(a.toLowerCase()).includeDocs(true).reduce(false);
        return db.queryView(find_by_a, SampleEntity.class);
    }

    public List<SampleEntity> findByAInRange(String start, String end) {
        ViewQuery q = createQuery("by_a").startKey(start).endKey(end).inclusiveEnd(true).reduce(false);
        return db.queryView(q, SampleEntity.class);
    }

    public int numberOfItemsInRange(String start, String end) {
        ViewQuery q = createQuery("by_a").startKey(start).endKey(end).inclusiveEnd(true).reduce(true);
        ViewResult viewResult = db.queryView(q);
        List<ViewResult.Row> rows = viewResult.getRows();
        return rows.size() == 0 ? 0 : rows.get(0).getValueAsInt();
    }
}