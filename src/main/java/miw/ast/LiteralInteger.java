package miw.ast;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralInteger implements Expression {
    public Integer value;
    public Integer line, column;

    public LiteralInteger(Integer line, Integer column, Integer value) {
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
}
