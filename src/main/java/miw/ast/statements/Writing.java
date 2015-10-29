package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;

import java.util.List;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Writing extends AbstractASTNode implements Statement {
    public List<Expression> expressions;

    public Writing(Integer line, Integer column, List<Expression> expressions) {
        this.expressions = expressions;
        this.column = column;
        this.line = line;
    }

    @Override
    public String toString() {
        String s = "";
        for (Expression expression: expressions){
            s += expression;
            if (!(expressions.indexOf(expression) == expressions.size()-1))
                s += ",";
        }
        return "write " + s;

    }
}
