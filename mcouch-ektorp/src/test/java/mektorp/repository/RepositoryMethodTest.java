package mektorp.repository;

import mektorp.rhino.EmitFunction;
import mektorp.sample.SampleRepository;
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