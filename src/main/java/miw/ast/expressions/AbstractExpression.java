package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 30/10/15.
 */
public abstract class AbstractExpression extends AbstractASTNode implements Expression{
    public boolean lvalue = false;

    public AbstractExpression(Integer line, Integer column) {
        super(line, column);
    }

    public boolean getLvalue() {
        return false;
    }

    public void setLvalue(boolean lvalue) {
        this.lvalue = lvalue;
    }
}
