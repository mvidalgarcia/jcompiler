package miw.visitor;

import miw.ast.Program;
import miw.ast.expressions.*;
import miw.ast.expressions.binary.Arithmetic;
import miw.ast.expressions.binary.ArrayAccess;
import miw.ast.expressions.binary.Comparison;
import miw.ast.expressions.binary.Logic;
import miw.ast.expressions.literals.LiteralCharacter;
import miw.ast.expressions.literals.LiteralDouble;
import miw.ast.expressions.literals.LiteralInteger;
import miw.ast.expressions.unary.Cast;
import miw.ast.expressions.unary.Negation;
import miw.ast.expressions.unary.UnaryMinus;
import miw.ast.statements.*;
import miw.ast.statements.definitions.Definition;
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.*;

/**
 * Created by mvidalgarcia on 30/10/15.
 */
public abstract class AbstractVisitor implements Visitor {

    /* Program */
    public Object visit(Program program, Object params) {
        for (Definition definition: program.definitions)
            definition.accept(this, params);
        return null;
    }

    /* Expressions */

    public Object visit(Identifier identifier, Object params) {
        return null;
    }

    public Object visit(InvocationExpression invocationExpression, Object params) {
        for (Expression expression: invocationExpression.arguments)
            expression.accept(this, params);

        invocationExpression.function.accept(this, params);
        return null;
    }

    /* Expressions -> Binary */
    public Object visit(Arithmetic arithmetic, Object params) {
        arithmetic.leftExpression.accept(this, params);
        arithmetic.rightExpression.accept(this, params);
        return null;
    }
    public Object visit(ArrayAccess arrayAccess, Object params) {
        arrayAccess.leftExpression.accept(this, params);
        arrayAccess.rightExpression.accept(this, params);
        return null;
    }
    public Object visit(Comparison comparison, Object params) {
        comparison.leftExpression.accept(this, params);
        comparison.rightExpression.accept(this, params);
        return null;
    }
    public Object visit(Logic logic, Object params) {
        logic.leftExpression.accept(this, params);
        logic.rightExpression.accept(this, params);
        return null;
    }

    /* Expressions -> Unary */
    public Object visit(UnaryMinus unaryMinus, Object params) {
        unaryMinus.expression.accept(this, params);
        return null;
    }
    public Object visit(Negation negation, Object params) {
        negation.expression.accept(this, params);
        return null;
    }
    public Object visit(Cast cast, Object params) {
        cast.type.accept(this, params);
        cast.expression.accept(this, params);
        return null;
    }

    /* Expressions -> Literals */
    public Object visit(LiteralInteger literalInteger, Object params) {
        return null;
    }
    public Object visit(LiteralDouble literalDouble, Object params) {
        return null;
    }
    public Object visit(LiteralCharacter literalCharacter, Object params) {
        return null;
    }

    /* Statements */
    public Object visit(Assignment assignment, Object params) {
        assignment.leftExpression.accept(this, params);
        assignment.rightExpression.accept(this, params);
        return null;
    }

    public Object visit(Writing writing, Object params) {
        for (Expression expression: writing.expressions)
            expression.accept(this, params);
        return null;
    }

    public Object visit(Reading reading, Object params) {
        for (Expression expression: reading.expressions)
            expression.accept(this, params);
        return null;
    }

    public Object visit(If ifStatement, Object params) {
        for (Statement statement: ifStatement.ifBody)
            statement.accept(this, params);

        for (Statement statement: ifStatement.elseBody)
            statement.accept(this, params);

        ifStatement.condition.accept(this, params);
        return null;
    }

    public Object visit(While whileStatement, Object params) {
        for (Statement statement: whileStatement.whileBody)
            statement.accept(this, params);

        whileStatement.condition.accept(this, params);
        return null;
    }

    public Object visit(Return returnStatement, Object params) {
        returnStatement.expression.accept(this, params);
        return null;
    }

    public Object visit(InvocationStatement invocationStatement, Object params) {
        for (Expression expression: invocationStatement.arguments)
            expression.accept(this, params);

        invocationStatement.function.accept(this, params);
        return null;
    }

    /* Statements -> Definitions */
    public Object visit(VariableDef variableDef, Object params) {
        variableDef.type.accept(this, params);
        variableDef.name.accept(this, params);
        return null;
    }

    public Object visit(FunctionDef functionDef, Object params) {
        functionDef.type.accept(this, params);
        functionDef.name.accept(this, params);
        for (Statement statement: functionDef.statements)
            statement.accept(this, params);
        return null;
    }

    /* Types */
    public Object visit(TypeInteger typeInteger, Object params) {
        return null;
    }
    public Object visit(TypeDouble typeDouble, Object params) {
        return null;
    }
    public Object visit(TypeChar typeChar, Object params) {
        return null;
    }
    public Object visit(TypeVoid typeVoid, Object params) {
        return null;
    }
    public Object visit(TypeError typeError, Object params) {
        return null;
    }
    public Object visit(TypeArray typeArray, Object params) {
        typeArray.type.accept(this, params);
        return null;
    }
    public Object visit(TypeFunction typeFunction, Object params) {
        typeFunction.returnType.accept(this, params);
        for (VariableDef variableDef: typeFunction.parameters)
            variableDef.accept(this, params);
        return null;
    }


}
