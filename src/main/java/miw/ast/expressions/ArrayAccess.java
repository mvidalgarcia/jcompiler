package miw.ast.expressions;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public class ArrayAccess implements Expression {
    public String name;
    public Expression index;
    public Integer line, column;

    public ArrayAccess(String name, Expression index) {
        this.name = name;
        this.index = index;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }
}
