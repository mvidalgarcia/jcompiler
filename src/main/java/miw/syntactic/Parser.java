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

//#line 30 "Parser.java"




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
public final static short CTE_DOUBLE=259;
public final static short CTE_STRING=260;
public final static short IDENTIFIER=261;
public final static short MAIN=262;
public final static short VOID=263;
public final static short INT=264;
public final static short DOUBLE=265;
public final static short CHAR=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short WHILE=269;
public final static short RETURN=270;
public final static short EQUAL=271;
public final static short LTE=272;
public final static short GTE=273;
public final static short NOTEQUAL=274;
public final static short AND=275;
public final static short OR=276;
public final static short READ=277;
public final static short WRITE=278;
public final static short UNARYMINUS=279;
public final static short LOWER_THAN_ELSE=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    4,    4,    7,    7,    6,
    6,    9,    9,    8,    8,    8,   10,   10,   11,    5,
    5,   12,   12,   14,   14,   15,   13,   16,   16,   17,
   17,    3,   18,   18,   19,   19,   19,   19,   19,   19,
   19,   19,   23,   23,   22,   22,   21,   21,   20,   20,
   20,   20,   20,   20,   20,   20,   20,   20,   20,   20,
   20,   20,   20,   20,   20,   20,   20,   20,   20,   24,
   24,   24,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    2,    1,    1,    2,    1,    3,
    3,    1,    3,    1,    1,    1,    4,    4,    1,    8,
    8,    1,    0,    1,    3,    2,    2,    1,    0,    1,
    0,    7,    1,    2,    4,    3,    3,    5,    3,    5,
    7,    5,    3,    1,    1,    0,    1,    3,    1,    1,
    3,    3,    3,    3,    3,    2,    4,    3,    4,    3,
    3,    3,    3,    3,    3,    3,    3,    2,    4,    1,
    1,    1,
};
final static short yydefred[] = {                         0,
    0,   14,   16,   15,    0,    1,    0,    3,    4,    6,
    7,    0,    0,    0,    0,    2,    5,    0,    0,    0,
   12,    0,    0,    0,    0,    0,    0,   10,    0,    0,
   11,    0,    0,    0,    0,    0,   24,   17,   13,   18,
    0,    0,   26,    0,    0,    0,    9,    0,    0,    0,
    0,    0,   25,    0,    8,   32,   70,   72,   71,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   27,    0,
   33,    0,   50,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   34,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   20,   21,    0,    0,    0,    0,    0,
   39,   37,    0,   36,    0,   58,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   59,   35,   57,   38,
    0,   44,    0,   40,   69,    0,    0,   43,   41,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   10,   47,   48,   49,   20,   13,
   14,   35,   50,   36,   37,   51,   69,   70,  142,   72,
  106,  107,  143,   73,
};
final static short yysindex[] = {                      -192,
 -259,    0,    0,    0,    0,    0, -192,    0,    0,    0,
    0,  -86,  -85, -237,   -8,    0,    0,    5, -209,  -42,
    0, -204,  -29,   15,   16, -205,  -15,    0, -197,  -14,
    0, -205,  -43, -180,   43,   44,    0,    0,    0,    0,
   48, -205,    0,  -27, -205,  -21,    0, -205,  -82,  -26,
   37, -205,    0, -205,    0,    0,    0,    0,    0,   58,
   64,   67,  372,  372,  372,  372,  372,  553,    0,   37,
    0,   76,    0,  -17,  -11,  372,  372,  372,   75,  366,
  509,   -1,    6,  518,   25,   83,  390,    0,  372,  372,
  372,  372,  372,  372,  372,  372,  372,  372,  372,  372,
  372,  372,  372,    0,    0,   73,   84,  399,  406,  372,
    0,    0,  372,    0,  372,    0,   -6,   -6,   -6,   -6,
  518,  518,  427,   -6,   -6,  -24,  -24,   25,   25,   25,
  451,   72,  -33,  -33,   92,  509,    0,    0,    0,    0,
   37,    0, -129,    0,    0,  -19,  -33,    0,    0,
};
final static short yyrindex[] = {                         0,
 -121,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    7,    0,    0,
    0,    0,    0,    0,    0,  101,    0,    0,    0,    0,
    0,  101,    0,    0,    0,  109,    0,    0,    0,    0,
    0,   -5,    0,    0,    0,    0,    0,    9,    0,    0,
   26,   -5,    0,   -5,    0,    0,    0,    0,    0,  462,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   27,
    0,    0,    0,    0,    0,   48,    0,    0,   50,    0,
  -25,    0,    0,   42,   85,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  119,    0,    0,    0,   48,
    0,    0,    0,    0,    0,    0,  441,  551,  558,  564,
  216,  244,    0,  646,  713,  523,  529,  112,  121,  148,
    0,  488,    0,    0,    0,    3,    0,    0,    0,    0,
    0,    0,   23,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  154,  162,    0,    4,    0,    1,  157,    0,
    0,  145,  -32,    0,  139,    0,    0,   45,  -41,  778,
   11,   77, -109,    0,
};
final static int YYTABLESIZE=989;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         66,
   12,   29,   15,   11,   19,   22,   68,   12,   19,   71,
   11,   67,  102,   66,   29,   47,   28,  100,   47,   74,
   68,   75,  101,   24,  144,   67,   34,   29,   88,   31,
  102,   25,   34,   47,   29,  100,   98,  149,   99,   29,
  101,   28,  113,   48,   26,   34,   48,   27,   28,  113,
   12,   55,   30,   28,   32,   42,   33,  112,    2,    3,
    4,   48,   42,   39,  114,   12,  103,   42,   86,   66,
    1,    2,    3,    4,   82,   83,   68,   38,   40,   42,
   43,   67,   68,   44,  103,   68,   49,   45,   46,  141,
   49,   49,   49,   49,   49,   52,   49,   76,   56,   71,
   68,   54,   68,   77,   88,  148,   78,  104,   49,   49,
   49,   49,  102,  105,  110,  103,  113,  100,   98,   29,
   99,   56,  101,  115,  132,   56,   56,   56,   56,   56,
  140,   56,  145,   28,   68,   97,   95,   96,  147,   19,
   49,   23,   49,   56,   56,   56,   56,   42,   53,   22,
   31,   30,   53,   53,   53,   53,   53,   54,   53,   45,
   16,   54,   54,   54,   54,   54,  103,   54,   17,   23,
   53,   53,   53,   53,   18,   21,   41,   56,   21,   54,
   54,   54,   54,   53,   55,  146,  135,    0,   55,   55,
   55,   55,   55,    0,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   53,    0,   55,   55,   55,   55,
    0,    0,    0,   54,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   57,   58,   59,    0,   60,    0,    0,
    0,    0,    0,   61,    0,   62,   63,   57,   58,   59,
   55,   60,    0,   64,   65,    0,    0,   61,    0,   62,
   63,   29,   29,   29,    0,   29,   66,   64,   65,   66,
    0,   29,    0,   29,   29,   28,   28,   28,    0,   28,
    0,   29,   29,    0,   66,   28,   66,   28,   28,   42,
   42,   42,    0,   42,   67,   28,   28,   67,    0,   42,
    0,   42,   42,   57,   58,   59,    0,   60,    0,   42,
   42,    0,   67,   61,   67,   62,   63,    0,   66,    0,
    0,    0,    0,   64,   65,    0,   68,   68,    0,    0,
   49,   49,   49,   49,   49,   49,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   67,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   89,   90,   91,   92,
   93,   94,    0,    0,    0,   56,   56,   56,   56,   56,
   56,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   53,   53,   53,   53,   53,   53,    0,    0,
    0,   54,   54,   54,   54,   54,   54,    0,    0,    0,
    0,    0,  102,    0,   66,    0,    0,  100,   98,    0,
   99,   68,  101,    0,    0,    0,   67,    0,   55,   55,
   55,   55,   55,   55,  111,   97,  102,   96,    0,    0,
  116,  100,   98,    0,   99,  102,  101,    0,    0,  133,
  100,   98,  102,   99,    0,  101,  134,  100,   98,   97,
   99,   96,  101,    0,    0,    0,  103,    0,   97,    0,
   96,    0,    0,  102,    0,   97,    0,   96,  100,   98,
    0,   99,    0,  101,    0,    0,    0,    0,    0,    0,
  103,   64,    0,    0,   64,  138,   97,  102,   96,  103,
   66,   66,  100,   98,    0,   99,  103,  101,   49,   64,
   64,   64,   64,   49,   49,    0,   49,    0,   49,    0,
   97,    0,   96,    0,    0,    0,    0,  103,   67,   67,
    0,   49,   49,   49,   69,    0,    0,    0,    0,   69,
   69,    0,   69,   64,   69,    0,    0,    0,    0,    0,
    0,  103,    0,  139,    0,  102,    0,   69,   69,   69,
  100,   98,   49,   99,  102,  101,    0,    0,    0,  100,
   98,    0,   99,   51,  101,   51,   51,   51,   97,   52,
   96,   52,   52,   52,    0,    0,    0,   97,   69,   96,
    0,   51,   51,   51,   51,   66,    0,   52,   52,   52,
   52,   62,   68,    0,   62,    0,    0,   67,   63,  103,
    0,   63,    0,    0,   65,    0,    0,   65,  103,   62,
   62,   62,   62,    0,    0,   51,   63,   63,   63,   63,
    0,   52,   65,   65,   65,   65,    0,    0,   57,   58,
   59,    0,   79,    0,    0,    0,   89,   90,   91,   92,
   93,   94,    0,   62,    0,    0,    0,    0,    0,    0,
   63,    0,    0,    0,    0,    0,   65,    0,    0,    0,
   89,   90,   91,   92,   93,   94,    0,    0,    0,   89,
   90,   91,   92,   93,   94,    0,   89,   90,   91,   92,
   93,   94,    0,    0,    0,    0,   61,    0,    0,   61,
    0,    0,    0,    0,    0,    0,    0,   89,   90,   91,
   92,   93,   94,    0,   61,   61,   61,   61,    0,    0,
    0,   64,   64,   64,   64,   64,   64,    0,    0,    0,
    0,   89,   90,   91,   92,   93,   94,    0,    0,    0,
    0,    0,   49,   49,   49,   49,   49,   49,   61,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   60,    0,    0,   60,    0,   69,   69,
   69,   69,   69,   69,    0,    0,    0,    0,    0,    0,
    0,   60,   60,   60,   60,    0,    0,    0,    0,   89,
   90,   91,   92,   93,   94,    0,    0,    0,   89,   90,
   91,   92,    0,   51,   51,   51,   51,   51,   51,   52,
   52,   52,   52,   52,   52,   60,    0,    0,    0,   57,
   58,   59,    0,   79,    0,    0,    2,    3,    4,    0,
    0,   62,   62,   62,   62,   62,   62,    0,   63,   63,
   63,   63,   63,   63,   65,   65,   65,   65,   65,   65,
   80,   81,   81,   84,   85,   87,    0,    0,    0,    0,
    0,    0,    0,   81,  108,  109,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  117,  118,  119,  120,
  121,  122,  123,  124,  125,  126,  127,  128,  129,  130,
  131,    0,    0,    0,    0,    0,    0,   81,    0,    0,
  136,    0,  137,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   61,   61,   61,   61,
   61,   61,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   60,   60,   60,   60,   60,   60,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   44,  262,    0,   91,   91,   40,    7,   91,   51,
    7,   45,   37,   33,   44,   41,   59,   42,   44,   52,
   40,   54,   47,  261,  134,   45,   26,   33,   70,   59,
   37,   40,   32,   59,   40,   42,   43,  147,   45,   45,
   47,   33,   44,   41,   40,   45,   44,  257,   40,   44,
   44,   48,  257,   45,   40,   33,   41,   59,  264,  265,
  266,   59,   40,  261,   59,   59,   91,   45,   68,   33,
  263,  264,  265,  266,   64,   65,   40,   93,   93,  123,
  261,   45,   41,   41,   91,   44,   37,   44,   41,  123,
   41,   42,   43,   44,   45,  123,   47,   40,  125,  141,
   59,  123,   61,   40,  146,  125,   40,  125,   59,   60,
   61,   62,   37,  125,   40,   91,   44,   42,   43,  125,
   45,   37,   47,   41,   41,   41,   42,   43,   44,   45,
   59,   47,   41,  125,   93,   60,   61,   62,  268,  261,
   91,   41,   93,   59,   60,   61,   62,  125,   37,   41,
  125,  125,   41,   42,   43,   44,   45,   37,   47,   41,
    7,   41,   42,   43,   44,   45,   91,   47,    7,   13,
   59,   60,   61,   62,  261,  261,   32,   93,  261,   59,
   60,   61,   62,   45,   37,  141,  110,   -1,   41,   42,
   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   59,   60,   61,   62,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,   -1,  261,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  257,  258,  259,
   93,  261,   -1,  277,  278,   -1,   -1,  267,   -1,  269,
  270,  257,  258,  259,   -1,  261,   41,  277,  278,   44,
   -1,  267,   -1,  269,  270,  257,  258,  259,   -1,  261,
   -1,  277,  278,   -1,   59,  267,   61,  269,  270,  257,
  258,  259,   -1,  261,   41,  277,  278,   44,   -1,  267,
   -1,  269,  270,  257,  258,  259,   -1,  261,   -1,  277,
  278,   -1,   59,  267,   61,  269,  270,   -1,   93,   -1,
   -1,   -1,   -1,  277,  278,   -1,  275,  276,   -1,   -1,
  271,  272,  273,  274,  275,  276,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,  273,  274,
  275,  276,   -1,   -1,   -1,  271,  272,  273,  274,  275,
  276,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  271,  272,  273,  274,  275,  276,   -1,   -1,
   -1,  271,  272,  273,  274,  275,  276,   -1,   -1,   -1,
   -1,   -1,   37,   -1,   33,   -1,   -1,   42,   43,   -1,
   45,   40,   47,   -1,   -1,   -1,   45,   -1,  271,  272,
  273,  274,  275,  276,   59,   60,   37,   62,   -1,   -1,
   41,   42,   43,   -1,   45,   37,   47,   -1,   -1,   41,
   42,   43,   37,   45,   -1,   47,   41,   42,   43,   60,
   45,   62,   47,   -1,   -1,   -1,   91,   -1,   60,   -1,
   62,   -1,   -1,   37,   -1,   60,   -1,   62,   42,   43,
   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   91,   41,   -1,   -1,   44,   59,   60,   37,   62,   91,
  275,  276,   42,   43,   -1,   45,   91,   47,   37,   59,
   60,   61,   62,   42,   43,   -1,   45,   -1,   47,   -1,
   60,   -1,   62,   -1,   -1,   -1,   -1,   91,  275,  276,
   -1,   60,   61,   62,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   93,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   93,   -1,   37,   -1,   60,   61,   62,
   42,   43,   91,   45,   37,   47,   -1,   -1,   -1,   42,
   43,   -1,   45,   41,   47,   43,   44,   45,   60,   41,
   62,   43,   44,   45,   -1,   -1,   -1,   60,   91,   62,
   -1,   59,   60,   61,   62,   33,   -1,   59,   60,   61,
   62,   41,   40,   -1,   44,   -1,   -1,   45,   41,   91,
   -1,   44,   -1,   -1,   41,   -1,   -1,   44,   91,   59,
   60,   61,   62,   -1,   -1,   93,   59,   60,   61,   62,
   -1,   93,   59,   60,   61,   62,   -1,   -1,  257,  258,
  259,   -1,  261,   -1,   -1,   -1,  271,  272,  273,  274,
  275,  276,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
  271,  272,  273,  274,  275,  276,   -1,   -1,   -1,  271,
  272,  273,  274,  275,  276,   -1,  271,  272,  273,  274,
  275,  276,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,  273,
  274,  275,  276,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,  271,  272,  273,  274,  275,  276,   -1,   -1,   -1,
   -1,  271,  272,  273,  274,  275,  276,   -1,   -1,   -1,
   -1,   -1,  271,  272,  273,  274,  275,  276,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,  271,  272,
  273,  274,  275,  276,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,  271,
  272,  273,  274,  275,  276,   -1,   -1,   -1,  271,  272,
  273,  274,   -1,  271,  272,  273,  274,  275,  276,  271,
  272,  273,  274,  275,  276,   93,   -1,   -1,   -1,  257,
  258,  259,   -1,  261,   -1,   -1,  264,  265,  266,   -1,
   -1,  271,  272,  273,  274,  275,  276,   -1,  271,  272,
  273,  274,  275,  276,  271,  272,  273,  274,  275,  276,
   63,   64,   65,   66,   67,   68,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   76,   77,   78,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   89,   90,   91,   92,
   93,   94,   95,   96,   97,   98,   99,  100,  101,  102,
  103,   -1,   -1,   -1,   -1,   -1,   -1,  110,   -1,   -1,
  113,   -1,  115,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,  273,  274,
  275,  276,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  271,  272,  273,  274,  275,  276,
};
}
final static short YYFINAL=5;
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
"CTE_DOUBLE","CTE_STRING","IDENTIFIER","MAIN","VOID","INT","DOUBLE","CHAR","IF",
"ELSE","WHILE","RETURN","EQUAL","LTE","GTE","NOTEQUAL","AND","OR","READ",
"WRITE","UNARYMINUS","LOWER_THAN_ELSE",
};
final static String yyrule[] = {
"$accept : program",
"program : defs",
"defs : global_defs main",
"defs : main",
"global_defs : global_def",
"global_defs : global_defs global_def",
"global_def : func_def",
"global_def : var_def",
"var_defs : var_defs var_def",
"var_defs : var_def",
"var_def : simple_type ids ';'",
"var_def : array_type ids ';'",
"ids : IDENTIFIER",
"ids : ids ',' IDENTIFIER",
"simple_type : INT",
"simple_type : CHAR",
"simple_type : DOUBLE",
"array_type : simple_type '[' CTE_INTEGER ']'",
"array_type : array_type '[' CTE_INTEGER ']'",
"void_type : VOID",
"func_def : simple_type IDENTIFIER '(' arguments_opt ')' '{' func_body '}'",
"func_def : void_type IDENTIFIER '(' arguments_opt ')' '{' func_body '}'",
"arguments_opt : arguments",
"arguments_opt :",
"arguments : argument",
"arguments : arguments ',' argument",
"argument : simple_type IDENTIFIER",
"func_body : var_defs_opt statements_opt",
"var_defs_opt : var_defs",
"var_defs_opt :",
"statements_opt : statements",
"statements_opt :",
"main : VOID MAIN '(' ')' '{' func_body '}'",
"statements : statement",
"statements : statements statement",
"statement : expression '=' expression ';'",
"statement : WRITE expressions ';'",
"statement : READ expressions ';'",
"statement : IDENTIFIER '(' expressions_opt ')' ';'",
"statement : RETURN expression ';'",
"statement : WHILE '(' expression ')' block_body",
"statement : IF '(' expression ')' block_body ELSE block_body",
"statement : IF '(' expression ')' block_body",
"block_body : '{' statements '}'",
"block_body : statement",
"expressions_opt : expressions",
"expressions_opt :",
"expressions : expression",
"expressions : expressions ',' expression",
"expression : IDENTIFIER",
"expression : ctes",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : '-' expression",
"expression : expression '[' expression ']'",
"expression : '(' expression ')'",
"expression : '(' simple_type ')' expression",
"expression : expression '<' expression",
"expression : expression '>' expression",
"expression : expression LTE expression",
"expression : expression GTE expression",
"expression : expression EQUAL expression",
"expression : expression NOTEQUAL expression",
"expression : expression AND expression",
"expression : expression OR expression",
"expression : '!' expression",
"expression : IDENTIFIER '(' expressions_opt ')'",
"ctes : CTE_INTEGER",
"ctes : CTE_DOUBLE",
"ctes : CTE_CHARACTER",
};

