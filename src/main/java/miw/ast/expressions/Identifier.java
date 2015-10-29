package miw.ast.expressions;

import miw.ast.AbstractASTNode;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Identifier extends AbstractASTNode implements Expression {
    public String name;

    public Identifier(Integer line, Integer column, String name) {
        super(line, column);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "name='" + name + '\'' +
                '}';
    }

    public String toStringMod() {
        return name;
    }
}
