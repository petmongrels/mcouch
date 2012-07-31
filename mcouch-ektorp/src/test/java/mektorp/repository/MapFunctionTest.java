package mektorp.repository;

import org.junit.Assert;
import org.junit.Test;

public class MapFunctionTest {
    @Test
    public void fromGenerateView() {
        Assert.assertEquals("function(doc) {emit(doc.fooBar, doc._id);}", MapFunction.fromGenerateView("findByFooBar"));
    }
}