package mcouch.ektorp;

import java.util.StringTokenizer;

public class TypeDiscriminatorExpression {
    private String expression;

    public TypeDiscriminatorExpression(String expression) {
        this.expression = expression;
    }

    public String typeName() {
        StringTokenizer tokenizer = new StringTokenizer(expression, "'");
        tokenizer.nextToken();
        return tokenizer.nextToken().trim();
    }
}