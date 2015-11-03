package miw.ast.expressions.unary;

import miw.ast.expressions.AbstractExpression;
import miw.ast.expressions.Expression;
import miw.ast.types.Type;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class Cast extends AbstractUnaryExpression implements Expression {
    public Type type;

    public Cast(Integer line, Integer column, Type type, Expression expression) {
        super(line, column, expression);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "type=" + type +
                ", expression=" + expression +
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }
}
