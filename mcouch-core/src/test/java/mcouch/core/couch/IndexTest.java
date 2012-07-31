package mcouch.core.couch;

import mcouch.core.couch.indexing.Index;
import org.junit.Test;

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
    }
}