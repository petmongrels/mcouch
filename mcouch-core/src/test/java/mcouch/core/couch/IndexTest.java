package mcouch.core.couch;

import junit.framework.Assert;
import mcouch.core.TestContext;
import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;
import mcouch.core.couch.indexing.View;
import org.junit.Test;

import java.util.NavigableMap;

public class IndexTest {
    @Test
    public void addOrUpdate() throws Exception {
        View view = new View("anything", TestContext.MAP_FUNCTION_INTERPRETER, null);
        view.addOrUpdate("abc", "1", "1");
        view.addOrUpdate("gsh", "2", "2");
        view.addOrUpdate("jcw", "3", "3");
        view.addOrUpdate("esa", "4", "4");
        view.addOrUpdate("pds", "5", "5");
        view.addOrUpdate("sfs", "6", "6");
        NavigableMap<IndexKey,IndexEntry> map = view.itemsBetween(new IndexKey("abc"), new IndexKey("esa"));
        Assert.assertEquals(2, map.size());
    }
}