package mcouch.core.integration;

import mcouch.testapp.SampleEntity;
import mcouch.testapp.SampleRepository;
import org.ektorp.impl.StdCouchDbConnector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class DocumentLevelAPITest {
    private StdCouchDbConnector dbConnector;

    @Before
    public void setup() throws Exception {
        dbConnector = InMemoryCouchDbInstanceFactoryForTest.create("fooDb");
//        dbConnector = CouchDbInstanceFactoryForTest.create("foodb");
    }

    @Test
    public void updateADocument() {
        SampleEntity entity = new SampleEntity("anything");
        dbConnector.create(entity);
        String revision = entity.getRevision();
        entity.setA("something");
        dbConnector.update(entity);
        Assert.assertFalse(revision.equals(entity.getRevision()));
        revision = entity.getRevision();
        dbConnector.update(entity);
        Assert.assertFalse(revision.equals(entity.getRevision()));
    }

    @Test
    public void findDocuments() {
        SampleRepository sampleRepository = new SampleRepository(dbConnector);
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        List<SampleEntity> list = sampleRepository.findByA("anything");
        assertEquals(1, list.size());
    }

    @Test
    public void findDocumentsByComplexKey() {
        SampleRepository sampleRepository = new SampleRepository(dbConnector);
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        List<SampleEntity> list = sampleRepository.findByAInRange("anything", "dfd");
        assertEquals(1, list.size());
    }

    @Test
    public void numberOfDocumentsUsingReduceFunction() {
        SampleRepository sampleRepository = new SampleRepository(dbConnector);
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        int count = sampleRepository.numberOfItemsInRange("anything", "dfd");
        assertEquals(1, count);
    }
}