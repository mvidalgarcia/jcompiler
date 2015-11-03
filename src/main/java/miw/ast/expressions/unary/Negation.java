package miw.ast.expressions.unary;

import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Negation extends AbstractUnaryExpression implements Expression {

    public Negation(Integer line, Integer column, Expression expression) {
        super(line, column, expression);
    }

    @Override
    public String toString() {
        return "Negation{" +
                "expression=" + expression +
                '}';
    }

    public String toStringMod() {
        return "!"+expression;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
