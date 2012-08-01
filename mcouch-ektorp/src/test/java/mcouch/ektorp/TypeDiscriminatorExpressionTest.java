package mcouch.ektorp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TypeDiscriminatorExpressionTest {
    @Test
    public void typeName() {
        TypeDiscriminatorExpression expression = new TypeDiscriminatorExpression("doc.type == 'Patient'");
        assertEquals("Patient", expression.typeName());
    }
}