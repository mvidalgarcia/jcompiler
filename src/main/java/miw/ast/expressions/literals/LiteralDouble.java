package miw.ast.expressions.literals;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.AbstractExpression;
import miw.ast.expressions.Expression;
import miw.ast.types.TypeDouble;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class LiteralDouble extends AbstractExpression implements Expression {
    public Double value;

    public LiteralDouble(Integer line, Integer column, Double value) {
        super(line, column);
        this.value = value;
        this.type = TypeDouble.getInstance(line, column);
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

    @Override
    public String toString() {
        return value.toString();
    }


}
