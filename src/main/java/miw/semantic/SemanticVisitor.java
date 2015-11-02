package miw.semantic;

import miw.ast.expressions.ArrayAccess;
import miw.ast.expressions.Expression;
import miw.ast.expressions.Identifier;
import miw.ast.statements.Assignment;
import miw.ast.statements.Reading;
import miw.ast.types.TypeError;
import miw.visitor.AbstractVisitor;

/**
 * Created by mvidalgarcia on 31/10/15.
 */
public class SemanticVisitor extends AbstractVisitor {
    

    /* Expressions */
    /* Missing classes inherit from AbstractExpression and have lvalue=false */
    @Override
    public Object visit(Identifier identifier, Object params) {
        super.visit(identifier, params);
        identifier.setLvalue(true);
        return null;
    }

    @Override
    public Object visit(ArrayAccess arrayAccess, Object params) {
        super.visit(arrayAccess, params);
        arrayAccess.setLvalue(true);
        return null;
    }


    /* Statements */
    @Override
    public Object visit(Assignment assignment, Object params) {
        super.visit(assignment, params);
        if (!assignment.leftExpression.getLvalue()) {
            new TypeError("Semantic error: Lvalue expected.", assignment);
        }
        return null;
    }

    @Override
    public Object visit(Reading reading, Object params) {
        super.visit(reading, params);
        for (Expression expression: reading.expressions) {
            if (!expression.getLvalue())
                new TypeError("Semantic error: Lvalue expected.", reading);
        }
        return null;
    }
}
