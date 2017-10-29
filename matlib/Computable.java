package matlib;

/**
 * Abstract class. Base for Calculus operations
 *
 * @author shreyansh
 */
public abstract class Computable {
    /**
     * Method to compute the value for a
     * corresponding point
     *
     * @param x The point at which value needs to
     *         be computed
     * @return The value at x
     */
    public abstract double compute(double x);
}
