package matlib;

/**
 * Exception if the Array is Empty
 */
public class ArrayEmptyException extends Exception{
  /**
   * Overriding toString method to print a custom
   * exception message
   *
   * @return The exception message
   */
  @Override
  public String toString(){
    return "Array length equals to 0";
  }
}