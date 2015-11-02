package miw.ast.expressions.binary;

import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class ArrayAccess extends AbstractBinaryExpression implements Expression {

    public ArrayAccess(Integer line, Integer column, Expression array, Expression index) {
        super(line, column, array, index, null);
        this.leftExpression = array;
        this.rightExpression = index;

    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "ArrayAccess{" +
                "array=" + leftExpression +
                ", index=" + rightExpression +
                '}';
    }


}
