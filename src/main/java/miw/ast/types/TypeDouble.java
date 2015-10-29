package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeDouble extends AbstractASTNode implements Type {
    private static TypeDouble instance;
    private TypeDouble(){}

    public TypeDouble getInstance(){
        if (instance == null) {
            instance = new TypeDouble();
        }
        return instance;
    }

}
