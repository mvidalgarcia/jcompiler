// ************  Code to include ********************

package miw.lexical;
import miw.syntactic.Parser;
import miw.ast.types.TypeError;

%%
// ************  Options ********************
// % debug // * Debug option
%byaccj
%class Lexical // Generated class name
%public
%unicode
%line
%column

%{
// ************  Attributes and methods ********************
// * Syntactic Analyzer
private Parser parser;
public void setParser(Parser parser) {
	this.parser = parser;
}

// * Access to line number (yyline is package)
public int getLine() {
	// * Flex starts with zero
	return yyline+1;
}

// * Access to column num ber (yycolumn is package)
public int getColumn() {
	// * Flex starts with zero
	return yycolumn+1;
}

%}

// ************  Patterns (macros) ********************
Letter = [a-zA-Z]
Digit = [0-9]
StringConstant = \"~\"
IntegerConstant = {Digit}*
DoubleConstant = ({Digit}+\.?{Digit}*|{Digit}*\.?{Digit}+)([eE]-?{Digit}+)?
Identifier = {Letter}({Letter}|{Digit}|_)*
LineComment = "//".*
MultipleLineComment = "/*"~"*/" // ~ -> any string of values
EndLine = \r\n | \n\r | \r | \n
Trash = {LineComment}|{MultipleLineComment}|{EndLine}|[ \t\f]

%%
// ************  Actions ********************

// * Delete trash, first step
{Trash} {}

// Reserved words

"int"		{ parser.setYylval(yytext()); return Parser.INT;  }
"while"		{ parser.setYylval(yytext()); return Parser.WHILE; }
"if"		{ parser.setYylval(yytext()); return Parser.IF; }
"else"		{ parser.setYylval(yytext()); return Parser.ELSE; }
"double"	{ parser.setYylval(yytext()); return Parser.DOUBLE; }
"char"		{ parser.setYylval(yytext()); return Parser.CHAR; }
"return"	{ parser.setYylval(yytext()); return Parser.RETURN; }
"void"		{ parser.setYylval(yytext()); return Parser.VOID; }
"read"		{ parser.setYylval(yytext()); return Parser.READ; }
"write"		{ parser.setYylval(yytext()); return Parser.WRITE; }
"main"		{ parser.setYylval(yytext()); return Parser.MAIN; }




// * String constant
{StringConstant}	{parser.setYylval(yytext().replace("\"", "")); return Parser.CTE_STRING; }

// * Operators lenght 2
"=="	{parser.setYylval(yytext()); return Parser.EQUAL; }
"<="	{parser.setYylval(yytext()); return Parser.LTE; }
">="	{parser.setYylval(yytext()); return Parser.GTE; }
"!="	{parser.setYylval(yytext()); return Parser.NOTEQUAL; }
"&&"	{parser.setYylval(yytext()); return Parser.AND; }
"||"	{parser.setYylval(yytext()); return Parser.OR; }


// * Operators lenght 1
"+" |
"-"	|
">"	|
"<"	|
","	|
";"	|
"*"	|
"/"	|
"%"	|
"="	|
"!"	|
"["	|
"]"	|
"("	|
")"	|
"{"	|
"}"		{ parser.setYylval(yytext()); return (int)yycharat(0); }

// * Integer constant
{IntegerConstant}	{ parser.setYylval(new Integer(yytext())); return Parser.CTE_INTEGER; }

// * Double (optional)
{DoubleConstant} {parser.setYylval(new Double(yytext())); return Parser.CTE_DOUBLE; }


// * Identifiers
{Identifier}	{parser.setYylval(yytext()); return Parser.IDENTIFIER; }

// * Characters
'.'		{parser.setYylval(new Character(yycharat(1))); return Parser.CTE_CHARACTER; }
'\\t'	{parser.setYylval('\t'); return Parser.CTE_CHARACTER; }
'\\n'	{parser.setYylval('\n'); return Parser.CTE_CHARACTER; }
// Ascii chars
'\\{Digit}+' {parser.setYylval((char)Integer.parseInt(yytext().replace("\\", "").replace("\'", ""))); return Parser.CTE_CHARACTER; }

// . -> anything
.		{ new TypeError(getLine(), getColumn(), "Lexical error: character \'" + yycharat(0)+  "\' unknown."); }