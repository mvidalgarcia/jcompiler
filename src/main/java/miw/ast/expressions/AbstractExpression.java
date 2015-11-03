package miw.ast.expressions;

import miw.ast.AbstractASTNode;
import miw.ast.types.Type;

/**
 * Created by mvidalgarcia on 30/10/15.
 */
public abstract class AbstractExpression extends AbstractASTNode implements Expression{
    public boolean lvalue = false;
    public Type type;

    public AbstractExpression(Integer line, Integer column) {
        super(line, column);
    }

    public boolean getLvalue() {
        return lvalue;
    }

    public void setLvalue(boolean lvalue) {
        this.lvalue = lvalue;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
