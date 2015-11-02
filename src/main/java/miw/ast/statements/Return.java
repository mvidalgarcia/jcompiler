package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class Return extends AbstractASTNode implements Statement {
    public Expression expression;

    public Return(Integer line, Integer column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Return{" +
                "expression=" + expression +
                '}';
    }


    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
