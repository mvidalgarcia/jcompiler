package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class ArrayAccess extends AbstractASTNode implements Expression {
    public String name;
    public Expression index;

    public ArrayAccess(String name, Expression index) {
        this.name = name;
        this.index = index;
    }

}
