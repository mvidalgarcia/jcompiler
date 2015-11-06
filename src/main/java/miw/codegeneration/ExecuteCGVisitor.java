package miw.codegeneration;

import miw.ast.Program;
import miw.ast.expressions.Expression;
import miw.ast.statements.*;
import miw.ast.statements.definitions.Definition;
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.TypeFunction;
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

    public Object visit(VariableDef variableDef, Object params) {
        codeGen.varDefComment(variableDef.getType(), variableDef.name, variableDef.offset);
        return null;
    }

    public Object visit(FunctionDef functionDef, Object params) {
        codeGen.line(functionDef.line);
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
            statement.accept(this, params);
            codeGen.emptyLine();
        }

        codeGen.ret(functionDef.getType().size(), functionDef.localVariablesOffset, bytesParams);
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
        count++;
        codeGen.jz("label" + count);
        for (Statement statement: whileStatement.whileBody)
            statement.accept(this, params);
        codeGen.label("label" + count);
        return null;
    }



}
