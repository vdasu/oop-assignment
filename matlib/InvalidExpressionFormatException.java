package matlib;

/**
 * Created by shreyansh on 29/10/17.
 */
public class InvalidExpressionFormatException extends Exception {
    public String toString(){
        return "The entered expression is of invalid type";
    }
}