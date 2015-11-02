package miw.visitor;

import miw.ast.Program;
import miw.ast.expressions.binary.Comparison;
import miw.ast.expressions.binary.Logic;
import miw.ast.expressions.unary.Cast;
import miw.ast.expressions.binary.Arithmetic;
import miw.ast.expressions.binary.ArrayAccess;
import miw.ast.expressions.Identifier;
import miw.ast.expressions.unary.Negation;
import miw.ast.expressions.unary.UnaryMinus;
import miw.ast.expressions.literals.LiteralCharacter;
import miw.ast.expressions.literals.LiteralDouble;
import miw.ast.expressions.literals.LiteralInteger;
import miw.ast.statements.*;
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.*;

import java.util.Locale;

/**
 * Created by mvidalgarcia on 30/10/15.
 */
public interface Visitor {
    /* Program */
    Object visit(Program program, Object params);

    /* Expressions */
    Object visit(Identifier identifier, Object params);
    /* Expressions -> Binary */
    Object visit(Arithmetic arithmetic, Object params);
    Object visit(ArrayAccess arrayAccess, Object params);
    Object visit(Comparison comparison, Object params);
    Object visit(Logic logic, Object params);
    /* Expressions -> Unary */
    Object visit(UnaryMinus unaryMinus, Object params);
    Object visit(Negation negation, Object params);
    Object visit(Cast cast, Object params);
    /* Expressions -> Literals */
    Object visit(LiteralInteger literalInteger, Object params);
    Object visit(LiteralDouble literalDouble, Object params);
    Object visit(LiteralCharacter literalCharacter, Object params);


    /* Statements */
    Object visit(Assignment assignment, Object params);
    Object visit(Writing writing, Object params);
    Object visit(Reading reading, Object params);
    Object visit(If ifStatement, Object params);
    Object visit(While whileStatement, Object params);
    Object visit(Return returnStatement, Object params);
    Object visit(Invocation invocation, Object params);
    /* Statements -> Definitions */
    Object visit(VariableDef variableDef, Object params);
    Object visit(FunctionDef functionDef, Object params);

    /* Types */
    Object visit(TypeInteger typeInteger, Object params);
    Object visit(TypeDouble typeDouble, Object params);
    Object visit(TypeChar typeChar, Object params);
    Object visit(TypeArray typeArray, Object params);
    Object visit(TypeFunction typeFunction, Object params);
    Object visit(TypeVoid typeVoid, Object params);
    Object visit(TypeError typeError, Object params);


}
