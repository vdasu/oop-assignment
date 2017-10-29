package matlib;

import java.util.Comparator;

/**
 * Class to store one term of polynomial
 */
public class Term implements Comparator
{
    public int coeff,exp;

    /**
     * Default Constructor
     */
    Term(){
        coeff=0;
        exp=0;
    }

    /**
     * Parameterized Constructor
     * @param exp The exponent of the term
     * @param coeff The Coefficient of the term
     */
    Term(int exp,int coeff)
    {
        this.exp=exp;
        this.coeff=coeff;
    }
    /**
     * Compare two objects
     *
     *@param o1 Object 1 to be compared
     *@param o2 Object 2 to be compared
     *@return Compared value
     */
    @Override
    public int compare(Object o1,Object o2)
    {
        Term t1=(Term) o1;
        Term t2=(Term) o2;
        return t2.exp-t1.exp;
    }
    /**
     *@param o check for equality with this
     *@return true if both are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        Term t=(Term) o;
        return this.exp==t.exp;
    }
}