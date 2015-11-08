package miw.codegeneration;

import miw.ast.expressions.Identifier;
import miw.ast.expressions.binary.ArrayAccess;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.Type;
import miw.ast.types.TypeArray;
import miw.ast.types.TypeInteger;
import miw.visitor.AbstractCGVisitor;

/**
 * Created by mvidalgarcia on 4/11/15.
 */
public class AddressCGVisitor extends AbstractCGVisitor {
    private CodeGenerator codeGen;
    private ValueCGVisitor valueCGVisitor;

    public void setValueCGVisitor (ValueCGVisitor v) {
        this.valueCGVisitor = v;
    }

    public void setCodeGen (CodeGenerator cg) {
        this.codeGen = cg;
    }

    public Object visit(Identifier identifier, Object params) {
        if (identifier.definition.getScope() == 0) // Global
            codeGen.pusha( ((VariableDef)identifier.definition).offset );
        else {
            codeGen.pushBP();
            codeGen.push( ((VariableDef)identifier.definition).offset );
            codeGen.add(TypeInteger.getInstance(identifier.line, identifier.column));
        }
        return null;
    }

    public Object visit(ArrayAccess arrayAccess, Object params) {
        arrayAccess.leftExpression.accept(this, params);
        arrayAccess.rightExpression.accept(valueCGVisitor, params);
        codeGen.transformType(arrayAccess.rightExpression.getType(),
                TypeInteger.getInstance(arrayAccess.getLine(), arrayAccess.getColumn()));
        codeGen.push( ((TypeArray)(arrayAccess.leftExpression.getType())).type.size() );
        codeGen.muli();
        codeGen.addi();
        return null;
    }



}
