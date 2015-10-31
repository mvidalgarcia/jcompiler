package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Writing extends AbstractASTNode implements Statement {
    public List<Expression> expressions;

    public Writing(Integer line, Integer column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "Writing{" +
                "expressions=" + expressions +
                '}';
    }

    public String toStringMod() {
        String s = "";
        for (Expression expression: expressions){
            s += expression;
            if (!(expressions.indexOf(expression) == expressions.size()-1))
                s += ",";
        }
        return "write " + s;

    }
}
