package miw.ast.expressions;

import miw.ast.statements.definitions.Definition;
import miw.visitor.Visitor;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Identifier extends AbstractExpression implements Expression {
    public String name;
    public Definition definition; // IdentificationVisitor purposes


    public Identifier(Integer line, Integer column, String name) {
        super(line, column);
        this.name = name;
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }



    public String toStringM() {
        return "Identifier{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return name;
    }
}
