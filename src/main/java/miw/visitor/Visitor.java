package miw.visitor;

import miw.ast.Program;
import miw.ast.expressions.binary.Arithmetic;
import miw.ast.expressions.binary.ArrayAccess;
import miw.ast.expressions.Identifier;
import miw.ast.expressions.UnaryMinus;
import miw.ast.expressions.literals.LiteralCharacter;
import miw.ast.expressions.literals.LiteralDouble;
import miw.ast.expressions.literals.LiteralInteger;
import miw.ast.statements.Assignment;
import miw.ast.statements.Reading;
import miw.ast.statements.Writing;
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.*;

/**
 * Created by mvidalgarcia on 30/10/15.
 */
public interface Visitor {
    /* Program */
    Object visit(Program program, Object params);

    /* Expressions */
    Object visit(Arithmetic arithmetic, Object params);
    Object visit(Identifier identifier, Object params);
    Object visit(ArrayAccess arrayAccess, Object params);
    Object visit(UnaryMinus unaryMinus, Object params);

    /* Expressions -> Literals */
    Object visit(LiteralInteger literalInteger, Object params);
    Object visit(LiteralDouble literalDouble, Object params);
    Object visit(LiteralCharacter literalCharacter, Object params);


    /* Statements */
    Object visit(Assignment assignment, Object params);
    Object visit(Writing writing, Object params);
    Object visit(Reading reading, Object params);
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
