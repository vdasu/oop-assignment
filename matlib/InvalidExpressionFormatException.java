package matlib;

/**
 * Exception raised when the Expression is
 * not of correct format
 *
 * @author shreyansh
 */
public class InvalidExpressionFormatException extends Exception {
    /**
     * Used to display a custom exception message
     * @return The Exception message
     */
    public String toString(){
        return "The entered expression is of invalid type";
    }
}