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
        this.expressions = expressions;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        String s = "";
        for (Expression expression: expressions){
            s += expression;
            if (!(expressions.indexOf(expression) == expressions.size()-1))
                    s += ",";
        }
        return "read " + s;
    }
}
