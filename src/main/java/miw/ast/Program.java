package miw.ast;

import miw.ast.statements.Statement;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class Program {
    public List<Statement> statements;

    public Program(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {

        // Order by statements by line
        Collections.sort(statements, new Comparator<Statement>() {
            public int compare(Statement o1, Statement o2) {
                return o1.getLine() - o2.getLine();
            }
        });

        String s = "void main() {\n";
        for (Statement statement: statements) {
            s += "\t"+statement+";\n";
        }
        s += "}";
        return s;
    }
}
