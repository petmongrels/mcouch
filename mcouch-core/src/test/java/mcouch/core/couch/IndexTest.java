package mcouch.core.couch;

import junit.framework.Assert;
import mcouch.core.couch.indexing.Index;
import mcouch.core.couch.indexing.IndexKey;
import mcouch.core.rhino.EmitFunction;
import mcouch.core.rhino.MapFunctionInterpreter;
import org.junit.Test;

import java.util.List;

public class IndexTest {
    @Test
    public void addOrUpdate() throws Exception {
        EmitFunction emitFunction = new EmitFunction();
        Index index = new Index("anything", new MapFunctionInterpreter(emitFunction), null, emitFunction);
        index.addOrUpdate("abc", "1");
        index.addOrUpdate("gsh", "2");
        index.addOrUpdate("jcw", "3");
        index.addOrUpdate("esa", "4");
        index.addOrUpdate("pds", "5");
        index.addOrUpdate("sfs", "6");
        List<String> docIds = index.itemsBetween(new IndexKey("abc"), new IndexKey("esa"));
        Assert.assertEquals(2, docIds.size());
    }
}