package miw.ast.statements;

import miw.ast.expressions.Expression;

import java.util.List;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Reading implements Statement {
    public List<Expression> expressions;
    public Integer line, column;

    public Reading(Integer line, Integer column, List<Expression> expressions) {
        this.expressions = expressions;
        this.line = line;
        this.column = column;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
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
