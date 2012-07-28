package mektorp;

import mektorp.repository.Repository;
import mektorp.rhino.EmitFunction;
import mektorp.rhino.MapFunctionInterpreter;
import mektorp.sample.Entity;
import mektorp.sample.SampleRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class InMemoryCouchTest {
    @Test
    public void findBySingleField() {
        EmitFunction emitFunction = new EmitFunction();
        MapFunctionInterpreter mapFunctionInterpreter = new MapFunctionInterpreter(emitFunction);
        Repository repository = new Repository(emitFunction);
        InMemoryCouch couch = new InMemoryCouch(repository, mapFunctionInterpreter);

        createEntity("1", "abc", couch);
        createEntity("2", "ghs", couch);
        createEntity("3", "fds", couch);
        createEntity("4", "esr", couch);
        createEntity("5", "pgs", couch);
        createEntity("6", "gts", couch);
        createEntity("7", "gts", couch);
        SampleRepository sampleRepository = new SampleRepository(couch);
        Assert.assertEquals(1, sampleRepository.findBySingleField("esr").size());
        Assert.assertEquals(0, sampleRepository.findBySingleField("bgc").size());
        Assert.assertEquals(2, sampleRepository.findBySingleField("gts").size());
    }

    private Entity createEntity(String id, String firstString, InMemoryCouch couch) {
        Entity entity = new Entity();
        entity.set_id(id);
        entity.setFirstString(firstString);

        couch.create(entity.get_id(), entity);
        return entity;
    }
}