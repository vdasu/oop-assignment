package matlib;

import java.math.*;

/**
* <h1>Combinatorics Module</h1>
* Combinatorics module is a part of the matlib package and provides functions for
* basic combinatorics operations.
*
* @author  Vishnu Asutosh Dasu
* @version 1.0
* @since   2017-10-29
*/

public class Combinatorics{

  /**
  * This helper method is used to swap two elements in a <code>char[]</code> array
  *
  * @param arr First parameter to <code>swap</code> method which is array of type <code>char[]</code>
  * @param i Second parameter to <code>swap</code> method is index of element to be swapped of type <code>int</code>
  * @param j Third parameter to <code>swap</code> method is index of element to be swapped of type <code>int</code>
  */ 
  private static void swap(char[] arr,int i,int j){
    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /**
  * This method is used to find the next permutation of a string of type <code>String</code>
  *
  * @param str First parameter to <code>nextPermutation</code> method which is a string
  * @return String Returns the lexicographically next permutation of the string elements
  * @throws InvalidNextPermutationException On absence of valid next permutation
  * @see InvalidNextPermutationException
  */ 
  public static String nextPermutation(String str) throws InvalidNextPermutationException{
    char[] charArray = str.toCharArray();
    int i = charArray.length - 1;

    while(i>0 && charArray[i-1]>=charArray[i]){
    	i--;
    }
    if(i<=0){
    	throw new InvalidNextPermutationException();
    }

    int j = charArray.length - 1;

    while(charArray[j]<=charArray[i-1]){
    	j--;
    }

    swap(charArray,i-1,j);

    j = charArray.length - 1;

    while(i<j){
    	swap(charArray,i,j);
    	i++;
    	j--;
    }

    return new String(charArray);
  }

  /**
  * This method is used to find the previous permutation of a string of type <code>String</code>
  *
  * @param str First parameter to <code>previousPermutation</code> method which is a string
  * @return String Returns the lexicographically previous permutation of the string elements
  * @throws InvalidPreviousPermutationException On absence of valid previous permutation
  */ 
  public static String previousPermutation(String str) throws InvalidPreviousPermutationException{
  	char[] charArray = str.toCharArray();
  	int len = charArray.length - 1;
  	int i = len;

  	while(i>0 && charArray[i-1]<=charArray[i]){
  		i--;
  	}

  	if(i<=0){
  		throw new InvalidPreviousPermutationException();
  	}

  	int j = i - 1;
  	while(j + 1<=len && charArray[j + 1]<=charArray[i - 1]){
  		j++;
  	}

  	swap(charArray,i-1,j);

  	String st = new String(charArray);
  	String ans = st.substring(0,i);
  	ans += new StringBuilder(st.substring(i,st.length())).reverse().toString();
  	ans += st.substring(st.length());
  	return ans;
  }

  /**
  * This method is used to find the factorial of an integer
  *
  * @param n First parameter to <code>factorial</code> method which is of type <code>int</code>
  * @return long Returns the factorial of the input
  * @throws NumberOutOfLimitsException On number out of valid range
  * @see NumberOutOfLimitsException
  */ 
  public static long factorial(int n) throws NumberOutOfLimitsException{
  	if(n<0){
  		throw new NumberOutOfLimitsException();
  	}
    if(n==0){
      return 1;
    }else{
      return n*factorial(n-1);
    }
  }

  /**
  * This method is used to find the factorial of a BigInteger
  *
  * @param n First parameter to <code>factorialBig</code> method which is of type <code>BigInteger</code>
  * @return BigInteger Returns the factorial of the input
  * @throws NumberOutOfLimitsException On number out of valid range
  * @see NumberOutOfLimitsException
  */ 
  public static BigInteger factorialBig(BigInteger n) throws NumberOutOfLimitsException{
  	if(n.compareTo(BigInteger.ZERO)<0){
  		throw new NumberOutOfLimitsException();
  	}
  	if (n.equals(BigInteger.ZERO)){
    	return BigInteger.ONE;
    }else{
    	return n.multiply(factorialBig(n.subtract(BigInteger.ONE)));
    }
  }

  /**
  * This method is used to find the nCr of the inputs of type int
  *
  * @param n First parameter to <code>choose</code> method which is of type <code>int</code>
  * @param r Second parameter to <code>choose</code> method which is of type <code>int</code>
  * @return long Returns the nCr of the inputs
  * @throws NumberOutOfLimitsException On number out of valid range
  * @see NumberOutOfLimitsException
  */ 
  public static long choose(int n,int r) throws NumberOutOfLimitsException{
  	if(r<0||n<0){
  		throw new NumberOutOfLimitsException();
  	}
    if(r==0||r==n){
      return 1;
    }else{
      return choose(n-1,r-1) + choose(n-1,r);
    }
  }

  /**
  * This method is used to find the nPr of the inputs of type int
  *
  * @param n First parameter to <code>chooseUnordered</code> method which is of type <code>int</code>
  * @param r Second parameter to <code>chooseUnordered</code> method which is of type <code>int</code>
  * @return long Returns the nPr of the inputs
  * @throws NumberOutOfLimitsException On number out of valid range
  * @see NumberOutOfLimitsException
  */ 
  public static long chooseUnordered(int n,int r) throws NumberOutOfLimitsException{
  	if(r<0||n<0){
  		throw new NumberOutOfLimitsException();
  	}
  	return  choose(n,r)*factorial(r);
  }

  /**
  * This method is used to find the nCr of the inputs of type BigInteger
  *
  * @param n First parameter to <code>chooseBig</code> method which is of type <code>BigInteger</code>
  * @param r Second parameter to <code>chooseBig</code> method which is of type <code>BigInteger</code>
  * @return BigInteger Returns the nCr of the inputs
  * @throws NumberOutOfLimitsException On number out of valid range
  * @see NumberOutOfLimitsException
  */ 
  public static BigInteger chooseBig(BigInteger n, BigInteger r) throws NumberOutOfLimitsException{
  	if(r.compareTo(BigInteger.ZERO)<0||n.compareTo(BigInteger.ZERO)<0){
  		throw new NumberOutOfLimitsException();
  	}
  	if(r.equals(BigInteger.ZERO)||n.equals(r)){
  		return BigInteger.ONE;
  	}else{
  		return chooseBig(n.subtract(BigInteger.ONE),r.subtract(BigInteger.ONE))
  		.add(chooseBig(n.subtract(BigInteger.ONE),r));
  	}
  }

   /**
  * This method is used to find the nPr of the inputs of type BigInteger
  *
  * @param n First parameter to <code>chooseUnorderedBig</code> method which is of type <code>BigInteger</code>
  * @param r Second parameter to <code>chooseUnorderedBig</code> method which is of type <code>BigInteger</code>
  * @return BigInteger Returns the nCr of the inputs
  * @throws NumberOutOfLimitsException On number out of valid range
  * @see NumberOutOfLimitsException
  */ 
  public static BigInteger chooseUnorderedBig(BigInteger n,BigInteger r) throws NumberOutOfLimitsException{
  	if(r.compareTo(BigInteger.ZERO)<0||n.compareTo(BigInteger.ZERO)<0){
  		throw new NumberOutOfLimitsException();
  	}
  	return chooseBig(n,r).multiply(factorialBig(r));
  }
}