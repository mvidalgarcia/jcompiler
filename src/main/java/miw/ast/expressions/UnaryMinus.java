package miw.ast.expressions;

import miw.ast.AbstractASTNode;

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
        return "-"+expression;
    }
}
