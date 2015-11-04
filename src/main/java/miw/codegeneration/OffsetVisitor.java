package miw.codegeneration;

import miw.ast.statements.Statement;
import miw.ast.statements.definitions.Definition;
import miw.ast.statements.definitions.FunctionDef;
import miw.ast.statements.definitions.VariableDef;
import miw.ast.types.TypeFunction;
import miw.visitor.AbstractVisitor;

/**
 * Created by mvidalgarcia on 4/11/15.
 */
public class OffsetVisitor extends AbstractVisitor {

    private int sumGlobalVarOffsets = 0;

    /* Statement -> Definition */

    // Offsets of global and local variables
    @Override
    public Object visit(VariableDef variableDef, Object params) {
        // Global variables
        if (variableDef.scope == 0) {
            variableDef.offset = sumGlobalVarOffsets;
            sumGlobalVarOffsets += variableDef.getType().size();
        }
        // Local variables in a function
        else if (params != null) {
            int offsetLocal = (Integer) params;
            offsetLocal -= variableDef.getType().size();
            variableDef.offset = offsetLocal;
            return offsetLocal;
        }
        return null;
    }

    @Override
    public Object visit(FunctionDef functionDef, Object params) {
        int offsetsCurrentSum = 0;
        functionDef.getType().accept(this, params);
        for (Statement statement: functionDef.statements) {
            Object currentOffset = statement.accept(this, offsetsCurrentSum);
            if (currentOffset != null)
                offsetsCurrentSum = (Integer) currentOffset;
        }
        functionDef.localVariablesOffset = Math.abs(offsetsCurrentSum);
        return null;
    }

    /* Types */
    // Offsets of function parameters
    @Override
    public Object visit(TypeFunction typeFunction, Object params) {
        int paramOffset = 4; // Parameter offset always starts in 4 (prev IP & BP)
        for (int i=typeFunction.parameters.size()-1; i>=0; i--) {
            typeFunction.parameters.get(i).offset = paramOffset;
            paramOffset += typeFunction.parameters.get(i).getType().size();
        }
        return null;
    }
}
