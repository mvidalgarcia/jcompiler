package miw.ast.expressions.unary;

import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class Negation extends AbstractUnaryExpression implements Expression {
    public Negation(Integer line, Integer column, Expression expression) {
        super(line, column, expression);
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "UnaryMinus{" +
                "expression=" + expression +
                '}';
    }
}
