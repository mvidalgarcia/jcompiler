package miw.ast.expressions.literals;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralInteger extends AbstractASTNode implements Expression {
    public Integer value;

    public LiteralInteger(Integer line, Integer column, Integer value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
