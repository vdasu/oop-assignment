/**
 * <h1>Complex Numbers Module</h1>
 * Complex is a part of the matlib package and provides functions for basic
 * complex arithmetic.
 *
 * @author rajan
 * @version 1.0
 * @since 2017-10-29
 */

public class Complex {

	/*
	 * variables
	 */
	private double real;
	private double imag;

	private double mod;
	private double argument;

	/************************************************/

	/*
	 * Calculate
	 */

	private void calculateMod() {
		this.mod = Math.sqrt((this.real) * (this.real) + (this.imag) * (this.imag));
	}

	private void calculateArgument() {
		this.argument = Math.atan2(this.imag, this.real);
	}

	private void polar() {
		calculateMod();
		calculateArgument();
	}

	private void calculateReal() {
		this.real = this.mod * (Math.cos(this.argument));
	}

	private void calculateImaginary() {
		this.imag = this.mod * (Math.sin(this.argument));
	}

	private void rectangular() {
		calculateReal();
		calculateImaginary();
	}

	/***************************************************/

	/*
		Constructors
	 */

	public Complex() {
		this.real = 0;
		this.imag = 0;
		polar();
	}

	/**
	 * This constructors takes another Complex object as a parameter and creates a copy
	 *
	 * @param object complex object whose copy should be created
	 */
	public Complex(Complex object) {
		this.real = object.real;
		this.imag = object.imag;
		this.mod = object.mod;
		this.argument = object.argument;
	}


	/**
	 * This constructor takes real and imaginary part of a complex number as the parameters
	 *
	 * @param real real part of the complex number
	 * @param imag imaginary part of the complex number
	 */
	public Complex(double real, double imag) {
		this.real = real;
		this.imag = imag;
		polar();
	}

	/**
	 * This constructor takes three arguments:
	 *  1. <code>double</code> arg1
	 *  2. <code>double</code> arg2
	 *  3. <code>boolean</code> rect
	 * If rect equals <code>true</code>, then the arg1 is the real part and arg2 is the imaginary part
	 * else arg1 is the modulus and arg2 is the argument of the complex number.
	 * @param arg1 of the form <code>double</code>
	 * @param arg2 of the form <code>double</code>
	 * @param rect of the form <code>boolean</code>
	 */
	public Complex(double arg1, double arg2, boolean rect) {
		if (rect) {
			this.real = arg1;
			this.imag = arg2;
			polar();
		} else {
			this.mod = arg1;
			this.argument = arg2;
			rectangular();
		}
	}

	/**************************************************/

	/*
	 * Getters and setters
	 */
	/** To get the real part of the complex number
	 *
	 * @return real part of the complex number
	 */
	public double getReal() {
		return real;
	}

	/**To set the real part of the complex number
	 *
	 * @param real real part of the complex number
	 */
	public void setReal(double real) {
		this.real = real;
		polar();
	}

	/**To get the imaginary part of the complex number
	 *
	 * @return imaginary part of the complex number
	 */
	public double getImag() {
		return imag;
	}

	/**T set the imaginary part of the complex number
	 *
	 * @param imag imaginary part of the complex number
	 */
	public void setImag(double imag) {
		this.imag = imag;
		polar();
	}

	/**To get the modulus of the complex number
	 *
	 * @return modulus of the complex number
	 */
	public double getMod() {
		return mod;
	}

	/**To set the modulus of the complex number
	 *
	 * @param mod modulus of the complex number
	 */
	public void setMod(double mod) {
		this.mod = mod;
		rectangular();
	}

	/**To get the argument of the complex number
	 *
	 * @return argument of the complex number in radians
	 */
	public double getArgument() {
		return argument;
	}

	/**To set the argument of the complex number
	 *
	 * @param argument argument of the complex number in radians
	 */
	public void setArgument(double argument) {
		this.argument = argument;
		rectangular();
	}

	/************************************************/

	/*
	 * Operations
	 */

	/**
	 * To multiply a constant with a complex number
	 *
	 * @param constant constant which should be multiplied with the complex number
	 * @return product of the constant and complex number
	 */
	public Complex times(double constant) {
		return new Complex(this.real*constant, this.imag*constant, true);
	}

	/**To get the conjugate of a complex number
	 *
	 * @return conjugate
	 */
	public Complex conjugate() {
		return new Complex(this.real, -this.imag, true);
	}

	/**To add two complex numbers
	 *
	 * @param summand Complex number which should be added to the complex number
	 * @return sum of the two complex numbers
	 */
	public Complex add(Complex summand) {
		return new Complex(this.real + summand.real, this.imag + summand.imag, true);
	}

