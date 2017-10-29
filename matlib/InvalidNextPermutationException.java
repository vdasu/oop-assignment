package matlib;

/**
 * Exception raised when the next permutation
 * does not exist
 *
 * @author vishnu
 */
class InvalidNextPermutationException extends Exception{
	/**
	 * Used to display a custom exception message
	 * @return The Exception message
	 */
	public String toString(){
		return "Next permutation does not exist";
	}
}