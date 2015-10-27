package miw;

import miw.error.ErrorHandler;
import miw.error.TypeError;
import miw.lexical.Lexical;
import miw.syntactic.Parser;

import java.io.FileReader;
import java.io.IOException;

/**
 *  Test of lexical analizer
 */

public class Main {
    public static void main(String args[]) throws IOException {
        if (args.length < 1) {
            System.err.println("I need the input file.");
            return;
        }

        FileReader fr = null;
        try {
            fr = new FileReader(args[0]);
        } catch(IOException io) {
            System.err.println("File "+args[0]+" could not be open.");
            return;
        }

        Lexical lexico = new Lexical(fr);
        Parser parser = new Parser(lexico);
        int token;
        while ((token=lexico.yylex()) != 0) {
            System.out.println("Line: "+lexico.getLine()+", column: "+lexico.getColumn()+", token: "+token+", semantic value: "+parser.getYylval());
        }

        /*  Print errors */
        if (ErrorHandler.getInstance().areErrors()) {
            for (TypeError error : ErrorHandler.getInstance().errors) {
                System.err.println(error);
            }
        }



    }
}