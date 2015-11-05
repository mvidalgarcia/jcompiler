package miw.codegeneration;

import miw.ast.expressions.Expression;
import miw.ast.expressions.Identifier;
import miw.ast.expressions.binary.Arithmetic;
import miw.ast.expressions.binary.ArrayAccess;
import miw.ast.expressions.binary.Comparison;
import miw.ast.expressions.binary.Logic;
import miw.ast.expressions.literals.LiteralCharacter;
import miw.ast.expressions.literals.LiteralDouble;
import miw.ast.expressions.literals.LiteralInteger;
import miw.ast.expressions.unary.UnaryMinus;
import miw.ast.statements.Writing;
import miw.visitor.AbstractCGVisitor;

/**
 * Created by mvidalgarcia on 4/11/15.
 */
public class ValueCGVisitor extends AbstractCGVisitor {
    private CodeGenerator codeGen = new CodeGenerator();
    private AddressCGVisitor addressCGVisitor = new AddressCGVisitor();

    public Object visit(Arithmetic arithmetic, Object params) {
        arithmetic.leftExpression.accept(this, params);
        codeGen.transformType(arithmetic.leftExpression.getType(), arithmetic.getType());
        arithmetic.rightExpression.accept(this, params);
        codeGen.transformType(arithmetic.rightExpression.getType(), arithmetic.getType());
        codeGen.arithmetic(arithmetic.operator, arithmetic.getType());

        return null;
    }

    public Object visit(LiteralInteger literalInteger, Object params) {
        codeGen.push(literalInteger.value);
        return null;
    }

    public Object visit(LiteralCharacter literalCharacter, Object params) {
        codeGen.push(literalCharacter.value);
        return null;
    }

    public Object visit(LiteralDouble literalDouble, Object params) {
        codeGen.push(literalDouble.value);
        return null;
    }

    public Object visit(Identifier identifier, Object params) {
        identifier.accept(addressCGVisitor, params);
        codeGen.load(identifier.getType());
        return null;
    }

    public Object visit(Comparison comparison, Object params) {
        /*
         * op1.accept(this)
         * Tipo mayorTipo = comparison.op1.gettipo.tipomayor(op2.getipo)
         *
         * En logica no tiene dificultad porqe simpre se hace con tipo entero.
         */
        return null;
    }

    public Object visit(Logic logic, Object params) {
        /*
         * En logica no tiene dificultad porqe simpre se hace con tipo entero.
         */
        return null;
    }

    public Object visit(UnaryMinus unaryMinus, Object params) {
        /*
         * push 0
         * gc.convertirA(tipoEntero.Getinstace, um.gettipo)
         */
        return null;
    }

    public Object visit(ArrayAccess arrayAccess, Object params) {
        /*
         * aarrayAcces.accept(addressCGvisti)
         * gc.load(arrayaccess.get
         */
        return null;
    }
}
