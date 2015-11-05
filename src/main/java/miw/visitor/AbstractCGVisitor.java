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
    public Object visit(Program program, Object params) {
        return null;
    }

    public Object visit(Identifier identifier, Object params) {
        return null;
    }

    public Object visit(InvocationExpression invocationExpression, Object params) {
        return null;
    }

    public Object visit(Arithmetic arithmetic, Object params) {
        return null;
    }

    public Object visit(ArrayAccess arrayAccess, Object params) {
        return null;
    }

    public Object visit(Comparison comparison, Object params) {
        return null;
    }

    public Object visit(Logic logic, Object params) {
        return null;
    }

    public Object visit(UnaryMinus unaryMinus, Object params) {
        return null;
    }

    public Object visit(Negation negation, Object params) {
        return null;
    }

    public Object visit(Cast cast, Object params) {
        return null;
    }

    public Object visit(LiteralInteger literalInteger, Object params) {
        return null;
    }

    public Object visit(LiteralDouble literalDouble, Object params) {
        return null;
    }

    public Object visit(LiteralCharacter literalCharacter, Object params) {
        return null;
    }

    public Object visit(Assignment assignment, Object params) {
        return null;
    }

    public Object visit(Writing writing, Object params) {
        return null;
    }

    public Object visit(Reading reading, Object params) {
        return null;
    }

    public Object visit(If ifStatement, Object params) {
        return null;
    }

    public Object visit(While whileStatement, Object params) {
        return null;
    }

    public Object visit(Return returnStatement, Object params) {
        return null;
    }

    public Object visit(InvocationStatement invocationStatement, Object params) {
        return null;
    }

    public Object visit(VariableDef variableDef, Object params) {
        return null;
    }

    public Object visit(FunctionDef functionDef, Object params) {
        return null;
    }

    public Object visit(TypeInteger typeInteger, Object params) {
        return null;
    }

    public Object visit(TypeDouble typeDouble, Object params) {
        return null;
    }

    public Object visit(TypeChar typeChar, Object params) {
        return null;
    }

    public Object visit(TypeArray typeArray, Object params) {
        return null;
    }

    public Object visit(TypeFunction typeFunction, Object params) {
        return null;
    }

    public Object visit(TypeVoid typeVoid, Object params) {
        return null;
    }

    public Object visit(TypeError typeError, Object params) {
        return null;
    }
}
