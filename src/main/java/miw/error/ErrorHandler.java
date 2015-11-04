package miw.error;

import miw.ast.types.TypeError;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mvidalgarcia on 27/10/15.
 */
public class ErrorHandler {
    public List<TypeError> errors;
    private static ErrorHandler instance;

    private ErrorHandler() {
        errors = new ArrayList<TypeError>();
    }

    public static ErrorHandler getInstance(){
        if (instance == null) {
            instance = new ErrorHandler();
        }
        return instance;
    }

    public boolean addError (TypeError error){
        return this.errors.add(error);
    }

    public boolean areErrors() {
        return errors.size() > 0;
    }

    public void printErrors(PrintStream out) {
        if (this.areErrors()) {
            for (TypeError error : this.errors) {
                out.println(error);
            }
        }
    }
}
