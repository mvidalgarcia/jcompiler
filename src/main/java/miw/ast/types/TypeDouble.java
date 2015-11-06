package miw.ast.types;

import miw.ast.AbstractASTNode;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeDouble extends AbstractType implements Type {
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
    public boolean isBasicType() {
        return true;
    }

    @Override
    public boolean promoteTo(Type type)  {
        if (type instanceof TypeDouble || type instanceof TypeError)
            return true;
        return false;
    }

    @Override
    public Type arithmetic(Type type) {
        if (type instanceof TypeDouble || type instanceof TypeError)
            return type;
        if (type instanceof TypeChar || type instanceof TypeInteger)
            return this;
        return null;
    }

    @Override
    public Type arithmetic() {
        return this;
    }

    @Override
    public Type comparison(Type type) {
        if (type instanceof TypeDouble || type instanceof TypeInteger || type instanceof TypeChar)
            return TypeInteger.getInstance(this.getLine(), this.getColumn());
        return null;
    }

    @Override
    public Type assignment(Type type) {
        if (type instanceof TypeError)
            return type;
        if (type instanceof TypeChar || type instanceof TypeInteger || type instanceof TypeDouble)
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

    /* Size of one double in bytes */
    public int size() {
        return 4;
    }

    /* Suffix needed to generate code */
    public String suffix() {
        return "f";
    }

    @Override
    public String toString() {
        return "TypeDouble{}";
    }

    @Override
    public String toStringCG() {
        return "double";
    }

    @Override
    public Type greaterType(Type type) {
        return this;
    }
}