//#line 225 "../src/main/java/miw/syntactic/syntactic.y"

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
//#line 566 "Parser.java"
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
//#line 45 "../src/main/java/miw/syntactic/syntactic.y"
{ ast = (ASTNode) val_peek(0); }
break;
case 2:
//#line 48 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Definition> list_defs = (List<Definition>) val_peek(1);
                         Definition main = (FunctionDef) val_peek(0);
                         list_defs.add(main);
                         yyval = new Program(lexico.getLine(), lexico.getColumn(), list_defs); }
break;
case 3:
//#line 52 "../src/main/java/miw/syntactic/syntactic.y"
{ Definition main = (FunctionDef) val_peek(0); List<Definition> list_defs = new ArrayList<Definition>();
             list_defs.add(main); yyval = new Program(lexico.getLine(), lexico.getColumn(), list_defs); }
break;
case 4:
//#line 56 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 5:
//#line 57 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Definition> definitions = (List<Definition>) val_peek(1);
                                      definitions.addAll((List<Definition>)val_peek(0));
                                      yyval = definitions; }
break;
case 6:
//#line 62 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Definition> definitions = new ArrayList<Definition>();
                       definitions.add((Definition)val_peek(0));
                       yyval = definitions; }
break;
case 7:
//#line 65 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 68 "../src/main/java/miw/syntactic/syntactic.y"
{ List<VariableDef> var_defs = (List<VariableDef>)val_peek(1);
                             List<VariableDef> var_def = (List<VariableDef>)val_peek(0);
                             var_defs.addAll(var_def); yyval = var_defs; }
