package miw.ast.expressions;

import miw.ast.AbstractASTNode;
import miw.ast.statements.Statement;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class InvocationExpression extends AbstractExpression implements Expression {
    public Identifier function;
    public List<Expression> arguments;

    public InvocationExpression(Integer line, Integer column, Identifier function, List<Expression> arguments) {
        super(line, column);
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "InvocationExpression{" +
                "function=" + function +
                ", arguments=" + arguments +
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
