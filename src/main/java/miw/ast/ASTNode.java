package miw.ast;

import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public interface ASTNode {
    Integer getLine();
    Integer getColumn();
    Object accept(Visitor visitor, Object params);
}
