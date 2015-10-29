package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeVoid extends AbstractASTNode implements Type {
    private static TypeVoid instance;
    private TypeVoid(Integer line, Integer column){
        super(line, column);
    }

    public TypeVoid getInstance(Integer line, Integer column){
        if (instance == null) {
            instance = new TypeVoid(line, column);
        }
        return instance;
    }

}
