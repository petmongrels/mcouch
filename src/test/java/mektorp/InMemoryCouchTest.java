package mektorp;

import mektorp.repository.Repository;
import mektorp.rhino.MapFunctionInterpreter;
import mektorp.sample.Entity;
import mektorp.sample.SampleRepository;
import mektorp.sample.TestIndexer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCouchTest {
    @Test
    public void foo() {
        InMemoryCouch couch = new InMemoryCouch(new Repository(), new MapFunctionInterpreter(new TestIndexer()));
        List<Entity> entities = new SampleRepository(couch).executeQuery(new ArrayList<String>());
        Assert.assertNotNull(entities);
    }
}