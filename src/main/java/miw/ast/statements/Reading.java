package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;

import java.util.List;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Reading extends AbstractASTNode implements Statement {
    public List<Expression> expressions;

    public Reading(Integer line, Integer column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        return "Reading{" +
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
        return "read " + s;
    }
}
