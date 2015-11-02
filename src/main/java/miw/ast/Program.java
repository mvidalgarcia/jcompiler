package miw.ast;

import miw.ast.statements.Statement;
import miw.ast.statements.definitions.Definition;
import miw.ast.statements.definitions.VariableDef;
import miw.visitor.Visitor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Program extends AbstractASTNode {
    public List<Definition> definitions;

    public Program(Integer line, Integer column, List<Definition> definitions) {
        super(line, column);
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        return "Program{" +
                "definitions=" + definitions +
                '}';
    }

    public Object accept(Visitor visitor, Object params) {
        return visitor.visit(this, params);
    }

//    public String toStringMod() {
//
//        // Order by statements by line
//        Collections.sort(statements, new Comparator<Statement>() {
//            public int compare(Statement o1, Statement o2) {
//                return o1.getLine() - o2.getLine();
//            }
//        });
//
//        String s = "void main() {\n";
//        for (Statement statement: statements) {
//            s += "\t"+statement+";\n";
//        }
//        s += "}";
//        return s;
//    }
}
