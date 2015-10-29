package miw.ast.expressions;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralDouble implements Expression {
    public Double value;
    public Integer line, column;

    public LiteralDouble(Integer line, Integer column, Double value) {
        this.value = value;
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
        return value.toString();
    }
}
