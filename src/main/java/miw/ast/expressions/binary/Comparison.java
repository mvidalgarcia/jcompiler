package miw.ast.expressions.binary;

import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class Comparison extends AbstractBinaryExpression implements Expression {
    public Comparison(Integer line, Integer column, Expression leftExpression,
                      String operator, Expression rightExpression) {
        super(line, column, leftExpression, rightExpression, operator);
    }

    @Override
    public String toString() {
        return "Comparison{" +
                "leftExpression=" + leftExpression +
                ", operator='" + operator + '\'' +
                ", rightExpression=" + rightExpression+
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
