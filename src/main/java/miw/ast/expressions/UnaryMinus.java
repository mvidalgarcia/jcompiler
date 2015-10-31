package miw.ast.expressions;

import miw.ast.AbstractASTNode;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class UnaryMinus extends AbstractASTNode implements Expression {

    public Expression expression;

    public UnaryMinus(Integer line, Integer column, Expression expression) {
        super(line, column);
        this.expression = expression;
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
