package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralDouble extends AbstractASTNode implements Expression {
    public Double value;

    public LiteralDouble(Integer line, Integer column, Double value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
