package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeChar extends AbstractASTNode implements Type {
    private static TypeChar instance;

    private TypeChar(Integer line, Integer column){
        super(line, column);
    }

    public TypeChar getInstance(Integer line, Integer column){
        if (instance == null) {
            instance = new TypeChar(line, column);
        }
        return instance;
    }

}
