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

    @View(name = "find_by_provider_having_active_treatment_v1", map = "function(doc) {if (doc.type ==='Patient' && doc.currentTherapy.currentTreatment && doc.onActiveTreatment === true) {emit(doc.currentTherapy.currentTreatment.providerId, doc._id);}}")
    public List<Entity> executeQuery(List<String> ids) {
        ViewQuery q = new ViewQuery().viewName("find_by_provider_having_active_treatment_v1").keys(ids).includeDocs(true);
        return couchDbConnector.queryView(q, Entity.class);
    }
}