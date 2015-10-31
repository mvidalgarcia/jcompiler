package miw.ast.types;

import miw.ast.AbstractASTNode;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeArray extends AbstractASTNode implements Type {
    public Integer size;
    public Type type;

    public TypeArray(Integer line, Integer column, Integer size, Type type) {
        super(line, column);
        this.size = size;
        this.type = type;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "TypeArray{" +
                "size=" + size +
                ", type=" + type +
                '}';
    }
}
