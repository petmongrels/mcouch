package mcouch.testapp;

import org.ektorp.ComplexKey;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;

import java.util.List;

public class SampleRepository extends org.ektorp.support.CouchDbRepositorySupport<SampleEntity> {
    public SampleRepository(CouchDbConnector db) {
        super(SampleEntity.class, db);
        initStandardDesignDocument();
    }

    @View(name = "by_a_without_reduce", map = "function(doc) { if(doc.type == 'Sample' && doc.a) {emit(doc.a, doc._id)} }")
    public List<SampleEntity> findByAWithoutReduce(String a) {
        ViewQuery find_by_a = createQuery("by_a_without_reduce").key(a.toLowerCase()).includeDocs(true).reduce(false);
        return db.queryView(find_by_a, SampleEntity.class);
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

    @Override
    @GenerateView
    public List<SampleEntity> getAll() {
        return super.getAll();
    }

    @View(name = "by_a_and_a", map = "function(doc) {if (doc.type == 'Sample') {emit([doc.a, doc.a], doc._id);}}")
    public List<SampleEntity> findUsingTwoEmittedValues(String a) {
        ComplexKey startKey = ComplexKey.of(a, a);
        ComplexKey endKey = ComplexKey.of(a, a);
        ViewQuery q = createQuery("by_a_and_a").startKey(startKey).endKey(endKey).inclusiveEnd(true).includeDocs(true);
        return db.queryView(q, SampleEntity.class);
    }

    @View(name = "by_a_custom_data", map = "function(doc) {if (doc.type =='Sample') {emit(doc.a, {a:doc.a, type:doc.type});}}")
    public List<SampleEntityPart> loadCustomDataStructure(String a) {
        ViewQuery q = createQuery("by_a_custom_data").key(a);
        return db.queryView(q, SampleEntityPart.class);
    }
}