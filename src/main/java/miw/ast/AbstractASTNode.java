package miw.ast;

/**
 * Created by mvidalgarcia on 29/10/15.
 */
public abstract class AbstractASTNode implements ASTNode{
    public Integer line, column;


    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }
}
