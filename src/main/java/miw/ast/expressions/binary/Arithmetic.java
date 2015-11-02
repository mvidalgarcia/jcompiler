package miw.ast.expressions.binary;

import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Arithmetic extends AbstractBinaryExpression implements Expression {

    public Arithmetic(Integer line, Integer column, Expression leftExpression,
                      String operator, Expression rightExpression) {
        super(line, column, leftExpression, rightExpression, operator);
    }

    @Override
    public String toString() {
        return "Arithmetic{" +
                "leftExpression=" + leftExpression +
                ", operator='" + operator + '\'' +
                ", rightExpression=" + rightExpression+
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