break;
case 9:
//#line 71 "../src/main/java/miw/syntactic/syntactic.y"
{ List<VariableDef> var_defs = new ArrayList<VariableDef>();
                             var_defs.addAll((List<VariableDef>) val_peek(0));
                             yyval = var_defs; }
break;
case 10:
//#line 77 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Identifier> identifiers = (List<Identifier>) val_peek(1);
                                 List<VariableDef> var_defs = new ArrayList<VariableDef>();
                                 for (Identifier identifier: identifiers) {
                                    var_defs.add(new VariableDef(lexico.getLine(), lexico.getColumn(), (Type)val_peek(2), identifier.name));
                                 }
                                 yyval = var_defs;}
break;
case 11:
//#line 83 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Identifier> identifiers = (List<Identifier>) val_peek(1);
                               List<VariableDef> var_defs = new ArrayList<VariableDef>();
                               for (Identifier identifier: identifiers) {
                                    var_defs.add(new VariableDef(lexico.getLine(), lexico.getColumn(), (Type)val_peek(2), identifier.name));
                               }
                               yyval = var_defs;}
break;
case 12:
//#line 91 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Identifier> list = new ArrayList<Identifier>();
                       list.add(new Identifier(lexico.getLine(), lexico.getColumn(), (String)val_peek(0))); yyval = list; }
