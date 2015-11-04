package miw.ast.types;

import miw.ast.AbstractASTNode;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeArray extends AbstractType implements Type {
    public Integer size;
    public Type type;

    public TypeArray(Integer line, Integer column, Integer size, Type type) {
        super(line, column);
        this.size = size;
        this.type = type;
    }
    // TODO: Invert array dimensions
//    public static TypeArray newArray(Type type, Integer size) {
//
//    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public Type arrayAccess(Type type) {
        if (type instanceof TypeError)
            return type;
        if (type instanceof TypeInteger || type instanceof TypeChar)
            return this.type;
        return null;
    }

    public int size() {
        return this.type.size() * this.size;
    }

    @Override
    public String toString() {
        return "TypeArray{" +
                "size=" + size +
                ", type=" + type +
                '}';
    }
}
