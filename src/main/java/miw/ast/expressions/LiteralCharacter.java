package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralCharacter extends AbstractASTNode implements Expression {
    public char value;

    public LiteralCharacter(Integer line, Integer column, char value) {
        this.value = value;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }
}
