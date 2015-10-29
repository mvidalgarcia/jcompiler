package miw.ast.types;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeArray implements Type {
    public Integer size;
    public Type type;
    public Integer line, column;

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }
}
