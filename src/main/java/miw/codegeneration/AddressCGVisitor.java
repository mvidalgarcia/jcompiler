package miw.codegeneration;

import miw.ast.expressions.Identifier;
import miw.ast.expressions.binary.ArrayAccess;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.Type;
import miw.ast.types.TypeInteger;
import miw.visitor.AbstractCGVisitor;

/**
 * Created by mvidalgarcia on 4/11/15.
 */
public class AddressCGVisitor extends AbstractCGVisitor {
    private CodeGenerator codeGen = new CodeGenerator();

    public Object visit(Identifier identifier, Object params) {
        if (identifier.definition.getScope() == 0) // Global
            codeGen.push( ((VariableDef)identifier.definition).offset );
        else {
            codeGen.pushBP();
            codeGen.push( ((VariableDef)identifier.definition).offset );
            codeGen.add(TypeInteger.getInstance(identifier.line, identifier.column));
        }
        return null;
    }

    public Object visit(ArrayAccess arrayAccess, Object params) {
        /*
         * op1.accept(this)
         * op2.accept(visitorValor)
         * gc.convertirA
         */
        return null;
    }



}
