package miw.ast.types;

import miw.ast.AbstractASTNode;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeInteger extends AbstractType implements Type {
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

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public boolean isLogic() {
        return true;
    }

    @Override
    public boolean isBasicType() {
        return true;
    }

    @Override
    public boolean promoteTo(Type type)  {
        if (type instanceof TypeDouble || type instanceof TypeInteger || type instanceof TypeError)
            return true;
        return false;
    }

    @Override
    public Type arithmetic(Type type) {
        if (type instanceof TypeDouble || type instanceof TypeInteger || type instanceof TypeError)
            return type;
        if (type instanceof TypeChar)
            return this;
        return null;
    }

    @Override
    public Type arithmetic() {
        return this;
    }

    @Override
    public Type comparison(Type type) {
        if (type instanceof TypeError)
            return type;
        if (type instanceof TypeDouble || type instanceof TypeInteger || type instanceof TypeChar)
            return this;
        return null;
    }

    @Override
    public Type logic(Type type) {
        if (type instanceof TypeInteger || type instanceof TypeChar)
            return this;
        return null;
    }

    @Override
    public Type logic() {
        return this;
    }

    @Override
    public Type assignment(Type type) {
        if (type instanceof TypeError)
            return type;
        if (type instanceof TypeInteger || type instanceof TypeChar)
            return this;
        return null;
    }

    @Override
    public Type castTo(Type type) {
        if (type instanceof TypeChar || type instanceof TypeInteger ||
                type instanceof TypeDouble || type instanceof TypeError)
            return type;
        return null;
    }

    public int size() {
        return 2;
    }

    @Override
    public String toString() {
        return "TypeInteger{}";
    }
}
