%{
import miw.lexical.Lexical;
import miw.ast.*;
import miw.ast.expressions.*;
import miw.ast.expressions.literals.*;
import miw.ast.expressions.binary.*;
import miw.ast.expressions.unary.*;
import miw.ast.statements.*;
import miw.ast.statements.definitions.*;
import miw.ast.types.*;
import java.util.*;

%}

// Not necessary for one-length tokens

%token CTE_INTEGER CTE_CHARACTER CTE_DOUBLE CTE_STRING
%token IDENTIFIER
%token MAIN VOID INT DOUBLE CHAR IF ELSE WHILE RETURN
%token EQUAL LTE GTE NOTEQUAL AND OR READ WRITE UNARYMINUS

    
// Left associative -> %left
// The order means ascending preference, last line -> most preference
// In same line, left token is done first

%right RETURN
%nonassoc LOWER_THAN_ELSE
%nonassoc ELSE
%right '='
%left AND OR
%left '>' GTE '<' LTE NOTEQUAL EQUAL
%left '+' '-'
%left '*' '/' '%'
%right UNARYMINUS '!'
%nonassoc '[' ']'
%nonassoc '(' ')'


%%

// Grammar terminus in capitals or double quote (ID, CTE_*, etc), non grammar terminus in lowercase.
// One-length tokens with simple quotes.

program: defs { ast = (ASTNode) $1; }
       ;

defs: global_defs main { List<Definition> list_defs = (List<Definition>) $1;
                         Definition main = (FunctionDef) $2;
                         list_defs.add(main);
                         $$ = new Program(lexico.getLine(), lexico.getColumn(), list_defs); }
    | main { Definition main = (FunctionDef) $1; List<Definition> list_defs = new ArrayList<Definition>();
             list_defs.add(main); $$ = new Program(lexico.getLine(), lexico.getColumn(), list_defs); }
    ;

global_defs: global_def { $$ = $1; }
           | global_defs global_def { List<Definition> definitions = (List<Definition>) $1;
                                      definitions.addAll((List<Definition>)$2);
                                      $$ = definitions; }
            ;

global_def: func_def { List<Definition> definitions = new ArrayList<Definition>();
                       definitions.add((Definition)$1);
                       $$ = definitions; }
          | var_def  { $$ = $1; }
          ;

var_defs: var_defs var_def { List<VariableDef> var_defs = (List<VariableDef>)$1;
                             List<VariableDef> var_def = (List<VariableDef>)$2;
                             var_defs.addAll(var_def); $$ = var_defs; }
                | var_def  { List<VariableDef> var_defs = new ArrayList<VariableDef>();
                             var_defs.addAll((List<VariableDef>) $1);
                             $$ = var_defs; }
                ;

