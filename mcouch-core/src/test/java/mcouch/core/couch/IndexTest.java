package mcouch.core.couch;

import junit.framework.Assert;
import mcouch.core.couch.indexing.Index;
import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;
import org.junit.Test;

import java.util.NavigableMap;

public class IndexTest {
    @Test
    public void addOrUpdate() throws Exception {
        Index index = new Index("anything", null, null);
        index.addOrUpdate("abc", "1");
        index.addOrUpdate("gsh", "2");
        index.addOrUpdate("jcw", "3");
        index.addOrUpdate("esa", "4");
        index.addOrUpdate("pds", "5");
        index.addOrUpdate("sfs", "6");
        NavigableMap<IndexKey,IndexEntry> map = index.itemsBetween(new IndexKey("abc"), new IndexKey("esa"));
        Assert.assertEquals(2, map.size());
    }
}