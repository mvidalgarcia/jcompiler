package miw.ast.types;

import miw.ast.AbstractASTNode;
import miw.ast.statements.definitions.VariableDef;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class TypeFunction extends AbstractASTNode implements Type {
    public Type returnType;
    public List<VariableDef> parameters;

    public TypeFunction(Integer line, Integer column, Type returnType, List<VariableDef> parameters) {
        super(line, column);
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "TypeFunction{" +
                "returnType=" + returnType +
                ", parameters=" + parameters +
                '}';
    }
}