package mcouch.core.integration;

import mcouch.testapp.AnotherSampleEntity;
import mcouch.testapp.SampleEntity;
import mcouch.testapp.SampleEntityPart;
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
    private SampleRepository sampleRepository;

    @Before
    public void setup() throws Exception {
        dbConnector = InMemoryCouchDbInstanceFactoryForTest.create("fooDb");
//        dbConnector = CouchDbInstanceFactoryForTest.create("foodb");
        dbConnector.create(new AnotherSampleEntity());
        sampleRepository = new SampleRepository(dbConnector);
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

        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        List<SampleEntity> list = sampleRepository.findByA("anything");
        assertEquals(1, list.size());
    }

    @Test
    public void findDocumentsByComplexKey() {

        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        List<SampleEntity> list = sampleRepository.findByAInRange("anything", "dfd");
        assertEquals(1, list.size());
    }

    @Test
    public void numberOfDocumentsUsingReduceFunction() {
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        int count = sampleRepository.numberOfItemsInRange("anything", "dfd");
        assertEquals(1, count);
    }

    @Test
    public void findByAWithoutReduce() {
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        List<SampleEntity> list = sampleRepository.findByAWithoutReduce("anything");
        assertEquals(1, list.size());
    }

    @Test
    public void getAll() {
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
        SampleEntity anything = new SampleEntity("anything");
        dbConnector.create(anything);
        assertEquals(1, sampleRepository.getAll().size());
        dbConnector.delete(anything);
        assertEquals(0, sampleRepository.getAll().size());
    }

    @Test
    public void findInARange() {
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        assertEquals(1, sampleRepository.findUsingTwoEmittedValues("anything").size());
    }

    @Test
    public void customQueryWhichDoesntReturnADoc() {
        dbConnector.create(new SampleEntity("anything"));
        dbConnector.create(new SampleEntity("something"));
        List<SampleEntityPart> list = sampleRepository.loadCustomDataStructure("anything");
        assertEquals(1, list.size());
    }

    @Test
    public void bulkPost() {
        ArrayList<SampleEntity> sampleEntities = new ArrayList<>();
        sampleEntities.add(new SampleEntity("anything"));
        sampleEntities.add(new SampleEntity("something"));
        dbConnector.executeAllOrNothing(sampleEntities);
        assertEquals(2, sampleRepository.getAll().size());
    }

    @Test
    public void bulkPostShouldUpdateOrAdd() {
        SampleEntity anything = new SampleEntity("anything");
        dbConnector.create(anything);
        assertEquals(1, sampleRepository.getAll().size());

        anything.setA("anythingUpdated");
        ArrayList<SampleEntity> sampleEntities = new ArrayList<>();
        sampleEntities.add(anything);
        dbConnector.executeAllOrNothing(sampleEntities);
        assertEquals(1, sampleRepository.getAll().size());
    }
}