package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeInteger extends AbstractASTNode implements Type {
    private static TypeInteger instance;
    private TypeInteger(Integer line, Integer column){
        super(line, column);
    }

    public static TypeInteger getInstance(Integer line, Integer column){
        if (instance == null) {
            instance = new TypeInteger(line, column);
        }
        return instance;
    }

    @Override
    public String toString() {
        return "TypeInteger{}";
    }
}
