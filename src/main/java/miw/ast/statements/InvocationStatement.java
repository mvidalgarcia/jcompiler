package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;
import miw.ast.expressions.Identifier;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class InvocationStatement extends AbstractASTNode implements Statement {
    public Identifier function;
    public List<Expression> arguments;

    public InvocationStatement(Integer line, Integer column, Identifier function, List<Expression> arguments) {
        super(line, column);
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "InvocationStatement{" +
                "function=" + function +
                ", arguments=" + arguments +
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