break;
case 13:
//#line 93 "../src/main/java/miw/syntactic/syntactic.y"
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
case 14:
//#line 104 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = TypeInteger.getInstance(lexico.getLine(), lexico.getColumn()); }
break;
case 15:
//#line 105 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = TypeChar.getInstance(lexico.getLine(), lexico.getColumn()); }
break;
case 16:
//#line 106 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = TypeDouble.getInstance(lexico.getLine(), lexico.getColumn());}
break;
case 17:
//#line 109 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new TypeArray(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(1), (Type) val_peek(3)); }
break;
case 18:
//#line 110 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new TypeArray(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(1), (Type) val_peek(3)); }
break;
case 19:
//#line 113 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = TypeVoid.getInstance(lexico.getLine(), lexico.getColumn());}
break;
case 20:
//#line 116 "../src/main/java/miw/syntactic/syntactic.y"
{
                    Type type = (Type) val_peek(7);
                    Type typeFunction = new TypeFunction(lexico.getLine(), lexico.getColumn(), type, (List<VariableDef>)val_peek(4));
                    yyval = new FunctionDef(lexico.getLine(), lexico.getColumn(), typeFunction,
                          (String) val_peek(6), (List<Statement>) val_peek(1)); }
break;
case 21:
//#line 121 "../src/main/java/miw/syntactic/syntactic.y"
{
                    Type type = (Type) val_peek(7);
                    Type typeFunction = new TypeFunction(lexico.getLine(), lexico.getColumn(), type, (List<VariableDef>)val_peek(4));
                    yyval = new FunctionDef(lexico.getLine(), lexico.getColumn(), typeFunction,
                    (String) val_peek(6), (List<Statement>) val_peek(1)); }
