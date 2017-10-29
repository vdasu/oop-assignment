package matlib;

/**
 * Exception raised when no previous permutation
 * exists
 *
 * @author vishnu
 */
class InvalidPreviousPermutationException extends Exception{
	/**
	 * Used to display a custom exception message
	 * @return The Exception message
	 */
	public String toString(){
		return "Previous permutation does not exist";
	}
}