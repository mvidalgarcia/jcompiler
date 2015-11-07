package miw.codegeneration;

import miw.ast.types.Type;
import miw.ast.types.TypeArray;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * Created by mvidalgarcia on 4/11/15.
 */
public class CodeGenerator {
    //public PrintWriter out;
    public static PrintStream out;

    private void write(String s) {
        System.out.println(s);
    }

    public void main() {
        write("call main");
        write("halt");
    }

    public void functionCall(String f) {
        write("\tcall\t" + f);
    }

    public void pushBP() {
        write("\tpush\tbp");
    }

    public void push(char c) {
        write("\tpushb\t" + (int)c);
    }

    public void push(int i) {
        write("\tpushi\t" + i);
    }

    public void push(double d) {
        write("\tpushf\t" + new BigDecimal(d).toPlainString()); //Prevent E notation (ex: 3e3)
    }

    public void pusha(int address) {
        write("\tpusha\t" + address);
    }

    public void pop(Type type) {
        write("\tpop" + type.suffix());
    }

    public void load(Type type) {
        write("\tload" + type.suffix());
    }

    public void store(Type type) {
        write("\tstore" + type.suffix());
    }

    public void add(Type type) {
        write("\tadd" + type.suffix());
    }

    public void addi() {
        write("\taddi");
    }

    public void sub(Type type) {
        write("\tsub" + type.suffix());
    }

    public void mul(Type type) {
        write("\tmul" + type.suffix());
    }

    public void muli() {
        write("\tmuli");
    }

    public void div(Type type) {
        write("\tdiv" + type.suffix());
    }

    public void mod() {
        write("\tmodi");
    }

    public void gt(Type type) {
        write("\tgt" + type.suffix());
    }

    public void lt(Type type) {
        write("\tlt" + type.suffix());
    }

    public void ge(Type type) {
        write("\tge" + type.suffix());
    }

    public void le(Type type) {
        write("\tle" + type.suffix());
    }

    public void ne(Type type) {
        write("\tne" + type.suffix());
    }

    public void eq(Type type) {
        write("\teq" + type.suffix());
    }

    public void and() {
        write("\tand");
    }

    public void or() {
        write("\tor");
    }

    public void not() {
        write("\tnot");
    }

    public void out(Type type) {
        write("\tout" + type.suffix());
    }

    public void in(Type type) {
        write("\tin" + type.suffix());
    }

    public void enter(int bytes) {
        write("\tenter\t" + bytes);
    }

    public void ret(int bytesReturned, int bytesLocalVars, int bytesParams) {
        write("\tret\t" + bytesReturned + ", " + bytesLocalVars + ", " + bytesParams);
    }

    public void jz(String label) {
        write("\tjz\t" + label);
    }

    public void jmp(String label) {
        write("\tjmp\t" + label);
    }

    public void label(String label) {
        write(" " + label + ":");
    }

    public void comment(String comment) {
        write("\t' * " + comment);
    }

    public void line(int line) {
        write("#line\t" + line);
    }

    public void source(String path) {
        write("#source\t\"" + path + "\"");
    }

    public void emptyLine() {
        write("");
    }

    public String varDef(Type type, String name, int offset) {
        String s = "";
        if (type instanceof TypeArray) {

            if (((TypeArray) type).type instanceof TypeArray) {
                s += "[" + ((TypeArray) type).size + ",";
                s += varDef(((TypeArray) type).type, name, offset);
                s += "] ";
            }
            else {
                s += "[" + ((TypeArray) type).size + "," + ((TypeArray) type).type.toStringCG() +
                        "] "  + name + " (offset " + offset + ")";;
            }
        }
        else
            s += type.toStringCG() + " " + name + " " + "(offset " + offset + ")";

        return s;
    }

    public void varDefComment(Type type, String name, int offset) {
        comment(varDef(type, name, offset));
    }

    public void arithmetic(String operator, Type type) {
        switch (operator) {
            case "+":
                add(type);
                break;
            case "-":
                sub(type);
                break;
            case "*":
                mul(type);
                break;
            case "/":
                div(type);
                break;
            case "%":
                mod();
                break;
        }
    }

    public void comparison(String operator, Type type) {
        switch (operator) {
            case ">":
                gt(type);
                break;
            case "<":
                lt(type);
                break;
            case ">=":
                ge(type);
                break;
            case "<=":
                le(type);
                break;
            case "!=":
                ne(type);
                break;
            case "==":
                eq(type);
                break;
        }
    }
    public void logic(String operator) {
        switch (operator) {
            case "&&":
                and();
                break;
            case "||":
                or();
                break;
        }
    }

    public void transformType(Type t1, Type t2) {
        char t1S = t1.suffix().charAt(0);
        char t2S = t2.suffix().charAt(0);

        switch (t1S) {
            case 'i':
                if (t2S == 'b')
                    i2b();
                else if (t2S == 'f')
                    i2f();
                break;
            case 'b':
                if (t2S == 'i')
                    b2i();
                else if (t2S == 'f') {
                    b2i();
                    i2f();
                }
                break;
            case 'f':
                if (t2S == 'i')
                    f2i();
                else if (t2S == 'b') {
                    f2i();
                    i2b();
                }
                break;
        }
    }

    private void b2i() {
        write("\tb2i");
    }
    private void i2f() {
        write("\ti2f");
    }
    private void f2i() {
        write("\tf2i");
    }
    private void i2b() {
        write("\ti2b");
    }
}
