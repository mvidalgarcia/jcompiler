package miw.ast.types;

import miw.ast.ASTNode;
import miw.ast.AbstractASTNode;
import miw.error.ErrorHandler;

/**
 * Created by mvidalgarcia on 27/10/15.
 */
public class TypeError extends AbstractASTNode implements Type {
    public String description;
    public ASTNode astNode;

    public TypeError(Integer line, Integer column, String description) {
        this.line = line;
        this.column = column;
        this.description = description;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public String toString() {
        return "Lexical error in line " + line +
                ", column " + column +
                ", character \'" + description + "\' unknown.";
    }

}
