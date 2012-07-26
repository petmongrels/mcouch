package mektorp.rhino;

import org.junit.Test;
import org.mozilla.javascript.NativeJavaObject;

import static org.junit.Assert.assertEquals;

public class JavaScriptEvaluatorTest {
    @Test
    public void shouldEval() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo("bar");
        Object eval = new JavaScriptEvaluator().evalMap("function(doc){return doc.foo;}", null, objectWithFoo);
        assertEquals(objectWithFoo.getFoo(), eval.toString());
    }

    @Test
    public void shouldEvalWithEmitCalls() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo("bar");
        String function = "function emit(one, two){return emitHolder.baz(one, two);} (function(doc){return emit(doc.foo, doc.foo);})";
        NativeJavaObject eval = (NativeJavaObject) new JavaScriptEvaluator().evalMap(function, objectWithFoo, objectWithFoo);
        assertEquals(objectWithFoo.getFoo() + objectWithFoo.getFoo(), eval.getDefaultValue(String.class).toString());
    }

    public class ObjectWithFoo {
        private String foo;

        ObjectWithFoo(String foo) {
            this.foo = foo;
        }

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String baz(String one, String two) {
            return one + two;
        }
    }
}