package mektorp.repository;

import mektorp.sample.SampleRepository;
import org.junit.Assert;
import org.junit.Test;

public class RepositoryMethodTest {
    @Test
    public void mapFunction() throws Exception {
        RepositoryMethod method = new RepositoryMethod(SampleRepository.class.getName(), "executeQuery");
        String mapFunction = method.mapFunction();
        Assert.assertNotNull(mapFunction);
        Assert.assertTrue(mapFunction.startsWith("function(doc)"));
    }
}