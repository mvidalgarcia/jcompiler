package miw.ast.test;

import miw.ast.Expression;
import miw.ast.Program;
import miw.ast.Reading;
import miw.ast.Variable;
import org.junit.Test;

import java.util.ArrayList;
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

    public String buildTree() {
        /* First line of code */
        List<Expression> expressions = new ArrayList<Expression>();
        expressions.add(new Variable(1, 6, "a"));
        expressions.add(new Variable(1, 8, "b"));
        Reading firstLine = new Reading(1, 1, expressions);

        /*
    }

    public String myFirstTest(){
        return "hello jUnit!";
    }

    @Test
    public void testReturnString() {
        assertEquals("hello jUnit!", myFirstTest());
    }

}