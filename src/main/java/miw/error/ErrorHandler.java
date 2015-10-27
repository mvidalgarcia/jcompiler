package miw.error;

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
}
