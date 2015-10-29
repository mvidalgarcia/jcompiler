package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralInteger extends AbstractASTNode implements Expression {
    public Integer value;

    public LiteralInteger(Integer line, Integer column, Integer value) {
        this.value = value;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
