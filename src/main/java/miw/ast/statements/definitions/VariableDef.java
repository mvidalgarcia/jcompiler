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
    public Identifier name;

    public VariableDef(Integer line, Integer column, Type type, Identifier name) {
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

    public Identifier getName() {
        return name;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
