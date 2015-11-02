package miw.ast.expressions.unary;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.AbstractExpression;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class UnaryMinus extends AbstractUnaryExpression implements Expression {

    public UnaryMinus(Integer line, Integer column, Expression expression) {
        super(line, column, expression);
    }

    @Override
    public String toString() {
        return "UnaryMinus{" +
                "expression=" + expression +
                '}';
    }

    public String toStringMod() {
        return "-"+expression;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
