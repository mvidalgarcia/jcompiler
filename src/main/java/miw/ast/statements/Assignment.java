package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Assignment extends AbstractASTNode implements Statement {
    public Expression leftExpression;
    public Expression rightExpression;

    public Assignment(Integer line, Integer column, Expression left_expression, Expression right_expression) {
        super(line, column);
        this.leftExpression = left_expression;
        this.rightExpression = right_expression;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "left_expression=" + leftExpression +
                ", right_expression=" + rightExpression +
                '}';
    }

    public String toStringMod() {
        return leftExpression+" = "+rightExpression;
    }
}
