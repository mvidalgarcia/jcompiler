package miw.visitor;

import miw.ast.ASTNode;
import miw.ast.Program;
import miw.ast.expressions.Arithmetic;
import miw.ast.expressions.ArrayAccess;
import miw.ast.expressions.Identifier;
import miw.ast.expressions.UnaryMinus;
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