	/**To subtract two complex numbers
	 *
	 * @param subtrahend Complex number which should be subtracted from the complex number
	 * @return difference of the two complex numbers
	 */
	public Complex subtract(Complex subtrahend) {
		return new Complex(this.real - subtrahend.real, this.imag - subtrahend.imag, true);
	}

	/**To multiply two complex numbers
	 *
	 * @param multiplicand Complex number you want your number to be multiplied with
	 * @return product of the two complex numbers
	 */
	public Complex multiply(Complex multiplicand) {
		double real = (this.real * multiplicand.real) - (this.imag * multiplicand.imag);
		double imag = (this.real * multiplicand.real) + (this.imag * multiplicand.imag);
		return new Complex(real, imag, true);
	}

	/**To find the inverse of the complex number
	 *
	 * @return inverse of the complex number
	 */
	public Complex inverse() {
		Complex conj = this.conjugate();
		double real = conj.getReal()/conj.getMod();
		double imag = conj.getImag()/conj.getMod();
		return new Complex(real, imag, true);
	}

	/**To divide two complex numbers
	 *
	 * @param divisor Complex object which should be your divisor
	 * @return quotient of the two complex numbers
	 */
	public Complex divide(Complex divisor) {
		divisor = divisor.inverse();
		return this.multiply(divisor);
	}

	/**To add an array of complex numbers
	 *
	 * @param array <code>Complex[]</code> array whose sum you want to get
	 * @return the sum of the array
	 */
	public static Complex addAll(Complex[] array) {
		Complex sum = new Complex();

		Thread realThread = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < array.length; i++) {
					sum.real += array[i].real;
				}
			}
		});

		Thread imagThread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i=0; i < array.length; i++) {
					sum.imag += array[i].imag;
				}
			}
		});

		realThread.start();
		imagThread.start();

		try {
			realThread.join();
			imagThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return sum;
	}

	/**To apply exponential function on a complex number
	 *
	 * @return e^z where z is an complex number
	 */
	public Complex exponent() {
		double realExp = Math.exp(this.real);
		return new Complex(realExp*Math.cos(this.imag), realExp*Math.sin(this.imag), true);
	}

	/**To apply logarithmic functional on a complex number
	 *
	 * @return ln(z) (natural log) where z is a complex number
	 */
	public Complex ln() {
		return new Complex(Math.log(this.getMod()), this.getArgument(), true);
	}

	/**To get the square of a complex number
	 *
	 * @return square of a complex number
	 */
	public Complex square() {
		return this.multiply(this);
	}

	/**To get the square root of a complex number
	 *
	 * @return square root of a complex number
	 */
	public Complex sqrt() {
		double real = Math.sqrt((this.real+this.getMod())/2);
		double imag = (this.imag/Math.abs(this.imag)) * (Math.sqrt((-this.real+this.getMod()/2)));
		return new Complex(real, imag, true);
	}

	/**To raise a complex number to a power
	 *
	 * @param pow power to which you want to raise your complex number of type <code>int</code>
	 * @return z^k where z is a complex number and k is the parameter constant
	 */
	public Complex power(int pow) {
		Complex result = new Complex(1, 0, true);
		int parts;
		if (pow%2==0) {
			parts = pow/2;
		} else {
			parts = (pow-1)/2;
			result = result.multiply(this);
		}


		class Processor extends Thread {
			Complex given;
			Complex square;
			Processor(Complex given) {
				this.given = given;
			}
			public void run() {
				square = given.square();
			}
			public Complex getSquare() {
				return square;
			}
		}

		Processor[] threads = new Processor[parts];

		for (int i=0; i<parts; i++) {
			threads[i] = new Processor(this);
		}

		for (Processor t: threads) {
			t.start();
		}

		try {
			for (Processor t: threads) {
				t.join();
				result = result.multiply(t.getSquare());
			}
		} catch(InterruptedException e) {
			System.out.println(e);
		}

		return result;

	}


	/********************************************************/

	/*
	 * Description methods
	 */

	/**
	 * To get the rectangular form of a complex number
	 *
	 * @return rectangular form of the complex number in <code>String</code> format
	 */

	public String getRectangular() {
		String rect;
		if (this.imag < 0) {
			rect = this.real + " - i" + Math.abs(this.imag);
		} else {
			rect = this.real + " + i" + this.imag;
		}
		return rect;
	}

	/**To get the polar form of a complex number
	 *
	 * @return polar form of the complex number in <code>String</code> format
	 */

	public String getPolar() {
		return this.getMod() + " ( cos(" + this.getArgument() + ") + isin(" + this.argument + ") )";
	}

	/**Overrides <code>toString()</code> method from Object class
	 *
	 * @return both the rectangular and polar in <code>String</code> format
	 */
	public String toString() {
		return "Rectangular: " + this.getRectangular() + "\nPolar: " + this.getPolar();
	}

	/*******************************************************/
}
