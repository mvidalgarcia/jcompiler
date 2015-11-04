package miw.ast.statements.definitions;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Identifier;
import miw.ast.statements.Statement;
import miw.ast.types.Type;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class VariableDef extends AbstractASTNode implements Statement, Definition {
    public Type type;
    public String name;
    public int offset;
    public int scope;

    public VariableDef(Integer line, Integer column, Type type, String name) {
        super(line, column);
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "VariableDef{" +
                "type=" + type +
                ", name=" + name +
                '}';
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
