package miw;

import miw.codegeneration.ExecuteCGVisitor;
import miw.codegeneration.OffsetVisitor;
import miw.error.ErrorHandler;
import miw.ast.types.TypeError;
import miw.lexical.Lexical;
import miw.semantic.IdentificationVisitor;
import miw.semantic.SemanticVisitor;
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
//        int token;
//        while ((token=lexico.yylex()) != 0) {
//            System.out.println("Line: "+lexico.getLine()+", column: "+lexico.getColumn()+", token: "+token+", visitor value: "+parser.getYylval());
//        }

        // Parse!
        parser.run();
        // System.out.println(parser.ast);
        if (!ErrorHandler.getInstance().areErrors()) {
            parser.ast.accept(new IdentificationVisitor(), null);
            parser.ast.accept(new SemanticVisitor(), null);
        }

        if (!ErrorHandler.getInstance().areErrors()) {
            parser.ast.accept(new OffsetVisitor(), null);
            parser.ast.accept(new ExecuteCGVisitor("output" + System.currentTimeMillis() + ".txt"), args[0]);
        }

        /* Print all errors */
       ErrorHandler.getInstance().printErrors(System.err);


    }
}