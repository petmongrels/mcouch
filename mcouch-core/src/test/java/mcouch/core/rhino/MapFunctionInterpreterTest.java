package mcouch.core.rhino;

import mcouch.core.couch.indexing.View;
import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapFunctionInterpreterTest {
    private EmitFunction emitFunction;
    private MapFunctionInterpreter mapFunctionInterpreter;

    @Before
    public void setup() {
        emitFunction = new EmitFunction();
        mapFunctionInterpreter = new MapFunctionInterpreter(emitFunction);
    }

    @Test
    public void shouldEvalWithEmitCalls() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo();
        objectWithFoo.setFoo("bar");
        String function = "function(doc){return emit(doc.foo, doc.foo);}";

        View view = new View("whatever", mapFunctionInterpreter, function, emitFunction);
        view.iterate("1", objectWithFoo);
        IndexEntry indexEntry = view.get(new IndexKey("bar"));
        assertNotNull(indexEntry);
    }

    @Test
    public void functionWithExpression() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo();
        objectWithFoo.setType("Baz");
        objectWithFoo.setFoo("bar");

        String mapFunction = "function(doc) {if (doc.type ==='Baz') emit(doc.foo);}";
        View view = new View("whatever", mapFunctionInterpreter, mapFunction, emitFunction);

        mapFunctionInterpreter.interpret(mapFunction, objectWithFoo);
        IndexEntry indexEntry = view.get(new IndexKey("bar"));
        assertNotNull(indexEntry);
        assertEquals("bar", indexEntry.documentIds().get(0));
    }

    public class ObjectWithFoo {
        private String foo;
        private String type;

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFoo() {
            return foo;
        }
    }
}