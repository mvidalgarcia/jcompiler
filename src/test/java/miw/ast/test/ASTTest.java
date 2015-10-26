package miw.ast.test;

import miw.ast.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mvidalgarcia on 26/10/15.
 */
public class ASTTest {

    /**
     *  void main(){
     *      read a,b;
     *      a = (-b+3)*c/2;
     *      write a,c*2;
     *  }
     */

    public Program buildTree() {
        /* First line of code */
        List<Expression> expressions = new ArrayList<Expression>();
        expressions.add(new Variable(1, 6, "a"));
        expressions.add(new Variable(1, 8, "b"));
        Statement firstLine = new Reading(1, 1, expressions);

        /* Second line of code */
        Expression leftExpression = new Variable(2, 1, "a");
        Expression rightExpression = new Arithmetic(2, 13,
                new Arithmetic(2, 11,
                        new Arithmetic(2, 8,
                                new UnaryMinus(2, 6, new Variable(2, 7, "b")),
                                "+",
                                new LiteralInteger(2, 9, 3)
                        ),
                        "*",
                        new Variable(2, 12, "c")
                ),
                "/",
                new LiteralInteger(2, 14, 2)
        );
        Statement secondLine = new Assignment(2, 3, leftExpression, rightExpression); // column means '=' symbol

        /* Third line of code */
        expressions = new ArrayList<Expression>();
        expressions.add(new Variable(3, 7, "a"));
        expressions.add(new Arithmetic(3, 10,
                new Variable(3, 9, "c"),
                "*",
                new LiteralInteger(3, 11, 2)
        ));
        Statement thirdLine = new Writing(3, 1, expressions);

        /* Program */
        List<Statement> statements = new ArrayList<Statement>();
        statements.add(firstLine);
        statements.add(thirdLine);
        statements.add(secondLine);
        Program program = new Program(statements);

        return program;
    }

    public String myFirstTest(){
        return "hello jUnit!";
    }

    @Test
    public void testReturnString() {
        assertEquals("hello jUnit!", myFirstTest());
        System.out.println(buildTree());
    }

}