break;
case 22:
//#line 128 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 23:
//#line 129 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new ArrayList<VariableDef>(); }
break;
case 24:
//#line 132 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 25:
//#line 133 "../src/main/java/miw/syntactic/syntactic.y"
{ List<VariableDef> arguments = (List<VariableDef>)val_peek(2);
                                    List<VariableDef> argument = (List<VariableDef>)val_peek(0);
                                    for(VariableDef arg: arguments) {
                                        if(arg.name.equals(argument.get(0).name))
                                            yyerror("Parameter \""+ arg.name +"\" repeated.");
                                    }
                                    arguments.addAll(argument); yyval = arguments; }
break;
case 26:
//#line 142 "../src/main/java/miw/syntactic/syntactic.y"
{ List<VariableDef> list = new ArrayList<VariableDef>();
                                   list.add(new VariableDef(lexico.getLine(), lexico.getColumn(), (Type)val_peek(1), (String)val_peek(0)));
                                   yyval = list; }
break;
case 27:
//#line 147 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Statement> body = new ArrayList<Statement>();
                                         List<Statement> var_defs = (List<Statement>) val_peek(1);
                                         List<Statement> statements = (List<Statement>) val_peek(0);
                                         body.addAll(var_defs);
                                         body.addAll(statements);
                                         yyval = body; }
