package mcouch.ektorp;

import mcouch.ektorp.sample.Entity;
import mcouch.ektorp.sample.SampleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MCouchDbConnectorTest {
    private SampleRepository sampleRepository;

    @Before
    public void setupData() {
        MCouchDbConnector couchDbConnector = MCouchDbConnector.create();

        createEntity("1", "abc", "sd52", couchDbConnector);
        createEntity("2", "ghs", "76hd", couchDbConnector);
        createEntity("3", "fds", "8hd", couchDbConnector);
        createEntity("4", "esr", "hgf", couchDbConnector);
        createEntity("5", "pgs", "43b", couchDbConnector);
        createEntity("6", "gts", "sas", couchDbConnector);
        createEntity("7", "gts", "sas", couchDbConnector);

        sampleRepository = new SampleRepository(couchDbConnector);
    }

    @Test
    public void findBySingleField() {
        Assert.assertEquals(1, sampleRepository.findBySingleField("esr").size());
        Assert.assertEquals(0, sampleRepository.findBySingleField("bgc").size());
        Assert.assertEquals(2, sampleRepository.findBySingleField("gts").size());
    }

    @Test
    public void findByTwoFields() {
        Assert.assertEquals(1, sampleRepository.findByTwoFields("esr", "hgf").size());
        Assert.assertEquals(0, sampleRepository.findByTwoFields("esr", "esr").size());
        Assert.assertEquals(2, sampleRepository.findByTwoFields("gts", "sas").size());
    }

    @Test
    public void findWhenViewIsGenerated() {
        Assert.assertEquals(1, sampleRepository.findBySecondString("8hd").size());
    }

    private Entity createEntity(String id, String firstString, String secondString, MCouchDbConnector couchDbConnector) {
        Entity entity = new Entity();
        entity.setId(id);
        entity.setFirstString(firstString);
        entity.setSecondString(secondString);

        couchDbConnector.create(entity.getId(), entity);
        return entity;
    }
}