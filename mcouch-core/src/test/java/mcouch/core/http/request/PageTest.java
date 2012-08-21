package mcouch.core.http.request;

import org.junit.Assert;
import org.junit.Test;

public class PageTest {
    @Test
    public void fallsOutOf() {
        Assert.assertEquals(false, new Page(0, 1).fallsOutOf(0));
        Assert.assertEquals(true, new Page(0, 1).fallsOutOf(1));

        Assert.assertEquals(true, new Page(2, 3).fallsOutOf(5));
        Assert.assertEquals(false, new Page(2, 3).fallsOutOf(6));
        Assert.assertEquals(false, new Page(2, 3).fallsOutOf(7));
        Assert.assertEquals(false, new Page(2, 3).fallsOutOf(8));
        Assert.assertEquals(true, new Page(2, 3).fallsOutOf(9));
    }
}