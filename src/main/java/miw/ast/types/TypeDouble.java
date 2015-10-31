package miw.ast.types;

import miw.ast.AbstractASTNode;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeDouble extends AbstractASTNode implements Type {
    private static TypeDouble instance;
    private TypeDouble(Integer line, Integer column){
        super(line, column);
    }

    public static TypeDouble getInstance(Integer line, Integer column){
        if (instance == null) {
            instance = new TypeDouble(line, column);
        }
        return instance;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "TypeDouble{}";
    }
}
