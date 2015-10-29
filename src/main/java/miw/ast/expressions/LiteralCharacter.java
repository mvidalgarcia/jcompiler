package miw.ast.expressions;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralCharacter implements Expression {
    public char value;
    public Integer line, column;

    public LiteralCharacter(Integer line, Integer column, char value) {
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
        return Character.toString(value);
    }
}
