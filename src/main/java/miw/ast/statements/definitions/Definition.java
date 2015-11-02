package miw.ast.statements.definitions;

import miw.ast.ASTNode;
import miw.ast.expressions.Identifier;
import miw.ast.types.Type;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public interface Definition extends ASTNode {
    Type getType();
    String getName();
    Integer getScope();
    void setScope(Integer scope);
}
