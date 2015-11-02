//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package miw.syntactic;



//#line 1 "../src/main/java/miw/syntactic/syntactic.y"

import miw.ast.expressions.unary.UnaryMinus;
import miw.lexical.Lexical;
import miw.ast.*;
import miw.ast.expressions.*;
import miw.ast.expressions.literals.*;
import miw.ast.statements.*;
import miw.ast.statements.definitions.*;
import miw.ast.types.*;
import java.util.*;

//#line 28 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_INTEGER=257;
public final static short CTE_CHARACTER=258;
public final static short CTE_CHAR=259;
public final static short CTE_DOUBLE=260;
public final static short CTE_STRING=261;
public final static short IDENTIFIER=262;
public final static short MAIN=263;
public final static short VOID=264;
public final static short INT=265;
public final static short DOUBLE=266;
public final static short CHAR=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short WHILE=270;
public final static short RETURN=271;
public final static short EQUAL=272;
public final static short LTE=273;
public final static short GTE=274;
public final static short NOTEQUAL=275;
public final static short AND=276;
public final static short OR=277;
public final static short READ=278;
public final static short WRITE=279;
public final static short UNARYMINUS=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    5,    5,    4,    4,    4,    4,
    2,    6,    6,    7,    7,    7,    9,    9,    8,    8,
    8,    8,    8,    8,    8,    8,    8,   10,   10,   10,
};
final static short yylen[] = {                            2,
    2,    2,    0,    3,    1,    3,    1,    1,    1,    4,
    7,    2,    3,    3,    2,    2,    1,    3,    1,    1,
    3,    3,    3,    3,    2,    4,    3,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    7,    9,    8,    1,    2,    0,    0,
    5,    0,    0,    0,    0,    4,    0,    0,   10,    6,
    0,   28,   30,   29,   19,    0,    0,    0,    0,    0,
    0,    0,   20,    0,    0,    0,    0,    0,   11,    0,
   12,    0,    0,    0,    0,    0,    0,    0,   27,   13,
    0,    0,    0,    0,    0,    0,    0,   26,
};
final static short yydgoto[] = {                          1,
    2,    7,    8,    9,   13,   30,   31,   32,   35,   33,
};
final static short yysindex[] = {                         0,
    0, -177, -261,    0,    0,    0,    0,    0,  -90,  -37,
    0, -251,  -33,  -34,  -83,    0, -250, -104,    0,    0,
  -36,    0,    0,    0,    0,  -32,  -32,  -32,  -32,  -40,
  -29,   39,    0,   33,   -9,   -9,  -55,   16,    0,  -22,
    0,  -32,  -32,  -32,  -32,  -32,  -32,  -32,    0,    0,
   33,   22,   22,  -55,  -55,  -14,   33,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   -6,  -19,   12,  -27,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   15,    6,   11,  -20,    1,    0,   24,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,    0,    0,   61,   89,   65,    0,
};
final static int YYTABLESIZE=243;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   12,   10,   14,   29,   28,   15,   18,   29,   28,   19,
   17,   20,   28,   25,   25,   25,   25,   25,   21,   25,
   23,   23,   23,   23,   23,   16,   23,   45,   43,   41,
   44,   25,   46,   25,   48,   47,   50,   17,   23,   16,
   23,   24,   24,   24,   24,   24,   21,   24,   21,   21,
   21,   22,   17,   22,   22,   22,   49,   45,   43,   24,
   44,   24,   46,   45,   21,   25,   21,   18,   46,   22,
   15,   22,   23,   14,   45,   43,   47,   44,   58,   46,
   45,   43,   18,   44,   39,   46,    3,    4,    5,    6,
   40,   36,    0,   24,    0,    0,    0,    0,   21,   42,
    0,    0,    0,   22,    0,    0,   47,    0,    0,    0,
    0,    0,   47,    0,   34,   34,   37,   38,    0,    0,
    0,    0,    0,   47,    0,    0,    0,    0,    0,   47,
   51,   52,   53,   54,   55,   56,   57,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   11,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   22,    0,   23,   24,
   22,   25,   23,   24,   22,   25,   23,   24,    0,   25,
    0,    0,    0,    0,    0,    0,    0,   26,   27,    0,
    0,   26,   27,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   91,  263,   40,   40,   45,  257,   41,   40,   45,   93,
   44,  262,   45,   41,   42,   43,   44,   45,  123,   47,
   41,   42,   43,   44,   45,   59,   47,   42,   43,   59,
   45,   59,   47,   61,   44,   91,   59,   44,   59,   59,
   61,   41,   42,   43,   44,   45,   41,   47,   43,   44,
   45,   41,   59,   43,   44,   45,   41,   42,   43,   59,
   45,   61,   47,   42,   59,   93,   61,   44,   47,   59,
   59,   61,   93,   59,   42,   43,   91,   45,   93,   47,
   42,   43,   59,   45,  125,   47,  264,  265,  266,  267,
   30,   27,   -1,   93,   -1,   -1,   -1,   -1,   93,   61,
   -1,   -1,   -1,   93,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   26,   27,   28,   29,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   91,
   42,   43,   44,   45,   46,   47,   48,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  262,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,  259,  260,
  257,  262,  259,  260,  257,  262,  259,  260,   -1,  262,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,   -1,
   -1,  278,  279,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_INTEGER","CTE_CHARACTER",
