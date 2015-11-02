package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class While extends AbstractASTNode implements Statement {
    public List<Statement> whileBody;
    public Expression condition;

    public While(Integer line, Integer column, List<Statement> whileBody, Expression condition) {
        super(line, column);
        this.whileBody = whileBody;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "While{" +
                "whileBody=" + whileBody +
                ", condition=" + condition +
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
