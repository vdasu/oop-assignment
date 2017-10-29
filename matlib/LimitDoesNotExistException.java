package matlib;

/**
 * Exception raised when no limit exists for a function
 *
 * @author shreyansh
 */
public class LimitDoesNotExistException extends Exception {
    /**
     * Used to display a custom exception message
     * @return The Exception message
     */
    public String toString(){
        return "Limit does not exist for this function";
    }
}
