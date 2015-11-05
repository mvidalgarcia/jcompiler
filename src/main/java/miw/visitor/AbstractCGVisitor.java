package miw.visitor;

import miw.ast.Program;
import miw.ast.expressions.Identifier;
import miw.ast.expressions.InvocationExpression;
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
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.*;

/**
 * Created by mvidalgarcia on 4/11/15.
 */
public abstract class AbstractCGVisitor implements Visitor {
    private RuntimeException e = new RuntimeException("COMPILATION ERROR");

    public Object visit(Program program, Object params) {
        throw e;
    }

    public Object visit(Identifier identifier, Object params) {
        throw e;
    }

    public Object visit(InvocationExpression invocationExpression, Object params) {
        throw e;
    }

    public Object visit(Arithmetic arithmetic, Object params) {
        throw e;
    }

    public Object visit(ArrayAccess arrayAccess, Object params) {
        throw e;
    }

    public Object visit(Comparison comparison, Object params) {
        throw e;
    }

    public Object visit(Logic logic, Object params) {
        throw e;
    }

    public Object visit(UnaryMinus unaryMinus, Object params) {
        throw e;
    }

    public Object visit(Negation negation, Object params) {
        throw e;
    }

    public Object visit(Cast cast, Object params) {
        throw e;
    }

    public Object visit(LiteralInteger literalInteger, Object params) {
        throw e;
    }

    public Object visit(LiteralDouble literalDouble, Object params) {
        throw e;
    }

    public Object visit(LiteralCharacter literalCharacter, Object params) {
        throw e;
    }

    public Object visit(Assignment assignment, Object params) {
        throw e;
    }

    public Object visit(Writing writing, Object params) {
        throw e;
    }

    public Object visit(Reading reading, Object params) {
        throw e;
    }

    public Object visit(If ifStatement, Object params) {
        throw e;
    }

    public Object visit(While whileStatement, Object params) {
        throw e;
    }

    public Object visit(Return returnStatement, Object params) {
        throw e;
    }

    public Object visit(InvocationStatement invocationStatement, Object params) {
        throw e;
    }

    public Object visit(VariableDef variableDef, Object params) {
        throw e;
    }

    public Object visit(FunctionDef functionDef, Object params) {
        throw e;
    }

    public Object visit(TypeInteger typeInteger, Object params) {
        throw e;
    }

    public Object visit(TypeDouble typeDouble, Object params) {
        throw e;
    }

    public Object visit(TypeChar typeChar, Object params) {
        throw e;
    }

    public Object visit(TypeArray typeArray, Object params) {
        throw e;
    }

    public Object visit(TypeFunction typeFunction, Object params) {
        throw e;
    }

    public Object visit(TypeVoid typeVoid, Object params) {
        throw e;
    }

    public Object visit(TypeError typeError, Object params) {
        throw e;
    }
}
