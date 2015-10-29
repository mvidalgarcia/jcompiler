package miw.ast.types;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeInteger implements Type {
    private static TypeInteger instance;
    public Integer line, column;
    private TypeInteger(){}

    public TypeInteger getInstance(){
        if (instance == null) {
            instance = new TypeInteger();
        }
        return instance;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }
}
