package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Arithmetic extends AbstractASTNode implements Expression {
    public Expression firstExpression;
    public Expression secondExpression;
    public String operator;

    public Arithmetic(Integer line, Integer column, Expression firstExpression,
                      String operator, Expression secondExpression) {
        super(line, column);
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "("+firstExpression+operator+secondExpression+")";
    }
}
