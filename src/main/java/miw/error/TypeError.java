package miw.error;

/**
 * Created by mvidalgarcia on 27/10/15.
 */
public class TypeError {
    public Integer line;
    public Integer column;
    public String description;

    public TypeError(Integer line, Integer column, String description) {
        this.line = line;
        this.column = column;
        this.description = description;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public String toString() {
        return "Lexical error in line " + line +
                ", column " + column +
                ", character \'" + description + "\' unknown.";
    }
}
