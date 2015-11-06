package miw.ast.types;

import miw.ast.ASTNode;
import miw.ast.expressions.Expression;

import java.util.List;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public interface Type extends ASTNode {
    boolean isLogic();
    boolean isBasicType();
    boolean promoteTo(Type type);
    Type arithmetic(Type type); // Binary
    Type arithmetic(); // Unary
    Type comparison(Type type);
    Type logic(Type type);
    Type logic();
    Type assignment(Type type);
    Type castTo(Type type);
    Type arrayAccess(Type type);
    Type functionInvocation(List<Expression> expressions);
    // Size in bytes of a certain type
    int size();
    // Suffix for code generation
    String suffix();
    String toStringCG();
    Type greaterType(Type type);

}
