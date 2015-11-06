package miw.semantic;

import miw.ast.expressions.InvocationExpression;
import miw.ast.expressions.binary.Arithmetic;
import miw.ast.expressions.binary.ArrayAccess;
import miw.ast.expressions.Expression;
import miw.ast.expressions.Identifier;
import miw.ast.expressions.binary.Comparison;
import miw.ast.expressions.binary.Logic;
import miw.ast.expressions.unary.Cast;
import miw.ast.expressions.unary.Negation;
import miw.ast.expressions.unary.UnaryMinus;
import miw.ast.statements.*;
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.Type;
import miw.ast.types.TypeError;
import miw.ast.types.TypeFunction;
import miw.ast.types.TypeInteger;
import miw.visitor.AbstractVisitor;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
        if (identifier.definition != null)
            identifier.setType(identifier.definition.getType());
        return null;
    }

    @Override
    public Object visit(InvocationExpression invocationExpression, Object params) {
        super.visit(invocationExpression, params);
        if (invocationExpression.function.getType() != null)
            invocationExpression.setType(invocationExpression.function.getType().functionInvocation(invocationExpression.arguments));
        if (invocationExpression.getType() == null)
            invocationExpression.setType(new TypeError("Function \'" + invocationExpression.function.name +
                    "\' cannot be invoked, parameters do not match.", invocationExpression));
        return null;
    }

    /* Expressions -> Binary */
    @Override
    public Object visit(ArrayAccess arrayAccess, Object params) {
        super.visit(arrayAccess, params);
        arrayAccess.setLvalue(true);
        // To Avoid NullPointerException
        if (arrayAccess.leftExpression.getType() != null && arrayAccess.rightExpression.getType() != null)
            arrayAccess.setType(arrayAccess.leftExpression.getType().arrayAccess(arrayAccess.rightExpression.getType()));
        if (arrayAccess.getType() == null)
            arrayAccess.setType(new TypeError("Invalid array access, " +
                    "index cannot be \'"+arrayAccess.rightExpression+"\'", arrayAccess));
        return null;
    }

    @Override
    public Object visit(Arithmetic arithmetic, Object params) {
        super.visit(arithmetic, params);
        // To Avoid NullPointerException
        if (arithmetic.leftExpression.getType() != null && arithmetic.rightExpression.getType() != null)
            arithmetic.setType(arithmetic.leftExpression.getType().arithmetic(arithmetic.rightExpression.getType()));
        if(arithmetic.getType() == null)
            arithmetic.setType(new TypeError("Arithmetic operation \'" + arithmetic.operator
                    + "\' between \'" + arithmetic.leftExpression + "\' and \'" + arithmetic.rightExpression +
                    "\' cannot be performed.", arithmetic));
        return null;
    }

    @Override
    public Object visit(Comparison comparison, Object params) {
        super.visit(comparison, params);
        if (comparison.leftExpression.getType() != null && comparison.rightExpression.getType() != null)
            comparison.setType(comparison.leftExpression.getType().comparison(comparison.rightExpression.getType()));
        if (comparison.getType() == null)
            comparison.setType(new TypeError("Comparison operation \'" + comparison.operator
                    + "\' between \'" + comparison.leftExpression + "\' and \'" + comparison.rightExpression +
                    "\' cannot be performed.", comparison));
        return null;
    }

    @Override
    public Object visit(Logic logic, Object params) {
        super.visit(logic, params);
        // To Avoid NullPointerException
        if (logic.leftExpression.getType() != null && logic.rightExpression.getType() != null)
        logic.setType(logic.leftExpression.getType().logic(logic.rightExpression.getType()));
        if(logic.getType() == null)
            logic.setType(new TypeError("Logic operation \'" + logic.operator
                    + "\' between \'" + logic.leftExpression + "\' and \'" + logic.rightExpression +
                    "\' cannot be performed.", logic));
        return null;
    }

    /* Expressions -> Unary */
    @Override
    public Object visit(UnaryMinus unaryMinus, Object params) {
        super.visit(unaryMinus, params);
        if (unaryMinus.expression.getType() != null)
            unaryMinus.setType(unaryMinus.expression.getType().arithmetic());
        if(unaryMinus.getType() == null)
            unaryMinus.setType(new TypeError("Arithmetic negation of \'"+ unaryMinus.expression +
                    "\' cannot be performed.", unaryMinus));
        return null;
    }

    @Override
    public Object visit(Negation negation, Object params) {
        super.visit(negation, params);
        if (negation.expression.getType() != null)
            negation.setType(negation.expression.getType().logic());
        if(negation.getType() == null)
            negation.setType(new TypeError("Logic negation of \'"+ negation.expression +
                    "\' cannot be performed.", negation));
        return null;
    }

    @Override
    public Object visit(Cast cast, Object params) {
        super.visit(cast, params);
        int i = (char) 3;
        if (cast.expression.getType() != null)
            cast.setType(cast.expression.getType().castTo(cast.getType()));
        if(cast.getType() == null)
            cast.setType(new TypeError("Cast \'"+cast+"\' of \'"+
                    cast.expression + "\' cannot be performed.", cast));
        return null;
    }


    /* Statements */
    @Override
    public Object visit(Assignment assignment, Object params) {
        super.visit(assignment, params);
        /* lvalue check */
        if (!assignment.leftExpression.getLvalue()) {
            new TypeError("Semantic error: Lvalue expected.", assignment);
        }
        /* types check */
        // To Avoid NullPointerException
        if (assignment.leftExpression.getType() != null && assignment.rightExpression.getType() != null) {
            if (assignment.leftExpression.getType().assignment(assignment.rightExpression.getType()) == null)
                new TypeError("Assignment operation \'" + assignment.leftExpression + " = " +
                        assignment.rightExpression + "\' cannot be performed.", assignment);
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

    @Override
    public Object visit(InvocationStatement invocationStatement, Object params) {
        super.visit(invocationStatement, params);
        if (invocationStatement.function.getType() != null) {
            if (invocationStatement.function.getType().functionInvocation(invocationStatement.arguments) == null)
                new TypeError("Function \'" + invocationStatement.function.name +
                        "\' cannot be invoked, parameters do not match.", invocationStatement);
        }
        return null;
    }

    @Override
    public Object visit(Return ret, Object params) {
        super.visit(ret, params);
        //ret.expression.accept(this, params);
        if (params != null) {
            if (!ret.expression.getType().promoteTo((Type) params)) // In params -> returnType
                ret.expression.setType(new TypeError("Return type not compatible", ret));
        }
        return null;
    }

    @Override
    public Object visit(If ifStatement, Object params) {
        super.visit(ifStatement, params);
        if (ifStatement.condition.getType() != null) {
            if (!ifStatement.condition.getType().isLogic())
                ifStatement.condition.setType(new TypeError("'If' condition \'"+
                        ifStatement.condition+"\' is no a logic expression.", ifStatement));
        }
        return null;
    }

    @Override
    public Object visit(While whileStatement, Object params) {
        super.visit(whileStatement, params);
        if (whileStatement.condition.getType() != null) {
            if (!whileStatement.condition.getType().isLogic())
                whileStatement.condition.setType(new TypeError("'While' condition \'"+
                        whileStatement.condition+"\' is no a logic expression.", whileStatement));
        }
        return null;
    }

    /* Statements -> Definitions */
    @Override
    public Object visit(FunctionDef functionDef, Object params) {
        super.visit(functionDef, params);
        /* Check all function definitions but main() */
        if (!functionDef.name.equals("main")) {

            if (!((TypeFunction) functionDef.getType()).returnType.isBasicType())
                functionDef.setType(new TypeError("Return type of function \'" + functionDef.name +
                        "\' is not basic.", functionDef));

            for (VariableDef paramDef : ((TypeFunction) functionDef.getType()).parameters) {
                if (!paramDef.getType().isBasicType())
                    functionDef.setType(new TypeError("Parameter \'" + paramDef.name +
                            "\' of function \'" + functionDef.name + "\' is not basic.", functionDef));
            }

            boolean hasReturnStatement = false;
            for (Statement statement : functionDef.statements) {
                statement.accept(this, ((TypeFunction) functionDef.getType()).returnType);
                if (statement instanceof Return)
                    hasReturnStatement = true;
            }
            if (!hasReturnStatement) {
                functionDef.setType(new TypeError("Function '" + functionDef.name +
                        "' has not return statement", functionDef));
            }

        }

        return null;
    }
}
