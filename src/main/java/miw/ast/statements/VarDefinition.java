package miw.ast.statements;

import miw.ast.types.Type;

/**
 * Created by mvidalgarcia on 28/10/15.
 */
public class VarDefinition implements Statement {
    public Type type;
    public String name;
    public Integer line, column;

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }
}
