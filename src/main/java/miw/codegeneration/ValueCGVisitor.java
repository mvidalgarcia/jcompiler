package miw.codegeneration;

import miw.ast.expressions.Expression;
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
import miw.ast.statements.Writing;
import miw.ast.types.Type;
import miw.ast.types.TypeDouble;
import miw.ast.types.TypeFunction;
import miw.visitor.AbstractCGVisitor;

/**
 * Created by mvidalgarcia on 4/11/15.
 */
public class ValueCGVisitor extends AbstractCGVisitor {
    private CodeGenerator codeGen = new CodeGenerator();
    private AddressCGVisitor addressCGVisitor;

    public void setAddressCGVisitor(AddressCGVisitor v) {
        addressCGVisitor = v;
    }

    /* -- Expressions -- */

    public Object visit(Identifier identifier, Object params) {
        identifier.accept(addressCGVisitor, params);
        codeGen.load(identifier.getType());
        return null;
    }

    public Object visit(InvocationExpression invocationExpression, Object params) {
        int i = 0;
        for (Expression arg: invocationExpression.arguments) {
            arg.accept(this, params);
            codeGen.transformType(arg.getType(),
                    ((TypeFunction)(invocationExpression.function.getType())).parameters.get(0).getType());
            i++;
        }
        codeGen.functionCall(invocationExpression.function.name);
        return null;
    }


    /* Expressions -> Binary */

    public Object visit(Arithmetic arithmetic, Object params) {
        arithmetic.leftExpression.accept(this, params);
        codeGen.transformType(arithmetic.leftExpression.getType(), arithmetic.getType());
        arithmetic.rightExpression.accept(this, params);
        codeGen.transformType(arithmetic.rightExpression.getType(), arithmetic.getType());
        codeGen.arithmetic(arithmetic.operator, arithmetic.getType());

        return null;
    }

    public Object visit(Comparison comparison, Object params) {
        comparison.leftExpression.accept(this, params);
        Type greaterType = comparison.leftExpression.getType().greaterType(comparison.rightExpression.getType());
        codeGen.transformType(comparison.leftExpression.getType(), greaterType);
        comparison.rightExpression.accept(this, params);
        codeGen.transformType(comparison.rightExpression.getType(), greaterType);
        codeGen.comparison(comparison.operator, greaterType);
        return null;
    }

    public Object visit(Logic logic, Object params) {
        logic.leftExpression.accept(this, params);
        codeGen.transformType(logic.leftExpression.getType(), logic.getType());
        logic.rightExpression.accept(this, params);
        codeGen.transformType(logic.rightExpression.getType(), logic.getType());
        codeGen.logic(logic.operator);
        return null;
    }

    public Object visit(ArrayAccess arrayAccess, Object params) {
        arrayAccess.accept(addressCGVisitor, params);
        codeGen.load(arrayAccess.getType());
        return null;
    }


    /* Expressions -> Unary */

    public Object visit(UnaryMinus unaryMinus, Object params) {
        if (unaryMinus.expression.getType() instanceof TypeDouble)
            codeGen.push(0.0);
        else
            codeGen.push(0);
        unaryMinus.expression.accept(this, params);
        codeGen.transformType(unaryMinus.expression.getType(), unaryMinus.getType());
        codeGen.sub(unaryMinus.getType());
        return null;
    }

    public Object visit(Negation negation, Object params) {
        negation.expression.accept(this, params);
        codeGen.not();
        return null;
    }

    public Object visit(Cast cast, Object params) {
        cast.expression.accept(this, params);
        codeGen.transformType(cast.expression.getType(), cast.type);
        return null;
    }

    /* Expressions -> Literals */

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
}
