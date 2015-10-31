package miw.ast.types;

import miw.ast.ASTNode;
import miw.ast.AbstractASTNode;
import miw.error.ErrorHandler;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 27/10/15.
 */
public class TypeError extends AbstractASTNode implements Type {
    public String description;
    public ASTNode astNode;

    public TypeError(Integer line, Integer column, String description) {
        super(line, column);
        this.description = description;
        ErrorHandler.getInstance().addError(this);
    }

    public TypeError(String description, ASTNode astNode) {
        this.description = description;
        this.astNode = astNode;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return "Lexical error in line " + line +
                ", column " + column +
                ", character \'" + description + "\' unknown.";
    }

}
