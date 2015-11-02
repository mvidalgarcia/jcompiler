package miw.semantic;

import miw.ast.expressions.Identifier;
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.TypeError;
import miw.semantic.symboltable.SymbolTable;
import miw.visitor.AbstractVisitor;

/**
 * Created by mvidalgarcia on 2/11/15.
 */
public class IdentificationVisitor extends AbstractVisitor {
    public SymbolTable symbolTable;

    public IdentificationVisitor() {
        symbolTable = new SymbolTable();
    }

    @Override
    public Object visit(VariableDef variableDef, Object params) {
        if(!symbolTable.insert(variableDef))
            new TypeError("Semantic error: variable \"" + variableDef.getName() + "\" already declared.",
                    variableDef);
        else
            super.visit(variableDef, params);
        return null;
    }

    @Override
    public Object visit(FunctionDef functionDef, Object params) {
        if(!symbolTable.insert(functionDef))
            new TypeError("Semantic error: function \"" + functionDef.getName() + "\" already declared.",
                    functionDef);
        else {
            symbolTable.set();
            super.visit(functionDef, params);
            symbolTable.reset();
        }
        return null;
    }

    @Override
    public Object visit(Identifier identifier, Object params) {
        if (symbolTable.search(identifier.name) == null)
            new TypeError("Semantic error: \"" + identifier.name + "\" is not declared.",
                    identifier);
        else
            super.visit(identifier, params);
        return null;
    }
}
