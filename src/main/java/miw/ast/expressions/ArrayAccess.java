package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class ArrayAccess extends AbstractASTNode implements Expression {
    public Variable name;
    public Expression index;

    public ArrayAccess(Integer line, Integer column, Variable name, Expression index) {
        super(line, column);
        this.name = name;
        this.index = index;

    }
}
