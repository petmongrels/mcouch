package mektorp;

import mektorp.repository.Repository;
import mektorp.rhino.EmitFunction;
import mektorp.rhino.MapFunctionInterpreter;
import mektorp.sample.Entity;
import mektorp.sample.SampleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryCouchTest {
    private SampleRepository sampleRepository;

    @Before
    public void setupData() {
        EmitFunction emitFunction = new EmitFunction();
        MapFunctionInterpreter mapFunctionInterpreter = new MapFunctionInterpreter(emitFunction);
        Repository repository = new Repository(emitFunction);
        InMemoryCouch couch = new InMemoryCouch(repository, mapFunctionInterpreter);

        createEntity("1", "abc", "sd52", couch);
        createEntity("2", "ghs", "76hd", couch);
        createEntity("3", "fds", "8hd", couch);
        createEntity("4", "esr", "hgf", couch);
        createEntity("5", "pgs", "43b", couch);
        createEntity("6", "gts", "sas", couch);
        createEntity("7", "gts", "sas", couch);

        sampleRepository = new SampleRepository(couch);
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

    private Entity createEntity(String id, String firstString, String secondString, InMemoryCouch couch) {
        Entity entity = new Entity();
        entity.set_id(id);
        entity.setFirstString(firstString);
        entity.setSecondString(secondString);

        couch.create(entity.get_id(), entity);
        return entity;
    }
}