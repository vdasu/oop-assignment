import java.util.*;
import java.math.*;

public class Combinatorics{
  
  private static void swap(char[] arr,int i,int j){
    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

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

  public static long chooseUnordered(int n,int r) throws NumberOutOfLimitsException{
  	if(r<0||n<0){
  		throw new NumberOutOfLimitsException();
  	}
  	return  choose(n,r)*factorial(r);
  }

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

  public static BigInteger chooseUnorderedBig(BigInteger n,BigInteger r) throws NumberOutOfLimitsException{
  	if(r.compareTo(BigInteger.ZERO)<0||n.compareTo(BigInteger.ZERO)<0){
  		throw new NumberOutOfLimitsException();
  	}
  	return chooseBig(n,r).multiply(factorialBig(r));
  }

}