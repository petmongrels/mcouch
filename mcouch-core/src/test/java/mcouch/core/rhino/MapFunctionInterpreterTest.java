package mcouch.core.rhino;

import mcouch.core.couch.indexing.Index;
import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MapFunctionInterpreterTest {
    @Test
    public void shouldEvalWithEmitCalls() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo("bar");
        String function = "function(doc){return emit(doc.foo, doc.foo);}";
        EmitFunction emitFunction = new EmitFunction();
        MapFunctionInterpreter mapFunctionInterpreter = new MapFunctionInterpreter(emitFunction);
        Index index = new Index("whatever", mapFunctionInterpreter, function);
        emitFunction.currentIndex(index);

        mapFunctionInterpreter.interpret(function, objectWithFoo);
        IndexEntry indexEntry = index.get(new IndexKey("bar"));
        assertNotNull(indexEntry);
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