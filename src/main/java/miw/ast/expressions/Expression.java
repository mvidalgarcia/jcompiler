package miw.ast.expressions;

import miw.ast.ASTNode;
import miw.ast.AbstractASTNode;
import miw.ast.types.Type;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public interface Expression extends ASTNode {
    String toString();
    boolean getLvalue();
    void setLvalue(boolean lvalue);
    Type getType();
    void setType(Type type);
}
