package mektorp.rhino;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaScriptEvaluatorTest {
    @Test
    public void shouldEval() {
        ObjectWithFoo objectWithFoo = new ObjectWithFoo("bar");
        Object eval = new JavaScriptEvaluator().evalMap("function(doc){return doc.foo;}", objectWithFoo);
        assertEquals(objectWithFoo.getFoo(), eval.toString());
    }

    @Test
    public void shouldEvalWithEmitCalls() {

    }

    class ObjectWithFoo {
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
    }
}