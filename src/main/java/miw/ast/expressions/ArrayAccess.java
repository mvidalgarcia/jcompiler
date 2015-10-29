package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class ArrayAccess extends AbstractASTNode implements Expression {
    public Expression array;
    public Expression index;

    public ArrayAccess(Integer line, Integer column, Expression array, Expression index) {
        super(line, column);
        this.array = array;
        this.index = index;

    }
}
