package matlib;

/**
 * Created by shreyansh on 29/10/17.
 */
public class LimitDoesNotExistException extends Exception {
    public String toString(){
        return "Limit does not exist for this function";
    }
}
