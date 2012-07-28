package mektorp.rhino;

import mektorp.couch.Index;
import mektorp.couch.IndexKey;
import org.ektorp.ViewQuery;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
        List<String> list = index.list(new ViewQuery().key("bar"));
        assertEquals(1, list.size());
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