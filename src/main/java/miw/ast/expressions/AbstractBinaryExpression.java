package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public abstract class AbstractBinaryExpression extends AbstractASTNode implements Expression {
    public Expression leftExpression;
    public Expression rightExpression;
    public String operator;

    public AbstractBinaryExpression(Integer line, Integer column, Expression leftExpression,
                                    Expression rightExpression, String operator) {
        super(line, column);
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }
}
