package miw.ast.types;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeChar extends AbstractASTNode implements Type {
    private static TypeChar instance;
    private TypeChar(){}

    public TypeChar getInstance(){
        if (instance == null) {
            instance = new TypeChar();
        }
        return instance;
    }

}