break;
case 28:
//#line 154 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 29:
//#line 155 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 30:
//#line 158 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 31:
//#line 159 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new ArrayList<Statement>(); }
break;
case 32:
//#line 162 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new FunctionDef(lexico.getLine(), lexico.getColumn(),
                                                TypeVoid.getInstance(lexico.getLine(), lexico.getColumn()),
                                                "main", (List<Statement>) val_peek(1)); }
break;
case 33:
//#line 166 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Statement> list = new ArrayList<Statement>(); list.add((Statement) val_peek(0)); yyval = list; }
break;
case 34:
//#line 167 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Statement> list = (List<Statement>) val_peek(1); list.add((Statement) val_peek(0)); yyval = list; }
break;
case 35:
//#line 170 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Assignment(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(3), (Expression) val_peek(1)); }
break;
case 36:
//#line 171 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Writing(lexico.getLine(), lexico.getColumn(), (List) val_peek(1)); }
break;
case 37:
//#line 172 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Reading(lexico.getLine(), lexico.getColumn(), (List) val_peek(1)); }
break;
case 38:
//#line 174 "../src/main/java/miw/syntactic/syntactic.y"
{ Identifier identifier = new Identifier(lexico.getLine(), lexico.getColumn(), (String) val_peek(4));
                                                List<Expression> expressions = (List<Expression>)val_peek(2);
                                                yyval = new InvocationStatement(lexico.getLine(), lexico.getColumn(), identifier, expressions); }
