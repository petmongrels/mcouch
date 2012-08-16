package mcouch.core.integration;

import mcouch.testapp.AnotherSampleEntity;
import mcouch.testapp.SampleEntity;
import mcouch.testapp.SampleRepository;
import org.ektorp.BulkDeleteDocument;
import org.ektorp.impl.StdCouchDbConnector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class DocumentLevelAPITest {
    private StdCouchDbConnector dbConnector;

    @Before
    public void setup() throws Exception {
        dbConnector = InMemoryCouchDbInstanceFactoryForTest.create("fooDb");
//        dbConnector = CouchDbInstanceFactoryForTest.create("foodb");
        dbConnector.create(new AnotherSampleEntity());
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

    @Test
    public void findByAWithoutReduce() {
        SampleRepository sampleRepository = new SampleRepository(dbConnector);
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        List<SampleEntity> list = sampleRepository.findByAWithoutReduce("anything");
        assertEquals(1, list.size());
    }

    @Test
    public void getAll() {
        SampleRepository sampleRepository = new SampleRepository(dbConnector);
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        List<SampleEntity> list = sampleRepository.getAll();
        assertEquals(2, list.size());
    }

    @Test
    public void deleteAll() {
        SampleEntity anything = new SampleEntity("anything");
        dbConnector.create(anything);
        SampleEntity something = new SampleEntity("something");
        dbConnector.create(something);
        ArrayList<BulkDeleteDocument> toDelete = new ArrayList<>();
        toDelete.add(new BulkDeleteDocument(anything.getId(), anything.getRevision()));
        toDelete.add(new BulkDeleteDocument(something.getId(), something.getRevision()));
        dbConnector.executeBulk(toDelete);
    }

    @Test
    public void delete() {
        SampleRepository sampleRepository = new SampleRepository(dbConnector);
        SampleEntity anything = new SampleEntity("anything");
        dbConnector.create(anything);
        assertEquals(1, sampleRepository.getAll().size());
        dbConnector.delete(anything);
        assertEquals(0, sampleRepository.getAll().size());
    }
}