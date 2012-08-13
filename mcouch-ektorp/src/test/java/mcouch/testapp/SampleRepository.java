package mcouch.testapp;

import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.GenerateView;

import java.util.List;

public class SampleRepository extends org.ektorp.support.CouchDbRepositorySupport<SampleEntity> {
    public SampleRepository(CouchDbConnector db) {
        super(SampleEntity.class, db);
        initStandardDesignDocument();
    }

    @GenerateView
    public List<SampleEntity> findByA(String a) {
        ViewQuery find_by_a = createQuery("by_a").key(a.toLowerCase()).includeDocs(true);
        return db.queryView(find_by_a, SampleEntity.class);
    }

    public List<SampleEntity> findByAInRange(String start, String end) {
        ViewQuery q = createQuery("by_a").startKey(start).endKey(end).inclusiveEnd(true).reduce(false);
        return db.queryView(q, SampleEntity.class);
    }
}