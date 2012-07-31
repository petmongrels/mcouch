package mcouch.ektorp.sample;

import org.ektorp.ComplexKey;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;

import java.util.List;

public class SampleRepository {
    private CouchDbConnector couchDbConnector;

    public SampleRepository(CouchDbConnector couchDbConnector) {
        this.couchDbConnector = couchDbConnector;
    }

    @View(name = "find_by_single_field", map = "function(doc) {emit(doc.firstString, doc._id);}")
    public List<Entity> findBySingleField(String fieldValue) {
        ViewQuery q = new ViewQuery().viewName("find_by_single_field").key(fieldValue).includeDocs(true);
        return couchDbConnector.queryView(q, Entity.class);
    }

    @View(name = "find_by_two_fields", map = "function(doc) {emit([doc.firstString, doc.secondString], doc._id);}")
    public List<Entity> findByTwoFields(String firstFieldValue, String secondFieldValue) {
        ComplexKey startKey = ComplexKey.of(firstFieldValue, secondFieldValue);
        ComplexKey endKey = ComplexKey.of(firstFieldValue, secondFieldValue);
        ViewQuery q = new ViewQuery().viewName("find_by_two_fields").startKey(startKey).endKey(endKey).includeDocs(true);
        return couchDbConnector.queryView(q, Entity.class);
    }

    @GenerateView
    public List<Entity> findBySecondString(String secondString) {
        ViewQuery q = new ViewQuery().viewName("by_secondString").key(secondString).includeDocs(true);
        return couchDbConnector.queryView(q, Entity.class);
    }
}