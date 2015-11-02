package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class If extends AbstractASTNode implements Statement {
    public List<Statement> ifBody;
    public List<Statement> elseBody;
    public Expression condition;

    public If(Integer line, Integer column, Expression condition,
              List<Statement> ifBody, List<Statement> elseBody) {
        super(line, column);
        this.ifBody = ifBody;
        this.elseBody = elseBody;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "If{" +
                "condition=" + condition +
                ", ifBody=" + ifBody +
                ", elseBody=" + elseBody +
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
