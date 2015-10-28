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

    
// Asociativo de izquierdas (En orden de precedencia ascendente),
// en misma linea igual precedencia -> se hace primero lo de la izq)
%right '='
%left '+' '-'
%left '*' '/'
%nonassoc '(' ')'


%%

// Terminales de la gramática en mayus o con comillas (ID, CTE_*, etc), no terminales en minus.
// Tokens de longitud 1 entre comillas simples

program:  statements main

expression: '(' expression ')'
         | '-''(' expression ')'
         | expression '+' expression
         | expression '-' expression
         | expression '*' expression
         | expression '/' expression
         | IDENTIFIER
         | CTE_INTEGER
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
* Referencia al analizador léxico
*/
private Lexical lexico;
/**
* Referencia al ast
*/
public ASTNode ast;

// * Llamada al analizador léxico
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

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
	// Usar manejador de errores
    System.err.println ("Error Sintáctico en línea " + lexico.getLine()+
		" y columna "+lexico.getColumn()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Lexical lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}

// * El yyparse original no es público
public int parse() {
	return yyparse();
}

// * El yylval no es un atributo público
public void setYylval(Object yylval) {
	this.yylval=yylval;
}

// * El yylval no es un atributo público
public Object getYylval() {
	return this.yylval;
}