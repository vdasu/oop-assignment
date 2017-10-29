package matlib;

import java.util.LinkedList;

/**
 *The Polynomial class stores a polynomial linked list and performs
 *various functions such as add,multiply,find roots,compute,etc.
 *@author Raghav Daruka
 *@since 2017-10-29
 */
public class Polynomial extends Computable
{
    private LinkedList<Term> poly;
    /**
     *Default Constructor.
     */
    Polynomial()
    {
        poly=new LinkedList<Term>();
    }
    /**
     *Parametrised Constructor
     *@param l Takes linked list and stores it in polynomial class
     */
    Polynomial(LinkedList<Term> l)
    {
        poly=l;
        poly.sort(new Term());
    }
    /**
     *Appends the given term to the polynomial
     *@param coeff Coefficient
     *@param exp Exponent
     */
    public void addTerm(int coeff,int exp)
    {
        Term p=new Term(exp,coeff);
        poly.add(p);
        poly.sort(new Term());

    }

    /**
     *Adds two given polynomials
     *@param p6 Object of polynomial class
     *@param p7 Object of polynomial class
     *@return The sum of p6 and p7
     */
    public static Polynomial addition(Polynomial p6,Polynomial p7)
    {
        LinkedList<Term> poly=p6.poly;
        LinkedList<Term> po=p7.poly;
        int i=0,j=0,s1,s2;
        poly.sort(new Term());
        po.sort(new Term());
        s1=poly.size();
        s2=po.size();
        LinkedList<Term> ans=new LinkedList<Term>();

        while(i<s1 && j<s2)
        {
            Term p3=new Term();
            Term p1=poly.get(i);
            Term p2=po.get(j);
            if(p1.exp==p2.exp)
            {
                p3.exp=p1.exp;
                p3.coeff=p1.coeff+p2.coeff;
                ans.add(p3);
                i++;j++;
            }

            else if(p1.exp<p2.exp)
            {
                p3.exp=p2.exp;
                p3.coeff=p2.coeff;
                ans.add(p3);
                j++;
            }
            else if(p1.exp>p2.exp)
            {
                p3.exp=p1.exp;
                p3.coeff=p1.coeff;
                ans.add(p3);
                i++;
            }

        }
        if(i==s1)
        {
            while(j<s2)
            {
                Term p2=po.get(j);
                ans.add(p2);
                j++;
            }
        }

        else if(j==s2)
        {
            while(i<s1)
            {
                Term p1=poly.get(i);
                ans.add(p1);
                i++;
            }
        }
        Polynomial temp=new Polynomial(ans);
        //return ans;
        return temp;
    }

    /**
     *Computes the polynomial by substituting the value
     *@param x The value of x
     *@return Calculated value of the polynomial
     */
    public double compute(double x)
    {
        int s=poly.size();
        float sum=0;
        for(int i=0;i<s;i++)				  //Compute values
        {
            Term p3=poly.get(i);
            sum+=(p3.coeff*(Math.pow(x,p3.exp)));

        }
        return sum;
    }

