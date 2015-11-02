package miw.ast.statements.definitions;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Identifier;
import miw.ast.statements.Statement;
import miw.ast.types.Type;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class FunctionDef extends AbstractASTNode implements Definition {
    public List<Statement> statements;
    public Type type;
    public Identifier name;

    public FunctionDef(Integer line, Integer column, Type type, Identifier name, List<Statement> statements) {
        super(line, column);
        this.type = type;
        this.name = name;
        this.statements = statements;
    }

    public Type getType() {
        return type;
    }

    public Identifier getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FunctionDef{" +
                "statements=" + statements +
                ", type=" + type +
                ", name=" + name +
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
