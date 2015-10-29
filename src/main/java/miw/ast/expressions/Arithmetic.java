package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Arithmetic extends AbstractBinaryExpression {

    public Arithmetic(Integer line, Integer column, Expression leftExpression,
                      String operator, Expression rightExpression) {
        super(line, column, leftExpression, rightExpression, operator);
    }

    @Override
    public String toString() {
        return "Arithmetic{" +
                "line=" + line +
                ", column=" + column +
                ", leftExpression=" + leftExpression +
                ", operator='" + operator + '\'' +
                ", rightExpression=" + rightExpression+
                '}';
    }
}
