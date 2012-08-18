package mcouch.core.rhino;

import mcouch.core.TestContext;
import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;
import mcouch.core.couch.indexing.View;
import mcouch.core.jackson.JSONSerializer;
import org.junit.Test;

import java.util.NavigableMap;

import static org.junit.Assert.assertEquals;

public class MapFunctionInterpreterTest {
    private MapFunctionInterpreter mapFunctionInterpreter = TestContext.MAP_FUNCTION_INTERPRETER;

    @Test
    public void shouldEvalWithEmitCalls() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo();
        objectWithFoo.setFoo("bar");
        String function = "function(doc){return emit(doc.foo, doc.foo);}";

        View view = new View("whatever", mapFunctionInterpreter, function);
        mapFunctionInterpreter.emitOn(view);
        view.iterate("1", JSONSerializer.toJson(objectWithFoo));
        NavigableMap<IndexKey, IndexEntry> map = view.get(new IndexKey("bar"));
        assertEquals(1, map.size());
    }

    @Test
    public void functionWithExpression() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo();
        objectWithFoo.setType("Baz");
        objectWithFoo.setFoo("bar");

        String mapFunction = "function(doc) {if (doc.type ==='Baz') emit(doc.foo);}";
        View view = new View("whatever", mapFunctionInterpreter, mapFunction);

        mapFunctionInterpreter.emitOn(view);
        mapFunctionInterpreter.interpret(mapFunction, "1", JSONSerializer.toJson(objectWithFoo));
        NavigableMap<IndexKey, IndexEntry> map = view.get(new IndexKey("bar"));
        assertEquals(1, map.size());
        assertEquals("bar", map.get(new IndexKey("bar")).indexedValues().get(0).getDocId());
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