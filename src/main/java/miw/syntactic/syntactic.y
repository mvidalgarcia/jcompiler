%{
import miw.lexical.Lexical;
import miw.ast.*;
import miw.ast.expressions.*;
import miw.ast.expressions.literals.*;
import miw.ast.statements.*;
import miw.ast.statements.definitions.*;
import miw.ast.types.*;
import java.util.*;

%}

// Not necessary for one-length tokens

%token CTE_INTEGER CTE_CHARACTER CTE_CHAR CTE_DOUBLE CTE_STRING
%token IDENTIFIER
%token MAIN VOID INT DOUBLE CHAR IF ELSE WHILE RETURN
%token EQUAL LTE GTE NOTEQUAL AND OR READ WRITE UNARYMINUS

    
// Left associative -> %left
// The order means ascending preference, last line -> most preference
// In same line, left token is done first
%right '='
%left '+' '-'
%left '*' '/'
%right UNARYMINUS
%nonassoc '[' ']'
%nonassoc '(' ')'


%%

// Grammar terminus in capitals or double quote (ID, CTE_*, etc), non grammar terminus in lowercase.
// One-length tokens with simple quotes.

program:  var_defs main { List<Definition> list_defs = (List<Definition>) $1;
                          Definition main = (FunctionDef) $2;
                          list_defs.add(main);
                          ast = new Program(lexico.getLine(), lexico.getColumn(), list_defs); System.out.println(ast);}

var_defs: var_defs var_def { List<VariableDef> var_defs = (List<VariableDef>)$1;
                             List<VariableDef> var_def = (List<VariableDef>)$2;
                             var_defs.addAll(var_def); $$ = var_defs; }
                | { $$ = new ArrayList<VariableDef>(); }
                ;

// Always returns a list of var definitions
var_def:  type ids ';' { List<Identifier> identifiers = (List<Identifier>) $2;
                                 List<VariableDef> var_defs = new ArrayList<VariableDef>();
                                 for (Identifier identifier: identifiers) {
                                    var_defs.add(new VariableDef(lexico.getLine(), lexico.getColumn(), (Type)$1, identifier));
                                 }
                                 $$ = var_defs;}
                ;

ids:      IDENTIFIER { List<Identifier> list = new ArrayList<Identifier>();
                       list.add(new Identifier(lexico.getLine(), lexico.getColumn(), (String)$1)); $$ = list; }
        | ids',' IDENTIFIER  { List<Identifier> list = (List<Identifier>) $1;
                               Identifier identifier = new Identifier(lexico.getLine(), lexico.getColumn(), (String)$3);
                               for (Identifier id: list) {
                                    if (id.name.equals(identifier.name)) {
                               			yyerror("Variable already defined.");
                               			throw new RuntimeException();
                               		}
                               }
                               list.add(identifier); $$ = list;}
        ;

type:     INT { $$ = TypeInteger.getInstance(lexico.getLine(), lexico.getColumn()); }
        | CHAR { $$ = TypeChar.getInstance(lexico.getLine(), lexico.getColumn()); }
        | DOUBLE { $$ = TypeDouble.getInstance(lexico.getLine(), lexico.getColumn());}
        | type '['CTE_INTEGER']' { $$ = new TypeArray(lexico.getLine(), lexico.getColumn(), (Integer) $3, (Type) $1); }
        ;

main: VOID MAIN '(' ')' '{' statements '}' { $$ = new FunctionDef(lexico.getLine(), lexico.getColumn(),
                                                TypeVoid.getInstance(lexico.getLine(), lexico.getColumn()),
                                                new Identifier(lexico.getLine(), lexico.getColumn(), "main"),
                                                (List<Statement>) $6); }

statements: statement ';' { List<Statement> list = new ArrayList<Statement>(); list.add((Statement) $1); $$ = list; }
         | statements statement ';' { List<Statement> list = (List<Statement>) $1; list.add((Statement) $2); $$ = list; }
         ;

statement: expression '=' expression { $$ = new Assignment(lexico.getLine(), lexico.getColumn(), (Expression) $1, (Expression) $3); }
         | WRITE expressions  { $$ = new Writing(lexico.getLine(), lexico.getColumn(), (List) $2); }
         | READ expressions { $$ = new Reading(lexico.getLine(), lexico.getColumn(), (List) $2); }
         // ID'(' expressions ')' ';' -> llamada funcion en una linea
         ;

expressions: expression  { List<Expression> list = new ArrayList<Expression>(); list.add((Expression) $1); $$ = list; }
           | expressions ',' expression { List<Expression> list = (List<Expression>)$1; list.add((Expression) $3); $$ = list; }
           ;

expression: IDENTIFIER { $$ = new Identifier(lexico.getLine(), lexico.getColumn(), (String) $1); }
         | ctes
         | expression '+' expression { $$ = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) $1, "+", (Expression) $3); }
         | expression '-' expression { $$ = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) $1, "-", (Expression) $3); }
         | expression '*' expression { $$ = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) $1, "*", (Expression) $3); }
         | expression '/' expression { $$ = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) $1, "/", (Expression) $3); }
         | '-' expression %prec UNARYMINUS { $$ = new UnaryMinus(lexico.getLine(), lexico.getColumn(), (Expression) $2); }
         | expression '[' expression ']' { $$ = new ArrayAccess (lexico.getLine(), lexico.getColumn(), (Expression) $1, (Expression) $3); }
         | '(' expression ')' { $$ = $2; }
         // ID '(' expressions ')'
         ;

ctes: CTE_INTEGER   { $$ = new LiteralInteger(lexico.getLine(), lexico.getColumn(), (Integer) $1); }
    | CTE_DOUBLE    { $$ = new LiteralDouble(lexico.getLine(), lexico.getColumn(), (Double) $1); }
    | CTE_CHAR      { $$ = new LiteralCharacter(lexico.getLine(), lexico.getColumn(), (Character)$1); }
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
// Visitor visitor = new VisitorX(...);
// visitor.visit(ast);

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