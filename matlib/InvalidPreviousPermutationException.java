package matlib;

class InvalidPreviousPermutationException extends Exception{
	public String toString(){
		return "Previous permutation does not exist";
	}
}