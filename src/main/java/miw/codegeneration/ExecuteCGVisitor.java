package miw.codegeneration;

import miw.ast.Program;
import miw.ast.expressions.Expression;
import miw.ast.statements.*;
import miw.ast.statements.definitions.Definition;
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.Type;
import miw.ast.types.TypeFunction;
import miw.ast.types.TypeVoid;
import miw.visitor.AbstractCGVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvidalgarcia on 4/11/15.
 */
public class ExecuteCGVisitor extends AbstractCGVisitor {
    private CodeGenerator codeGen;
    private AddressCGVisitor addressCGVisitor;
    private ValueCGVisitor valueCGVisitor;
    private int count = 0;

    public ExecuteCGVisitor() {
        codeGen = new CodeGenerator();
        addressCGVisitor = new AddressCGVisitor();
        valueCGVisitor = new ValueCGVisitor();
        addressCGVisitor.setValueCGVisitor(valueCGVisitor);
        valueCGVisitor.setAddressCGVisitor(addressCGVisitor);
    }

    @Override
    public Object visit(Program program, Object params) {
        codeGen.source(params.toString());
        codeGen.emptyLine();

        /* Write global variables */
        codeGen.comment("Global variables");
        List<Definition> functionDefinitions = new ArrayList<Definition>();
        for (Definition definition: program.definitions)
            if (definition instanceof VariableDef)
                definition.accept(this, params);
            else
                functionDefinitions.add(definition);

        codeGen.emptyLine();
        codeGen.main();
        codeGen.emptyLine();

        /* Write functions */
        for (Definition definition: functionDefinitions)
            definition.accept(this, params);

        return null;
    }


    /* --- Statements --- */

    public Object visit(InvocationStatement invocationStatement, Object params) {
        codeGen.line(invocationStatement.getLine());
        codeGen.comment("Statement Invocation");
        invocationStatement.accept(valueCGVisitor, params);
        TypeFunction tf = ((TypeFunction)(invocationStatement.function.getType()));
        if (!( tf.returnType instanceof TypeVoid ))
            codeGen.pop(tf.returnType);
        return null;
    }

    public Object visit(Reading reading, Object params) {
        codeGen.line(reading.getLine());
        codeGen.comment("Reading");
        for (Expression expression: reading.expressions) {
            expression.accept(addressCGVisitor, params);
            codeGen.in(expression.getType());
            codeGen.store(expression.getType());
        }
        return null;
    }

    public Object visit(Writing writing, Object params) {
        codeGen.line(writing.getLine());
        codeGen.comment("Writing");
        for (Expression expression: writing.expressions) {
            expression.accept(valueCGVisitor, params);
            codeGen.out(expression.getType());
        }
        return null;
    }

    public Object visit(Assignment assignment, Object params) {
        codeGen.line(assignment.getLine());
        codeGen.comment("Assignment");
        assignment.leftExpression.accept(addressCGVisitor, params);
        assignment.rightExpression.accept(valueCGVisitor, params);
        codeGen.transformType(assignment.rightExpression.getType(), assignment.leftExpression.getType());
        codeGen.store(assignment.leftExpression.getType());
        return null;
    }

    public Object visit(While whileStatement, Object params) {
        codeGen.line(whileStatement.condition.getLine());
        codeGen.comment("While");
        int initCount = count;
        codeGen.label("label" + initCount);
        whileStatement.condition.accept(valueCGVisitor, params);

        codeGen.jz("label" + (initCount + 1));
        count = count + 2; // Two labels used
        for (Statement statement: whileStatement.whileBody) {
            codeGen.emptyLine();
            statement.accept(this, params);
        }
        codeGen.jmp("label" + initCount);
        codeGen.label("label" + (initCount + 1));
        return null;
    }

    public Object visit(If ifStatement, Object params) {
        codeGen.line(ifStatement.condition.getLine());
        codeGen.comment("If");
        int initCount = count;
        ifStatement.condition.accept(valueCGVisitor, params);
        codeGen.jz("label" + initCount);
        codeGen.comment("If body");
        for (Statement statement: ifStatement.ifBody)
            statement.accept(this, params);
        count++;

        if (ifStatement.elseBody != null) {
            codeGen.jmp("label" + (initCount + 1));
            count++;
        }

        codeGen.label("label" + initCount);
        if (ifStatement.elseBody != null) {
            codeGen.comment("Else body");
            for (Statement statement : ifStatement.elseBody)
                statement.accept(this, params);
            codeGen.label("label" + (initCount + 1));
        }
        return null;

    }

    public Object visit(Return returnStatement, Object params) {
        codeGen.line(returnStatement.getLine());
        FunctionDef fd = (FunctionDef) params;
        codeGen.comment("Return");
        returnStatement.expression.accept(valueCGVisitor, params);
        codeGen.transformType(returnStatement.expression.getType(), ((TypeFunction) fd.type).returnType);
        //codeGen.ret(returnStatement.expression.getType().size(),fd.localVariablesOffset, ((TypeFunction) fd.getType()).parametersSize());
        return null;
    }

    /* Statements -> Definitions */

    public Object visit(VariableDef variableDef, Object params) {
        codeGen.varDefComment(variableDef.getType(), variableDef.name, variableDef.offset);
        return null;
    }

    public Object visit(FunctionDef functionDef, Object params) {
        // Gets accurate line through first statement line of function - 1
        codeGen.line(functionDef.statements.get(0).getLine()-1);
        codeGen.emptyLine();
        codeGen.label(functionDef.getName());

        int bytesParams = 0;
        codeGen.comment("Parameters");
        for (VariableDef vd: ((TypeFunction)functionDef.getType()).parameters){
            bytesParams += vd.getType().size();
            vd.accept(this, params);
        }

        codeGen.comment("Local variables");
        // Stores all statements but variable definitions
        List<Statement> statements = new ArrayList<Statement>();
        for (Statement statement: functionDef.statements) {
            if (statement instanceof VariableDef)
                statement.accept(this, params);
            else
                statements.add(statement);
        }

        codeGen.enter(functionDef.localVariablesOffset);
        codeGen.emptyLine();

        for (Statement statement: statements) {
            // Function def as params, it'll be used in Return
            statement.accept(this, functionDef);
            codeGen.emptyLine();
        }

        codeGen.ret( ((TypeFunction)functionDef.type).returnType.size(), functionDef.localVariablesOffset, bytesParams);
        return null;
    }

}
