package miw.ast.types;

import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeChar extends AbstractType implements Type {
    private static TypeChar instance;

    private TypeChar(Integer line, Integer column) {
        super(line, column);
    }

    public static TypeChar getInstance(Integer line, Integer column) {
        if (instance == null) {
            instance = new TypeChar(line, column);
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
    public boolean promoteTo(Type type) {
        if (type instanceof TypeDouble || type instanceof TypeInteger ||
                type instanceof TypeError || type instanceof TypeChar)
            return true;
        return false;
    }

    @Override
    public Type arithmetic(Type type) {
        if (type instanceof TypeDouble || type instanceof TypeInteger || type instanceof TypeError)
            return type;
        if (type instanceof TypeChar)
            return TypeInteger.getInstance(type.getLine(), type.getLine());
        return null;
    }

    @Override
    public Type arithmetic() {
        return TypeInteger.getInstance(this.getLine(), this.getColumn());
    }

    @Override
    public Type comparison(Type type) {
        if (type instanceof TypeDouble || type instanceof TypeInteger || type instanceof TypeChar)
            return TypeInteger.getInstance(this.getLine(), this.getColumn());
        return null;
    }

    @Override
    public Type logic(Type type) {
        if (type instanceof TypeInteger || type instanceof TypeChar)
            return TypeInteger.getInstance(this.getLine(), this.getColumn());
        ;
        return null;
    }

    @Override
    public Type logic() {
        return TypeInteger.getInstance(this.getLine(), this.getColumn());
    }

    @Override
    public Type assignment(Type type) {
        if (type instanceof TypeError)
            return type;
        if (type instanceof TypeChar)
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

    /* Size of one char in bytes */
    public int size() {
        return 1;
    }

    /* Suffix needed to generate code */
    public String suffix() {
        return "b";
    }

    @Override
    public String toString() {
        return "TypeChar{}";
    }

    @Override
    public String toStringCG() {
        return "char";
    }

    @Override
    public Type greaterType(Type type) {
        if (type instanceof  TypeInteger || type instanceof TypeDouble)
            return type;
        return this;
    }
}
