package miw.ast.expressions;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Variable implements Expression {
    public String name;
    public Integer line, column;

    public Variable(Integer line, Integer column, String name) {
        this.name = name;
        this.column = column;
        this.line = line;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return name;
    }
}
