package miw.ast;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Assignment implements Statement {
    public Expression left_expression;
    public Expression right_expression;
    public Integer line, column;

    public Assignment(Integer line, Integer column, Expression left_expression, Expression right_expression) {
        this.left_expression = left_expression;
        this.right_expression = right_expression;
        this.line = line;
        this.column = column;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }
}
