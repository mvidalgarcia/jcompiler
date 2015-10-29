package miw.ast.types;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeVoid implements Type {
    private static TypeVoid instance;
    public Integer line, column;
    private TypeVoid(){}

    public TypeVoid getInstance(){
        if (instance == null) {
            instance = new TypeVoid();
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
