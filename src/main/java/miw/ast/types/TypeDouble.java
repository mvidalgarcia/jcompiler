package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeDouble extends AbstractASTNode implements Type {
    private static TypeDouble instance;
    private TypeDouble(Integer line, Integer column){
        super(line, column);
    }

    public TypeDouble getInstance(Integer line, Integer column){
        if (instance == null) {
            instance = new TypeDouble(line, column);
        }
        return instance;
    }

}
