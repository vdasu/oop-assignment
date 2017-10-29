package matlib;

/**
 * Exception raised when the number is
 * out of limits
 */
class NumberOutOfLimitsException extends Exception{
	/**
	 * Used to display a custom exception message
	 * @return The Exception message
	 */
	public String toString(){
		return "Parameter out of bounds for function";
	}
}