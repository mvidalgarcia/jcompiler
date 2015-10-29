package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeArray extends AbstractASTNode implements Type {
    public Integer size;
    public Type type;

}
