package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class While extends AbstractASTNode implements Statement {
    public Expression condition;
    public List<Statement> whileBody;

    public While(Integer line, Integer column, Expression condition, List<Statement> whileBody) {
        super(line, column);
        this.whileBody = whileBody;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "While{" +
                "condition=" + condition +
                ", whileBody=" + whileBody +
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