// Always returns a list of var definitions
var_def:  simple_type ids ';' { List<Identifier> identifiers = (List<Identifier>) $2;
                                 List<VariableDef> var_defs = new ArrayList<VariableDef>();
                                 for (Identifier identifier: identifiers) {
                                    var_defs.add(new VariableDef(lexico.getLine(), lexico.getColumn(), (Type)$1, identifier.name));
                                 }
                                 $$ = var_defs;}
        | array_type ids ';' { List<Identifier> identifiers = (List<Identifier>) $2;
                               List<VariableDef> var_defs = new ArrayList<VariableDef>();
                               for (Identifier identifier: identifiers) {
                                    var_defs.add(new VariableDef(lexico.getLine(), lexico.getColumn(), (Type)$1, identifier.name));
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

simple_type:     INT { $$ = TypeInteger.getInstance(lexico.getLine(), lexico.getColumn()); }
        | CHAR { $$ = TypeChar.getInstance(lexico.getLine(), lexico.getColumn()); }
        | DOUBLE { $$ = TypeDouble.getInstance(lexico.getLine(), lexico.getColumn());}
        ;

array_type: simple_type '['CTE_INTEGER']' { $$ = TypeArray.newArray(lexico.getLine(), lexico.getColumn(), (Integer) $3, (Type) $1); }
          | array_type '['CTE_INTEGER']'  { $$ = TypeArray.newArray(lexico.getLine(), lexico.getColumn(), (Integer) $3, (Type) $1); }
          ;

void_type: VOID { $$ = TypeVoid.getInstance(lexico.getLine(), lexico.getColumn());}
         ;

func_def: simple_type IDENTIFIER '(' arguments_opt ')' '{' func_body '}' {
                    Type type = (Type) $1;
                    Type typeFunction = new TypeFunction(lexico.getLine(), lexico.getColumn(), type, (List<VariableDef>)$4);
                    $$ = new FunctionDef(lexico.getLine(), lexico.getColumn(), typeFunction,
                          (String) $2, (List<Statement>) $7); }
        | void_type IDENTIFIER '(' arguments_opt ')' '{' func_body '}' {
                    Type type = (Type) $1;
                    Type typeFunction = new TypeFunction(lexico.getLine(), lexico.getColumn(), type, (List<VariableDef>)$4);
                    $$ = new FunctionDef(lexico.getLine(), lexico.getColumn(), typeFunction,
                    (String) $2, (List<Statement>) $7); }
        ;

arguments_opt: arguments { $$ = $1; }
         | { $$ = new ArrayList<VariableDef>(); }
         ;

arguments: argument { $$ = $1; }
         | arguments ',' argument { List<VariableDef> arguments = (List<VariableDef>)$1;
                                    List<VariableDef> argument = (List<VariableDef>)$3;
                                    for(VariableDef arg: arguments) {
                                        if(arg.name.equals(argument.get(0).name))
                                            yyerror("Parameter \""+ arg.name +"\" repeated.");
                                    }
                                    arguments.addAll(argument); $$ = arguments; }
         ;

argument: simple_type IDENTIFIER { List<VariableDef> list = new ArrayList<VariableDef>();
                                   list.add(new VariableDef(lexico.getLine(), lexico.getColumn(), (Type)$1, (String)$2));
                                   $$ = list; }
         ;

func_body: var_defs_opt statements_opt { List<Statement> body = new ArrayList<Statement>();
                                         List<Statement> var_defs = (List<Statement>) $1;
                                         List<Statement> statements = (List<Statement>) $2;
                                         body.addAll(var_defs);
                                         body.addAll(statements);
                                         $$ = body; }

var_defs_opt: var_defs { $$ = $1; }
            | { $$ = new ArrayList<Definition>(); }
            ;

statements_opt: statements { $$ = $1; }
              | { $$ = new ArrayList<Statement>(); }
              ;

main: VOID MAIN '(' ')' '{' func_body '}' { Type typeMain = new TypeFunction(lexico.getLine(), lexico.getColumn(),
                                                TypeVoid.getInstance(lexico.getLine(), lexico.getColumn()), new ArrayList<VariableDef>());
                                                $$ = new FunctionDef(lexico.getLine(), lexico.getColumn(),
                                                typeMain,
                                                "main", (List<Statement>) $6); }

statements: statement { List<Statement> list = new ArrayList<Statement>(); list.add((Statement) $1); $$ = list; }
         | statements statement { List<Statement> list = (List<Statement>) $1; list.add((Statement) $2); $$ = list; }
         ;

statement: expression '=' expression ';' { $$ = new Assignment(lexico.getLine(), lexico.getColumn(), (Expression) $1, (Expression) $3); }
         | WRITE expressions ';'  { $$ = new Writing(lexico.getLine(), lexico.getColumn(), (List) $2); }
         | READ expressions ';' { $$ = new Reading(lexico.getLine(), lexico.getColumn(), (List) $2); }
         // Function call in one line (as statement)
         | IDENTIFIER '(' expressions_opt ')' ';' { Identifier identifier = new Identifier(lexico.getLine(), lexico.getColumn(), (String) $1);
                                                List<Expression> expressions = (List<Expression>)$3;
                                                $$ = new InvocationStatement(lexico.getLine(), lexico.getColumn(), identifier, expressions); }
         | RETURN expression ';' { $$ = new Return(lexico.getLine(), lexico.getColumn(), (Expression) $2); }
         | WHILE '(' expression ')' block_body { $$ = new While(lexico.getLine(), lexico.getColumn(), (Expression)$3, (List<Statement>)$5); }
         | IF '(' expression ')' block_body ELSE block_body { $$ = new If(lexico.getLine(), lexico.getColumn(), (Expression)$3, (List<Statement>)$5, (List<Statement>)$7); }
         | IF '(' expression ')' block_body %prec LOWER_THAN_ELSE { $$ = new If(lexico.getLine(), lexico.getColumn(), (Expression)$3, (List<Statement>)$5, null); }
         ;

block_body: '{' statements '}' { $$ = $2; }
           | statement { List<Statement> list = new ArrayList<Statement>(); list.add((Statement) $1); $$ = list; }
           | '{' '}'   { $$ = new ArrayList<Statement>(); }

expressions_opt: expressions { $$ = $1; }
               | { $$ = new ArrayList<Expression>(); }
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
         | expression '%' expression { $$ = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) $1, "%", (Expression) $3); }
         | '-' expression %prec UNARYMINUS { $$ = new UnaryMinus(lexico.getLine(), lexico.getColumn(), (Expression) $2); }
         | expression '[' expression ']' { $$ = new ArrayAccess(lexico.getLine(), lexico.getColumn(), (Expression) $1, (Expression) $3); }
         | '(' expression ')' { $$ = $2; }
         | '(' simple_type ')' expression { $$ = new Cast(lexico.getLine(), lexico.getColumn(), (Type)$2, (Expression)$4); }
         | expression '<' expression { $$ = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) $1, "<", (Expression) $3); }
         | expression '>' expression { $$ = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) $1, ">", (Expression) $3); }
         | expression LTE expression { $$ = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) $1, "<=", (Expression) $3); }
         | expression GTE expression { $$ = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) $1, ">=", (Expression) $3); }
         | expression EQUAL expression { $$ = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) $1, "==", (Expression) $3); }
         | expression NOTEQUAL expression { $$ = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) $1, "!=", (Expression) $3); }
         | expression AND expression { $$ = new Logic(lexico.getLine(), lexico.getColumn(), (Expression) $1, "&&", (Expression) $3); }
         | expression OR expression { $$ = new Logic(lexico.getLine(), lexico.getColumn(), (Expression) $1, "||", (Expression) $3); }
         | '!' expression { $$ = new Negation(lexico.getLine(), lexico.getColumn(), (Expression) $2); }
         // Function call (as expression)
         | IDENTIFIER '(' expressions_opt ')' { Identifier identifier = new Identifier(lexico.getLine(), lexico.getColumn(), (String) $1);
                                                    List<Expression> expressions = (List<Expression>)$3;
                                                    $$ = new InvocationExpression(lexico.getLine(), lexico.getColumn(), identifier, expressions); }
         ;

ctes: CTE_INTEGER   { $$ = new LiteralInteger(lexico.getLine(), lexico.getColumn(), (Integer) $1); }
    | CTE_DOUBLE    { $$ = new LiteralDouble(lexico.getLine(), lexico.getColumn(), (Double) $1); }
    | CTE_CHARACTER { $$ = new LiteralCharacter(lexico.getLine(), lexico.getColumn(), (Character)$1); }
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
	    System.err.println ("Lexical error in line " + lexico.getLine()+
		", column "+lexico.getColumn()+":\n\t"+e);
    }
    return token;
}

// * Syntactic error handling
public void yyerror (String error) {
	// Usar manejador de errores
    new TypeError(lexico.getLine(),lexico.getColumn(), "Syntactic error\n\t"+error);
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