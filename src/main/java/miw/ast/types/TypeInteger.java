package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeInteger extends AbstractASTNode implements Type {
    private static TypeInteger instance;
    private TypeInteger(){}

    public TypeInteger getInstance(){
        if (instance == null) {
            instance = new TypeInteger();
        }
        return instance;
    }

}
