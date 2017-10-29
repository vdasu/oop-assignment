package matlib;

import java.util.*;
import java.math.*;

/**
* <h1>Statistics Module</h1>
* Statistics module is a part of the matlib package and provides functions for
* basic statistical operations.
*
* @author  Vishnu Asutosh Dasu
* @version 1.0
* @since   2017-10-29
*/

public class Statistics{

  /**
  * This method is used to find the mean of an array of type <code>double[]</code>
  *
  * @param arr First parameter to <code>mean</code> method which is array of type <code>double[]</code>
  * @return double Returns the mean of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double mean(double[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
    double sum = 0.0;
    for(double ele:arr){
      sum+=ele;
    }
    return sum/arr.length;
  }

  /**
  * This method is used to find the mean of an array of type <code>int[]</code>
  *
  * @param arr First parameter to <code>mean</code> method which is array of type <code>int[]</code>
  * @return double Returns the mean of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double mean(int[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
    double sum = 0.0;
    for(int ele:arr){
      sum+=ele;
    }
    return sum/arr.length;
  }

  /**
  * This method is used to find the mean of an array of type <code>long[]</code>
  *
  * @param arr First parameter to <code>mean</code> method which is array of type <code>long[]</code>
  * @return double Returns the mean of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double mean(long[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
    double sum = 0.0;
    for(long ele:arr){
      sum+=ele;
    }
    return sum/arr.length;
  }

  /**
  * This method is used to find the median of an array of type <code>int[]</code>
  *
  * @param arr First parameter to <code>median</code> method which is array of type <code>int[]</code>
  * @return double Returns the median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double median(int[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	int length = arr.length;
  	Arrays.sort(arr);
  	if(arr.length%2==0){
  		return (arr[length/2]+arr[length/2-1])/2;
  	}else{
  		return arr[length/2];
  	}
  }

  /**
  * This method is used to find the median of an array of type <code>long[]</code>
  *
  * @param arr First parameter to <code>median</code> method which is array of type <code>long[]</code>
  * @return double Returns the median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double median(long[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	int length = arr.length;
  	Arrays.sort(arr);
  	if(arr.length%2==0){
  		return (arr[length/2]+arr[length/2-1])/2;
  	}else{
  		return arr[length/2];
  	}
  }

  /**
  * This method is used to find the median of an array of type <code>double[]</code>
  *
  * @param arr First parameter to <code>median</code> method which is array of type <code>double[]</code>
  * @return double Returns the median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double median(double[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	int length = arr.length;
  	Arrays.sort(arr);
  	if(arr.length%2==0){
  		return (arr[length/2]+arr[length/2-1])/2;
  	}else{
  		return arr[length/2];
  	}
  }

  /**
  * This method is used to find the mode of an array of type <code>int[]</code>
  * <p>
  * In case of multiple candidates for mode, this function chooses the smallest
  * value.
  *
  * @param arr First parameter to <code>mode</code> method which is array of type <code>int[]</code>
  * @return int Returns the mode of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static int mode(int[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	int minArr = Integer.MAX_VALUE;
  	for(int i:arr){
  		if(i<minArr){
  			minArr = i;
  		}
  	}
  	int maxCount = 0;
  	int mode = minArr;
  	HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
  	for(int i:arr){
  		int currVal = i;
  		if(hm.containsKey(currVal)){
  			int currCount = hm.get(currVal);
  			currCount+=1;
  			hm.put(currVal,currCount);
  			if(currCount>maxCount){
  				mode = currVal;
  				maxCount = currCount;
  			}else if(currCount==maxCount && mode>currVal){
  				mode = currVal;
  			}
  		}else{
  			hm.put(currVal,1);
  		}
  	}
  	return mode;
  }

  /**
  * This method is used to find the mode of an array of type <code>long[]</code>
  * <p>
  * In case of multiple candidates for mode, this function chooses the smallest
  * value.
  *
  * @param arr First parameter to <code>mode</code> method which is array of type <code>long[]</code>
  * @return long Returns the mode of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static long mode(long[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	long minArr = Long.MAX_VALUE;
  	for(long i:arr){
  		if(i<minArr){
  			minArr = i;
  		}
  	}
  	int maxCount = 0;
  	long mode = minArr;
  	HashMap<Long,Integer> hm = new HashMap<Long,Integer>();
  	for(long i:arr){
  		long currVal = i;
  		if(hm.containsKey(currVal)){
  			int currCount = hm.get(currVal);
  			currCount+=1;
  			hm.put(currVal,currCount);
  			if(currCount>maxCount){
  				mode = currVal;
  				maxCount = currCount;
  			}else if(currCount==maxCount && mode>currVal){
  				mode = currVal;
  			}
  		}else{
  			hm.put(currVal,1);
  		}
  	}
  	return mode;
  }

  /**
  * This method is used to find the mode of an array of type <code>double[]</code>
  * <p>
  * In case of multiple candidates for mode, this function chooses the smallest
  * value.
  *
  * @param arr First parameter to <code>mode</code> method which is array of type <code>double[]</code>
  * @return double Returns the mode of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double mode(double[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	double minArr = Double.MAX_VALUE;
  	for(double i:arr){
  		if(i<minArr){
  			minArr = i;
  		}
  	}
  	int maxCount = 0;
  	double mode = minArr;
  	HashMap<Double,Integer> hm = new HashMap<Double,Integer>();
  	for(double i:arr){
  		double currVal = i;
  		if(hm.containsKey(currVal)){
  			int currCount = hm.get(currVal);
  			currCount+=1;
  			hm.put(currVal,currCount);
  			if(currCount>maxCount){
  				mode = currVal;
  				maxCount = currCount;
  			}else if(currCount==maxCount && mode>currVal){
  				mode = currVal;
  			}
  		}else{
  			hm.put(currVal,1);
  		}
  	}
  	return mode;
  }

  /**
  * This method is used to find the variance of an array of type <code>int[]</code>
  *
  * @param arr First parameter to <code>variance</code> method which is array of type <code>int[]</code>
  * @return double Returns the variance of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double variance(int[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	double mean = mean(arr);
  	double variance = 0.0;
  	int length = arr.length;
  	for(int i:arr){
  		variance += Math.pow(i-mean,2);
  	}
  	return variance/length;
  }

  /**
  * This method is used to find the variance of an array of type <code>long[]</code>
  *
  * @param arr First parameter to <code>variance</code> method which is array of type <code>long[]</code>
  * @return double Returns the variance of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double variance(long[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	double mean = mean(arr);
  	double variance = 0.0;
  	int length = arr.length;
  	for(long i:arr){
  		variance += Math.pow(i-mean,2);
  	}
  	return variance/length;
  }

  /**
  * This method is used to find the variance of an array of type <code>double[]</code>
  *
  * @param arr First parameter to <code>variance</code> method which is array of type <code>double[]</code>
  * @return double Returns the variance of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double variance(double[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	double mean = mean(arr);
  	double variance = 0.0;
  	int length = arr.length;
  	for(double i:arr){
  		variance += Math.pow(i-mean,2);
  	}
  	return variance/length;
  }

  /**
  * This method is used to find the standard deviation of an array of type <code>int[]</code>
  *
  * @param arr First parameter to <code>stdDeviation</code> method which is array of type <code>int[]</code>
  * @return double Returns the standard deviation of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double stdDeviation(int[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	return Math.sqrt(variance(arr));
  }

  /**
  * This method is used to find the standard deviation of an array of type <code>long[]</code>
  *
  * @param arr First parameter to <code>stdDeviation</code> method which is array of type <code>long[]</code>
  * @return double Returns the standard deviation of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double stdDeviation(long[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	return Math.sqrt(variance(arr));
  }

  /**
  * This method is used to find the standard deviation of an array of type <code>double[]</code>
  *
  * @param arr First parameter to <code>stdDeviation</code> method which is array of type <code>double[]</code>
  * @return double Returns the  standard deviation of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double stdDeviation(double[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	return Math.sqrt(variance(arr));
  }

  /**
  * This method is used to find the lower median of an array of type <code>int[]</code>
  * <p>
  * In case lower median is not defined for the array i.e array contains odd
  * number of elements, function returns median of array.
  *
  * @param arr First parameter to <code>medianLow</code> method which is array of type <code>int[]</code>
  * @return double Returns the lower median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double medianLow(int[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	if(arr.length%2==1){
  		return median(arr);
  	}else{
  		Arrays.sort(arr);
  		return arr[arr.length/2-1];
  	}
  }

  /**
  * This method is used to find the lower median of an array of type <code>long[]</code>
  * <p>
  * In case lower median is not defined for the array i.e array contains odd
  * number of elements, function returns median of array.
  *
  * @param arr First parameter to <code>medianLow</code> method which is array of type <code>long[]</code>
  * @return double Returns the lower median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double medianLow(long[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	if(arr.length%2==1){
  		return median(arr);
  	}else{
  		Arrays.sort(arr);
  		return arr[arr.length/2-1];
  	}
  }

  /**
  * This method is used to find the lower median of an array of type <code>double[]</code>
  * <p>
  * In case lower median is not defined for the array i.e array contains odd
  * number of elements, function returns median of array.
  *
  * @param arr First parameter to <code>medianLow</code> method which is array of type <code>double[]</code>
  * @return double Returns the lower median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double medianLow(double[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	if(arr.length%2==1){
  		return median(arr);
  	}else{
  		Arrays.sort(arr);
  		return arr[arr.length/2-1];
  	}
  }

  /**
  * This method is used to find the higher median of an array of type <code>int[]</code>
  * <p>
  * In case higher median is not defined for the array i.e array contains odd
  * number of elements, function returns median of array.
  *
  * @param arr First parameter to <code>medianHigh</code> method which is array of type <code>int[]</code>
  * @return double Returns the higher median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double medianHigh(int[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	if(arr.length%2==1){
  		return median(arr);
  	}else{
  		Arrays.sort(arr);
  		return arr[arr.length/2];
  	}
  }

  /**
  * This method is used to find the higher median of an array of type <code>long[]</code>
  * <p>
  * In case higher median is not defined for the array i.e array contains odd
  * number of elements, function returns median of array.
  *
  * @param arr First parameter to <code>medianHigh</code> method which is array of type <code>long[]</code>
  * @return double Returns the higher median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double medianHigh(long[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	if(arr.length%2==1){
  		return median(arr);
  	}else{
  		Arrays.sort(arr);
  		return arr[arr.length/2];
  	}
  }

  /**
  * This method is used to find the higher median of an array of type <code>double[]</code>
  * <p>
  * In case higher median is not defined for the array i.e array contains odd
  * number of elements, function returns median of array.
  *
  * @param arr First parameter to <code>medianHigh</code> method which is array of type <code>double[]</code>
  * @return double Returns the higher median of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double medianHigh(double[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	if(arr.length%2==1){
  		return median(arr);
  	}else{
  		Arrays.sort(arr);
  		return arr[arr.length/2];
  	}
  }

  /**
  * This method is used to find the harmonic mean of an array of type <code>int[]</code>
  *
  * @param arr First parameter to <code>meanHarmonic</code> method which is array of type <code>int[]</code>
  * @return double Returns the harmonic mean of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double meanHarmonic(int[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	double meanHarmonic = 0.0;
  	for(int i:arr){
  		meanHarmonic+=(1/i);
  	}
  	return arr.length/meanHarmonic;
  }

  /**
  * This method is used to find the harmonic mean of an array of type <code>long[]</code>
  *
  * @param arr First parameter to <code>meanHarmonic</code> method which is array of type <code>long[]</code>
  * @return double Returns the harmonic mean of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */ 
  public static double meanHarmonic(long[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	double meanHarmonic = 0.0;
  	for(long i:arr){
  		meanHarmonic+=(1/i);
  	}
  	return arr.length/meanHarmonic;
  }

  /**
  * This method is used to find the harmonic mean of an array of type <code>double[]</code>
  *
  * @param arr First parameter to <code>meanHarmonic</code> method which is array of type <code>double[]</code>
  * @return double Returns the harmonic mean of all array elements
  * @throws ArrayEmptyException On array size error
  * @see ArrayEmptyException
  */
  public static double meanHarmonic(double[] arr) throws ArrayEmptyException{
  	if(arr.length==0){
  		throw new ArrayEmptyException();
  	}
  	double meanHarmonic = 0.0;
  	for(double i:arr){
  		meanHarmonic+=(1/i);
  	}
  	return arr.length/meanHarmonic;
  }
}