break;
case 39:
//#line 177 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Return(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(1)); }
break;
case 40:
//#line 178 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new While(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 41:
//#line 179 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new If(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 42:
//#line 180 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new If(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (List<Statement>)val_peek(0), null); }
break;
case 43:
//#line 183 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 44:
//#line 184 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Statement> list = new ArrayList<Statement>(); list.add((Statement) val_peek(0)); yyval = list; }
break;
case 45:
//#line 186 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 46:
//#line 187 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new ArrayList<Expression>(); }
break;
case 47:
//#line 190 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Expression> list = new ArrayList<Expression>(); list.add((Expression) val_peek(0)); yyval = list; }
break;
case 48:
//#line 191 "../src/main/java/miw/syntactic/syntactic.y"
{ List<Expression> list = (List<Expression>)val_peek(2); list.add((Expression) val_peek(0)); yyval = list; }
break;
case 49:
//#line 194 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Identifier(lexico.getLine(), lexico.getColumn(), (String) val_peek(0)); }
break;
case 51:
//#line 196 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "+", (Expression) val_peek(0)); }
break;
case 52:
//#line 197 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "-", (Expression) val_peek(0)); }
break;
case 53:
//#line 198 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "*", (Expression) val_peek(0)); }
break;
case 54:
//#line 199 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "/", (Expression) val_peek(0)); }
break;
case 55:
//#line 200 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "%", (Expression) val_peek(0)); }
break;
case 56:
//#line 201 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new UnaryMinus(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(0)); }
break;
case 57:
//#line 202 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new ArrayAccess(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(3), (Expression) val_peek(1)); }
break;
case 58:
//#line 203 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 59:
//#line 204 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Cast(lexico.getLine(), lexico.getColumn(), (Type)val_peek(2), (Expression)val_peek(0)); }
break;
case 60:
//#line 205 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "<", (Expression) val_peek(0)); }
break;
case 61:
//#line 206 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), ">", (Expression) val_peek(0)); }
break;
case 62:
//#line 207 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "<=", (Expression) val_peek(0)); }
break;
case 63:
//#line 208 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), ">=", (Expression) val_peek(0)); }
break;
case 64:
//#line 209 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "==", (Expression) val_peek(0)); }
break;
case 65:
//#line 210 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Comparison(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "!=", (Expression) val_peek(0)); }
break;
case 66:
//#line 211 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Logic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "&&", (Expression) val_peek(0)); }
break;
case 67:
//#line 212 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Logic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), "||", (Expression) val_peek(0)); }
break;
case 68:
//#line 213 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new Negation(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(0)); }
break;
case 69:
//#line 215 "../src/main/java/miw/syntactic/syntactic.y"
{ Identifier identifier = new Identifier(lexico.getLine(), lexico.getColumn(), (String) val_peek(3));
                                                    List<Expression> expressions = (List<Expression>)val_peek(1);
                                                    yyval = new InvocationExpression(lexico.getLine(), lexico.getColumn(), identifier, expressions); }
break;
case 70:
//#line 220 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new LiteralInteger(lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0)); }
break;
case 71:
//#line 221 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new LiteralDouble(lexico.getLine(), lexico.getColumn(), (Double) val_peek(0)); }
break;
case 72:
//#line 222 "../src/main/java/miw/syntactic/syntactic.y"
{ yyval = new LiteralCharacter(lexico.getLine(), lexico.getColumn(), (Character)val_peek(0)); }
break;
//#line 1057 "Parser.java"
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
