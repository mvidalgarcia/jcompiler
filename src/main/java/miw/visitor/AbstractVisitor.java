package miw.visitor;

import miw.ast.Program;
import miw.ast.expressions.*;
import miw.ast.expressions.literals.LiteralCharacter;
import miw.ast.expressions.literals.LiteralDouble;
import miw.ast.expressions.literals.LiteralInteger;
import miw.ast.statements.Assignment;
import miw.ast.statements.Reading;
import miw.ast.statements.Statement;
import miw.ast.statements.Writing;
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
    public Object visit(Arithmetic arithmetic, Object params) {
        arithmetic.leftExpression.accept(this, params);
        arithmetic.rightExpression.accept(this, params);
        return null;
    }

    public Object visit(Identifier identifier, Object params) {
        return null;
    }

    public Object visit(ArrayAccess arrayAccess, Object params) {
        arrayAccess.array.accept(this, params);
        arrayAccess.index.accept(this, params);
        return null;
    }

    public Object visit(UnaryMinus unaryMinus, Object params) {
        unaryMinus.expression.accept(this, params);
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
