package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Assignment extends AbstractASTNode implements Statement {
    public Expression left_expression;
    public Expression right_expression;

    public Assignment(Integer line, Integer column, Expression left_expression, Expression right_expression) {
        super(line, column);
        this.left_expression = left_expression;
        this.right_expression = right_expression;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "left_expression=" + left_expression +
                ", right_expression=" + right_expression +
                '}';
    }

    public String toStringMod() {
        return left_expression+" = "+right_expression;
    }
}
