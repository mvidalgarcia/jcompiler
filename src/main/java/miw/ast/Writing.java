package miw.ast;

import java.util.List;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Writing implements Statement {
    public List<Expression> expressions;
    public Integer line, column;

    public Writing(Integer line, Integer column, List<Expression> expressions) {
        this.expressions = expressions;
        this.column = column;
        this.line = line;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }
}
