package miw.ast;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Arithmetic implements Expression {
    public Expression firstExpression;
    public Expression secondExpression;
    public String operator;
    public Integer line, column;

    public Arithmetic(Integer line, Integer column, Expression firstExpression,
                      String operator, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
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
