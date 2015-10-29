package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Variable extends AbstractASTNode implements Expression {
    public String name;

    public Variable(Integer line, Integer column, String name) {
        this.name = name;
        this.column = column;
        this.line = line;
    }

    @Override
    public String toString() {
        return name;
    }
}
