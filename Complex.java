package complex;


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
	 * Constructors
	 */
	public Complex() {
		this.real = 0;
		this.imag = 0;
		polar();
	}

	public Complex(Complex object) {
		this.real = object.real;
		this.imag = object.imag;
		this.mod = object.mod;
		this.argument = object.argument;
	}
	
	public Complex(double real, double imag) {
		this.real = real;
		this.imag = imag;
		polar();
	}

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

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
		polar();
	}

	public double getImag() {
		return imag;
	}

	public void setImag(double imag) {
		this.imag = imag;
		polar();
	}

	public double getMod() {
		return mod;
	}

	public void setMod(double mod) {
		this.mod = mod;
		rectangular();
	}

	public double getArgument() {
		return argument;
	}

	public void setArgument(double argument) {
		this.argument = argument;
		rectangular();
	}

	/************************************************/

	/*
	 * Operations
	 */
	
	public Complex times(int constant) {
		return new Complex(this.real*constant, this.imag*constant, true);
	}

	public Complex conjugate() {
		return new Complex(this.real, -this.imag, true);
	}

	public Complex add(Complex summand) {
		return new Complex(this.real + summand.real, this.imag + summand.imag, true);
	}

	public Complex subtract(Complex subtrahend) {
		return new Complex(this.real - subtrahend.real, this.imag - subtrahend.imag, true);
	}

	public Complex multiply(Complex multiplicand) {
		double real = (this.real * multiplicand.real) - (this.imag * multiplicand.imag);
		double imag = (this.real * multiplicand.real) + (this.imag * multiplicand.imag);
		return new Complex(real, imag, true);
	}
	
	public Complex inverse() {
		Complex conj = this.conjugate();
		double real = conj.getReal()/conj.getMod();
		double imag = conj.getImag()/conj.getMod();
		return new Complex(real, imag, true);
	}
	
	public Complex divide(Complex divisor) {
		divisor = divisor.inverse();
		return this.multiply(divisor);
	}

	public Complex addAll(Complex[] array) {
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
	
	//return e^z where z is an complex number
	public Complex exponent() {
		double realExp = Math.exp(this.real);
		return new Complex(realExp*Math.cos(this.imag), realExp*Math.sin(this.imag), true);
	}
	
	//return ln(z) (natural log) where z is a complex number
	public Complex ln() {
		return new Complex(Math.log(this.getMod()), this.getArgument(), true);
	}
	
	public Complex square() {
		return this.multiply(this);
	}
	
	public Complex sqrt() {
		double real = Math.sqrt((this.real+this.getMod())/2);
		double imag = (this.imag/Math.abs(this.imag)) * (Math.sqrt((-this.real+this.getMod()/2)));
		return new Complex(real, imag, true);
	}
	
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
	
	public String getRectangular() {
		String rect;
		if (this.imag < 0) {
			rect = this.real + " - i" + Math.abs(this.imag);
		} else {
			rect = this.real + " + i" + this.imag;
		}
		return rect;
	}
	
	public String getPolar() {
		return this.getMod() + " ( cos(" + this.getArgument() + ") + isin(" + this.argument + ") )"; 
	}
	
	public String toString() {
		return "Rectangular: " + this.getRectangular() + "\nPolar: " + this.getPolar();
	}
	
	/*******************************************************/
}


