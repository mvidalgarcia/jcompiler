%{
import miw.lexical.Lexical;
import miw.ast.*;
import java.util.*;

%}

// Not necessary for one-length tokens

%token CTE_INTEGER CTE_CHARACTER CTE_STRING CTE_DOUBLE
%token IDENTIFIER
%token MAIN VOID INT DOUBLE CHAR IF ELSE WHILE RETURN
%token EQUAL LTE GTE NOTEQUAL AND OR READ WRITE

    
// Left associative -> %left
// The order means ascending preference, last line -> most preference
// In same line, left token is done first
%right '='
%left '+' '-'
%left '*' '/'
%nonassoc '(' ')'


%%

// Grammar terminus in capitals or double quote (ID, CTE_*, etc), non grammar terminus in lowercase.
// One-length tokens with simple quotes.

program:  statements main

expression: '(' expression ')'
         | '-''(' expression ')'
         | expression '+' expression
         | expression '-' expression
         | expression '*' expression
         | expression '/' expression
         | IDENTIFIER
         | CTE_INTEGER //{ System.out.println((Integer)$1); }
         | '-' CTE_INTEGER
         | CTE_DOUBLE
         | '-' CTE_DOUBLE
         | vector_access
         ;

main: VOID MAIN '(' ')' '{' main_content '}'

main_content: statements

vector_access:   IDENTIFIER '['CTE_INTEGER']'
               | IDENTIFIER '['IDENTIFIER']'
               ;

expressions: expression
         | expressions ',' expression
         ;

statements: statement ';'
         | statements statement ';'
         ;

statement: expression '=' expression
         | WRITE expressions
         | READ expressions
         | INT expressions
         | CHAR expressions
         | DOUBLE expressions
         | vector_declaration
         ;

vector_declaration:
         | INT '['CTE_INTEGER']' IDENTIFIER
         | CHAR '['CTE_INTEGER']' IDENTIFIER
         | DOUBLE '['CTE_INTEGER']' IDENTIFIER
         ;

%%
/**
* Lexical analyzer reference
*/
private Lexical lexico;
/**
* AST reference
*/
public ASTNode ast;

// * Lexical analyzer call
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getLine()+
		" y columna "+lexico.getColumn()+":\n\t"+e);
    }
    return token;
}

// * Syntactic error handling
public void yyerror (String error) {
	// Usar manejador de errores
    System.err.println ("Syntactic error in line " + lexico.getLine()+
		", column "+lexico.getColumn()+":\n\t"+error);
}

// * Syntactic constructor
public Parser(Lexical lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}

// * The original yyparse is not public
public int parse() {
	return yyparse();
}

// * yylval is not a public attribute
public void setYylval(Object yylval) {
	this.yylval=yylval;
}

// * yylval is not a public attribute
public Object getYylval() {
	return this.yylval;
}