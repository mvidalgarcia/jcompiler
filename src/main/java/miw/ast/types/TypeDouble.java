package miw.ast.types;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeDouble implements Type {
    private static TypeDouble instance;
    public Integer line, column;
    private TypeDouble(){}

    public TypeDouble getInstance(){
        if (instance == null) {
            instance = new TypeDouble();
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
