package miw.ast.expressions.literals;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.AbstractExpression;
import miw.ast.expressions.Expression;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralCharacter extends AbstractExpression implements Expression {
    public char value;

    public LiteralCharacter(Integer line, Integer column, char value) {
        super(line, column);
        this.value = value;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }

}
