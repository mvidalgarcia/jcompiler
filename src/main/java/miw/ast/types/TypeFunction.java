package miw.ast.types;

import miw.ast.expressions.Expression;
import miw.ast.statements.definitions.VariableDef;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class TypeFunction extends AbstractType implements Type {
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

    public int parametersSize() {
        int total = 0;
        for (VariableDef vd: this.parameters)
            total += vd.getType().size();
        return total;
    }

    @Override
    public Type functionInvocation(List<Expression> expressions) {
        if (expressions.size() != this.parameters.size())
            return null;
        for (int i=0; i < expressions.size(); i++)
            if (!expressions.get(i).getType().promoteTo(this.parameters.get(i).getType()))
                return null;
        return this.returnType;
    }

    @Override
    public String toString() {
        return "TypeFunction{" +
                "returnType=" + returnType +
                ", parameters=" + parameters +
                '}';
    }
}
