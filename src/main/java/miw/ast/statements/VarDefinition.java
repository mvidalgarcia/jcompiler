package miw.ast.statements;

import miw.ast.AbstractASTNode;
import miw.ast.types.Type;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class VarDefinition extends AbstractASTNode implements Statement {
    public Type type;
    public String name;

    public VarDefinition(Integer line, Integer column, Type type, String name) {
        super(line, column);
        this.type = type;
        this.name = name;
    }
}
