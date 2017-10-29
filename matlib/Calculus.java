package matlib;

/**
 * Class to perform simple Calculus operations
 *
 * @author shreyansh
 */
public class Calculus{
    /**
     * Method to perform numerical integration by Simpson's rule
     *
     * @param expression The function which needs to be integrated
     * @param from The lower limit of integration
     * @param to The upper limit of integration
     * @param <T> A subclass of Computable
     * @return The value of the integration of the function over the limits
     */
    public static <T extends Computable> double integrate(T expression,double from,double to){
        double n=200;
        double h= (to-from)/n;
        double x=from;
        double mid = x+h/2;
        double sum = expression.compute(x);
        int i=0;
        while (i<n-1){
            i++;
            x= from + i * h;
            sum = sum + 4 * expression.compute(mid) + 2* expression.compute(x);
            mid = x+h/2;
        }
        sum = sum + 4 * expression.compute(mid) + expression.compute(to);
        return sum * h/6;
    }

    /**
     * Function to compute the derivative at a point of a function
     *
     * @param expression The function to be differentiated
     * @param point The point at which derivative is to be found
     * @param <T> A subclass of Computable class
     * @return The value of the slope at point
     * @throws LimitDoesNotExistException
     */
    public static <T extends Computable> double differentiate(T expression,double point)throws LimitDoesNotExistException{
        double ans=0.0;
        double epoch = 1.0/1e11;
        double accuracy = 1.0/1e5;
        double val1=expression.compute(point+epoch)/epoch;
        double val2=expression.compute(point)/epoch;
        double val3=expression.compute(point-epoch)/epoch;
        double ld = val2-val3;
        double rd = val1-val2;
        if(Math.abs(ld-rd) < accuracy)
            ans = (ld+rd)/2;
        else
            throw new LimitDoesNotExistException();
        return ans;
    }

    /**
     * Function to compute the limit of a function
     *
     * @param expression The function whose limit is to be found
     * @param point The point at which limit needs to be found
     * @param <T> A subclass of Computable class
     * @return The limit at the specified point
     * @throws LimitDoesNotExistException
     */
    public static <T extends Computable> double limit(T expression, double point)throws LimitDoesNotExistException{
        double epoch = 1/1e15;
        double accuracy = 1/1e6;
        try{
            while (true) {
                double ll = expression.compute(point - epoch);
                double rl = expression.compute(point + epoch);
                if(ll==rl && epoch<accuracy) {
                    epoch*=1e2;
                    continue;
                }
                if (Math.abs(ll - rl) < accuracy)
                    return (ll+rl)/2;
                else
                    throw new LimitDoesNotExistException();
            }
        }catch (Exception e){
            throw new LimitDoesNotExistException();
        }
    }
}
