package Matlib;

class NumberOutOfLimitsException extends Exception{
	public String toString(){
		return "Parameter out of bounds for function";
	}
}