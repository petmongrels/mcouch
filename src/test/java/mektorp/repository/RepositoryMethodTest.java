package mektorp.repository;

import mektorp.sample.SampleRepository;
import org.junit.Assert;
import org.junit.Test;

public class RepositoryMethodTest {
    @Test
    public void mapFunction() throws Exception {
        RepositoryMethod repositoryMethod = RepositoryMethod.withMapFunction(new RepositoryMethod(SampleRepository.class.getName(), "executeQuery"));
        Assert.assertNotNull(repositoryMethod);
        Assert.assertTrue(repositoryMethod.mapFunction().startsWith("function(doc)"));
    }
}