package miw.ast.expressions;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class UnaryMinus implements Expression {

    public Expression expression;
    public Integer line, column;

    public UnaryMinus(Integer line, Integer column, Expression expression) {
        this.expression = expression;
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
        return "-"+expression;
    }
}