"CTE_CHAR","CTE_DOUBLE","CTE_STRING","IDENTIFIER","MAIN","VOID","INT","DOUBLE",
"CHAR","IF","ELSE","WHILE","RETURN","EQUAL","LTE","GTE","NOTEQUAL","AND","OR",
"READ","WRITE","UNARYMINUS",
};
final static String yyrule[] = {
"$accept : program",
"program : var_defs main",
"var_defs : var_defs var_def",
"var_defs :",
"var_def : type ids ';'",
"ids : IDENTIFIER",
"ids : ids ',' IDENTIFIER",
"type : INT",
"type : CHAR",
"type : DOUBLE",
"type : type '[' CTE_INTEGER ']'",
"main : VOID MAIN '(' ')' '{' statements '}'",
"statements : statement ';'",
"statements : statements statement ';'",
"statement : expression '=' expression",
"statement : WRITE expressions",
"statement : READ expressions",
"expressions : expression",
"expressions : expressions ',' expression",
"expression : IDENTIFIER",
"expression : ctes",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : '-' expression",
"expression : expression '[' expression ']'",
"expression : '(' expression ')'",
"ctes : CTE_INTEGER",
"ctes : CTE_DOUBLE",
"ctes : CTE_CHAR",
};

//#line 114 "../src/main/java/miw/syntactic/syntactic.y"

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
//#line 333 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 39 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Definition> list_defs = (List<Definition>) val_peek(1);
                          Definition main = (FunctionDef) val_peek(0);
                          list_defs.add(main);
                          ast = new Program(lexico.getLine(), lexico.getColumn(), list_defs);}
break;
case 2:
//#line 44 "../src/main/java/miw/syntactic/syntactic.y"
{ List<VariableDef> var_defs = (List<VariableDef>)val_peek(1);
                             List<VariableDef> var_def = (List<VariableDef>)val_peek(0);
                             var_defs.addAll(var_def); yyval = var_defs; }
break;
case 3:
//#line 47 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new ArrayList<VariableDef>(); }
break;
case 4:
//#line 51 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Identifier> identifiers = (List<Identifier>) val_peek(1);
                                 List<VariableDef> var_defs = new ArrayList<VariableDef>();
                                 for (Identifier identifier: identifiers) {
                                    var_defs.add(new VariableDef(lexico.getLine(), lexico.getColumn(), (Type)val_peek(2), identifier));
                                 }
                                 yyval = var_defs;}
break;
case 5:
//#line 59 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Identifier> list = new ArrayList<Identifier>();
                       list.add(new Identifier(lexico.getLine(), lexico.getColumn(), (String)val_peek(0))); yyval = list; }
break;
case 6:
//#line 61 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Identifier> list = (List<Identifier>) val_peek(2);
                               Identifier identifier = new Identifier(lexico.getLine(), lexico.getColumn(), (String)val_peek(0));
                               for (Identifier id: list) {
                                    if (id.name.equals(identifier.name)) {
                               			yyerror("Variable already defined.");
                               			throw new RuntimeException();
                               		}
                               }
                               list.add(identifier); yyval = list;}
break;
case 7:
//#line 72 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = TypeInteger.getInstance(lexico.getLine(), lexico.getColumn()); }
break;
case 8:
//#line 73 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = TypeChar.getInstance(lexico.getLine(), lexico.getColumn()); }
break;
case 9:
//#line 74 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = TypeDouble.getInstance(lexico.getLine(), lexico.getColumn());}
break;
case 10:
//#line 75 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new TypeArray(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(1), (Type) val_peek(3)); }
break;
case 11:
//#line 78 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new FunctionDef(lexico.getLine(), lexico.getColumn(),
                                                TypeVoid.getInstance(lexico.getLine(), lexico.getColumn()),
                                                new Identifier(lexico.getLine(), lexico.getColumn(), "main"),
                                                (List<Statement>) val_peek(1)); }
break;
case 12:
//#line 83 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Statement> list = new ArrayList<Statement>(); list.add((Statement) val_peek(1)); yyval = list; }
break;
case 13:
//#line 84 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Statement> list = (List<Statement>) val_peek(2); list.add((Statement) val_peek(1)); yyval = list; }
break;
case 14:
//#line 87 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Assignment(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 15:
//#line 88 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Writing(lexico.getLine(), lexico.getColumn(), (List) val_peek(0)); }
break;
case 16:
//#line 89 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Reading(lexico.getLine(), lexico.getColumn(), (List) val_peek(0)); }
break;
case 17:
//#line 93 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Expression> list = new ArrayList<Expression>(); list.add((Expression) val_peek(0)); yyval = list; }
break;
case 18:
//#line 94 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Expression> list = (List<Expression>)val_peek(2); list.add((Expression) val_peek(0)); yyval = list; }
break;
case 19:
//#line 97 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Identifier(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); }
break;
case 21:
//#line 99 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "+", (Expression) val_peek(0)); }
break;
case 22:
//#line 100 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "-", (Expression) val_peek(0)); }
break;
case 23:
//#line 101 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "*", (Expression) val_peek(0)); }
break;
case 24:
//#line 102 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "/", (Expression) val_peek(0)); }
break;
case 25:
//#line 103 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new UnaryMinus(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(0)); }
break;
case 26:
//#line 104 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new ArrayAccess (lexico.getLine(), lexico.getColumn(), (Expression) val_peek(3), (Expression) val_peek(1)); }
break;
case 27:
//#line 105 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 28:
//#line 109 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new LiteralInteger(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0)); }
break;
case 29:
//#line 110 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new LiteralDouble(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0)); }
break;
case 30:
//#line 111 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new LiteralCharacter(lexico.getLine(), lexico.getColumn(), (Character)val_peek(0)); }
break;
//#line 620 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
