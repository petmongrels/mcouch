package mcouch.ektorp.repository;

import mcouch.core.rhino.EmitFunction;
import mcouch.ektorp.sample.SampleRepository;
import org.junit.Assert;
import org.junit.Test;

public class RepositoryMethodTest {
    @Test
    public void mapFunction() throws Exception {
        EmitFunction emitFunction = new EmitFunction();
        RepositoryMethod repositoryMethod = RepositoryMethod.withMapFunction(new RepositoryMethod(SampleRepository.class.getName(), "findBySingleField"), emitFunction);
        Assert.assertNotNull(repositoryMethod);
        Assert.assertTrue(repositoryMethod.mapFunction().startsWith("function(doc)"));
    }
}