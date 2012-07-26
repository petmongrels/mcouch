package mektorp.rhino;

import mektorp.sample.TestIndexer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapFunctionInterpreterTest {
    @Test
    public void shouldEvalWithEmitCalls() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo("bar");
        String function = "function emit(one, two){return indexer.index(one, two);} (function(doc){return emit(doc.foo, doc.foo);})";
        TestIndexer indexer = new TestIndexer();
        new MapFunctionInterpreter(indexer).interpret(function, objectWithFoo);
        assertEquals(objectWithFoo.getFoo(), indexer.indexEntry().getDocId());
        assertEquals(objectWithFoo.getFoo(), indexer.indexEntry().getValue());
    }

    public class ObjectWithFoo {
        private String foo;

        ObjectWithFoo(String foo) {
            this.foo = foo;
        }

        public String getFoo() {
            return foo;
        }
    }
}