package miw.ast.types;

import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeVoid extends AbstractType implements Type {
    private static TypeVoid instance;
    private TypeVoid(Integer line, Integer column){
        super(line, column);
    }

    public static TypeVoid getInstance(Integer line, Integer column){
        if (instance == null) {
            instance = new TypeVoid(line, column);
        }
        return instance;
    }

    @Override
    public boolean isBasicType() {
        return true;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "TypeVoid{}";
    }

    /* Size of one integer in bytes */
    public int size() {
        return 0;
    }
}
