package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeVoid extends AbstractASTNode implements Type {
    private static TypeVoid instance;
    private TypeVoid(){}

    public TypeVoid getInstance(){
        if (instance == null) {
            instance = new TypeVoid();
        }
        return instance;
    }

}
