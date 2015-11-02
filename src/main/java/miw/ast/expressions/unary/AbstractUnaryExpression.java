package miw.ast.expressions.unary;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.AbstractExpression;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public abstract class AbstractUnaryExpression extends AbstractExpression implements Expression {
    public Expression expression;

    public AbstractUnaryExpression(Integer line, Integer column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }
}
