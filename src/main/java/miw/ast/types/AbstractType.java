package miw.ast.types;

import miw.ast.AbstractASTNode;
import miw.ast.expressions.Expression;

import java.util.List;

/**
 * Created by mvidalgarcia on 3/11/15.
 */
public abstract class AbstractType extends AbstractASTNode implements Type {
    public AbstractType(Integer line, Integer column) {
        super(line, column);
    }

    public boolean isLogic() {
        return false;
    }

    public boolean isBasicType() {
        return false;
    }

    public boolean promoteTo(Type type)  {
        return false;
    }

    public Type arithmetic(Type type) {
        return null;
    }

    public Type arithmetic() {
        return null;
    }

    public Type comparison(Type type) {
        return null;
    }

    public Type logic(Type type) {
        return null;
    }

    public Type logic() {
        return null;
    }

    public Type assignment(Type type) {
        return null;
    }

    public Type castTo(Type type) {
        return null;
    }

    public Type arrayAccess(Type type) {
        return null;
    }

    public Type functionInvocation(List<Expression> expressions) {
        return null;
    }

    public int size() {
        return 0;
    }

    public String suffix() {
        return null;
    }

    public String toStringCG() {
        return null;
    }
}
