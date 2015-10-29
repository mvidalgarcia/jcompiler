package miw.ast.expressions.literals;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralCharacter extends AbstractASTNode implements Expression {
    public char value;

    public LiteralCharacter(Integer line, Integer column, char value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }
}
