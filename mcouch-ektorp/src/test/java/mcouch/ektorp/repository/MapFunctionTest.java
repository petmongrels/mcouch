package mcouch.ektorp.repository;

import org.junit.Assert;
import org.junit.Test;

public class MapFunctionTest {
    @Test
    public void fromGenerateView() {
        Assert.assertEquals(String.format(MapFunction.TemplateForMapBasedOnSingleField, "fooBar"), MapFunction.fromGenerateView("findByFooBar"));
    }
}