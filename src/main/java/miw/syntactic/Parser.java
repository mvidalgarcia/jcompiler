package miw.syntactic;

import miw.lexical.Lexical;

/**
 * Created by mvidalgarcia on 27/10/15.
 */
/*
 * Clase Analizador Sintáctico (Parser).
 */
public class Parser {

    // * Tokens
    public final static int CTE_INTEGER = 257;
    public final static int CTE_CHARACTER = 258;
    public final static int CTE_STRING = 273;
    public final static int IDENTIFIER = 274;

    public final static int MAIN = 259;
    public final static int VOID = 260;
    public final static int INT = 261;
    public final static int DOUBLE = 262;
    public final static int CHAR = 263;
    public final static int IF = 264;
    public final static int ELSE = 265;
    public final static int WHILE = 275;
    public final static int RETURN = 266;

    public final static int EQUAL = 267;
    public final static int LTE = 268;
    public final static int GTE = 269;
    public final static int NOTEQUAL = 270;
    public final static int AND = 271;
    public final static int OR = 272;


    // * Lexema del token devuelto (valor semántico)
    Object yylval;

    // * El yylval no es un atributo público
    public Object getYylval() {
        return yylval;
    }
    public void setYylval(Object yylval) {
        this.yylval = yylval;
    }

    /**
     * Referencia al analizador léxico
     */
    private Lexical lexico;

    // * Constructor del Sintáctico
    public Parser(Lexical lexico) {
        // * El sintático conoce al léxico
        this.lexico = lexico;
        // * El léxico conoce al sintáctico (para el yylval)
        lexico.setParser(this);
    }

}