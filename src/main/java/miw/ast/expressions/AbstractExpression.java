package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 30/10/15.
 */
public abstract class AbstractExpression extends AbstractASTNode {
    boolean getLvalue() {
        return false;
    }

    void setLvalue(boolean lvalue) {

    }
}