    /**
     *Displays the polynomial stored in polynomial class
     */
    public void display()
    {
        int s=poly.size();
        for(int i=0;i<s;i++)
        {
            Term p=poly.get(i);
            System.out.println("Exponent:"+p.exp +"\t Coefficient:"+p.coeff);

        }
    }
    /**
     *Multiplies given polnomial with the polnomial present in the class
     *@param p7 Polynomial object to be multiplied.
     *@return Product of this and p7
     *@throws InterruptedException throws exception when thread error
     */
    public Polynomial multiply(Polynomial p7) throws InterruptedException
    {
        LinkedList<Term> a=p7.poly;
        //LinkedList<Term> fin=new LinkedList<Term>();
        Polynomial fin=new Polynomial();
        int s1=poly.size();
        int s2=a.size();
        NewThread m[]=new NewThread[s1];

        for(int i=0;i<s1;i++)
            m[i]=new NewThread(poly.get(i),a);

        for(int i=0;i<s1;i++)
            m[i].t.join();

        for(int i=0;i<s1;i++)
            fin=addition(fin,m[i].p7);

        return fin;
    }
    /**
     *Find all rational roots of the given polynomial
     *@return Contains all the roots in array
     */
    public float[] rationalRoot()
    {
        float arr[]=new float[1000];
        float f[]=new float[100];
        int a1,a2,j=0,k=0,p=0,t=0;
        float sum=0;
        int arrc[]=new int[1000];
        int arrb[]=new int[1000];
        int s=poly.size();
        Term p2=poly.get(s-1);
        Term p1=poly.get(0);
        if(p2.exp==0)
        {
            for(int i=1;i<=Math.abs(p2.coeff);i++)//Find Factors for constant term
            {
                if(p2.coeff%i==0)
                {
                    arrc[j++]=i;
                    arrc[j++]=-i;
                }
            }
            for(int i=1;i<=Math.abs(p1.coeff);i++)//Find factors for coefficient of highest exponent
            {
                if(p1.coeff%i==0)
                {
                    arrb[k++]=i;
                    arrb[k++]=-i;
                }
            }
        }
        for(int i=0;i<j;i++)					  //Calculate all possible root values
        {
            for(int l=0;l<k;l++)
            {
                arr[p++]=(float)(arrc[i])/arrb[l];
            }
        }
        for(int i=0;i<p;i++)				  //Check which roots satisfy
        {
            sum=0;
            for(int l=0;l<s;l++)
            {
                Term p3=poly.get(l);
                sum+=(p3.coeff*(Math.pow(arr[i],p3.exp)));
            }
            if(sum<0.0001 && sum>-0.0001)
            {
                int flag=1;
                for(int q=0;q<t;q++)			//REmove duplicates
                {
                    if(f[q]==arr[i])
                    {flag=0;break;}
                }
                if(flag==1)
                    f[t++]=arr[i];
            }
        }
        return f;								//Return array containing possible rational roots
    }
    /**
     *Adds one polynomial to the class polynomial
     *@param p7 Polynomial object
     *@return The sum of this and p7
     */
    public Polynomial addition(Polynomial p7)
    {
        LinkedList<Term> po=p7.poly;
        int i=0,j=0,s1,s2;
        s1=poly.size();
        s2=po.size();
        LinkedList<Term> ans=new LinkedList<Term>();
        Term p1=null,p2=null,p3=new Term();
        while(i<s1 && j<s2)
        {
            p3=new Term();
            p1=poly.get(i);
            p2=po.get(j);
            if(p1.exp==p2.exp)
            {
                p3.exp=p1.exp;
                p3.coeff=p1.coeff+p2.coeff;
                ans.add(p3);
                i++;j++;
            }

            else if(p1.exp<p2.exp)
            {
                p3.exp=p2.exp;
                p3.coeff=p2.coeff;
                ans.add(p3);
                j++;
            }
            else if(p1.exp>p2.exp)
            {
                p3.exp=p1.exp;
                p3.coeff=p1.coeff;
                ans.add(p3);
                i++;
            }

        }
        if(i==s1)
        {
            while(j<s2)
            {
                p2=po.get(j);
                ans.add(p2);
                j++;
            }
        }

        else if(j==s2)
        {
            while(i<s1)
            {
                p1=poly.get(i);
                ans.add(p1);
                i++;
            }
        }
        Polynomial x1=new Polynomial(ans);
        return x1;
    }
    /**
     *Creates Threads for computing simultaneous multiplications
     */
    class NewThread implements Runnable
    {
        Term e;
        LinkedList<Term> g=new LinkedList<Term>();
        LinkedList<Term> f=new LinkedList<Term>();
        Polynomial p7=new Polynomial(f);
        Thread t;
        /**
         *Parametrised Constructor
         *@param e Object of class Term
         *@param g Linked List containing polynomial
         */
        NewThread(Term e, LinkedList<Term> g)
        {
            this.e=e;
            this.g=g;
            t=new Thread(this);
            t.start();
        }

        public void run()
        {
            int s=g.size();
            for(int i=0;i<s;i++)
            {
                Term p=g.get(i);
                Term c=new Term(e.exp+p.exp,e.coeff*p.coeff);
                //f.add(c);
                p7.poly.add(c);
            }
        }
    }

}


