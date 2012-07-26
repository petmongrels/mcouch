package mektorp;

import mektorp.repository.Repositories;
import mektorp.sample.Entity;
import mektorp.sample.SampleRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCouchTest {
    @Test
    public void foo() {
        InMemoryCouch couch = new InMemoryCouch(new Repositories());
        List<Entity> entities = new SampleRepository(couch).executeQuery(new ArrayList<String>());
        Assert.assertNotNull(entities);
    }
}