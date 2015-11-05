package miw.codegeneration;

import miw.ast.types.Type;

import java.io.PrintStream;
import java.io.PrintWriter;

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
        write("\tpushb\t" + c);
    }

    public void push(int i) {
        write("\tpushi\t" + i);
    }

    public void push(double d) {
        write("\tpushf\t" + d);
    }

    public void pusha(int address) {
        write("\tpusha\t" + address);
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

    public void sub(Type type) {
        write("\tsub" + type.suffix());
    }

    public void mul(Type type) {
        write("\tmul" + type.suffix());
    }

    public void div(Type type) {
        write("\tdiv" + type.suffix());
    }

    public void mod() {
        write("\tmodi");
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

    public void varDefcomment(Type type, String name, int offset) {
        comment(type.toStringCG() + " " + name + " " + "(offset " + offset + ")");
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

    public void transformType(Type t1, Type t2) {
        char t1S = t1.suffix().charAt(0);
        char t2S = t2.suffix().charAt(0);

        switch (t1S) {
            case 'i':
                if (t2S == 'b')
                    i2b();
                else if (t1S == 'f')
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
        write("\tb2i");
    }
}
