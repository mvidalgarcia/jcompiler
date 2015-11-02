package miw.ast.expressions;

import miw.ast.AbstractASTNode;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class ArrayAccess extends AbstractExpression implements Expression {
    public Expression array;
    public Expression index;


    public ArrayAccess(Integer line, Integer column, Expression array, Expression index) {
        super(line, column);
        this.array = array;
        this.index = index;

    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "ArrayAccess{" +
                "array=" + array +
                ", index=" + index +
                '}';
    }


}
