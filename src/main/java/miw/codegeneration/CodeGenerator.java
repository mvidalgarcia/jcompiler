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

    public void source(String fileName) {
        write("#source\t\"" + fileName + "\"");
    }

    public void emptyLine() {
        write("");
    }

    public void varDef(Type type, String name, int offset) {
        comment(type.toStringCG() + " " + name + " " + "(offset " + offset + ")");
    }
}
