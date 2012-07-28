package mektorp.sample;

import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
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
}