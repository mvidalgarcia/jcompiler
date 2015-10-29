package miw.ast.types;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class TypeChar implements Type {
    private static TypeChar instance;
    public Integer line, column;
    private TypeChar(){}

    public TypeChar getInstance(){
        if (instance == null) {
            instance = new TypeChar();
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
