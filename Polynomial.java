import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

 public class Polynomial // implements Comparator<Polynomial>
 {
 	private LinkedList<Term> poly;
	//private LinkedList<Term> poly2;

 	Polynomial()
 	{
 		poly=new LinkedList<Term>(); // Add adds 
		//poly2=new LinkedList<Term>();
 	}

 	Polynomial(LinkedList<Term> l)
 	{
 		poly=l;
 		poly.sort(new Term());
 	}

 	public void addTerm(int coeff,int exp)
 	{
 		Term p=new Term(exp,coeff);
 		poly.add(p);
 		poly.sort(new Term());

 	}
 	/*public void enter()
 	{
 		int i=0;
 		Scanner sc=new Scanner(System.in);
 		LinkedList <Polynomial> a=new LinkedList<Polynomial>();
 		while(true)
 		{
			//System.out.println("Enter exponent  and coefficient.Put exponent<0 to stop.");
 			int texp=sc.nextInt();
 			if(texp<=-1)
 				break;
 			int tcoeff=sc.nextInt();
 			Term p=new Term(texp,tcoeff);
 			poly.add(p);
			//return a;
 			poly.sort(new Term());
 		}
 	}
	*/
 	public LinkedList<Term> addition(LinkedList<Term> poly,LinkedList<Term> po)
 	{
 		int i=0,j=0,s1,s2;
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
 		return ans;
 	} 


 	public void display()
 	{
 		int s=poly.size();
 		for(int i=0;i<s;i++)
 		{
 			Term p=poly.get(i);
 			System.out.println("Exponent:"+p.exp +"\t Coefficient:"+p.coeff);

 		}
 	}

 	public LinkedList<Term> multiply(LinkedList<Term> a) throws Exception
 	{
 		LinkedList<Term> fin=new LinkedList<Term>();
 		int s1=poly.size();
 		int s2=a.size();
		NewThread m[]=new NewThread[s1];

		for(int i=0;i<s1;i++)
			m[i]=new NewThread(poly.get(i),a);

		for(int i=0;i<s1;i++)
			m[i].t.join();

		for(int i=0;i<s1;i++)
			fin=addition(fin,m[i].f);

		return fin;
 	}




	public float[] rationalRoot() //Using Rational Roots test
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
		for(int l=0;l<k;l++)
			arr[p++]=(float)(arrc[i])/arrb[l];

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
					{
						f[t++]=arr[i];
						System.out.println(arr[i]);
					}				
				}
				
				//if()
				/*{
					f[t++]=arr[i];
					System.out.println(arr[i]);
				}*/
			}	
			return f;								//Return array containing possible rational roots

		}

	public LinkedList<Term> addition(LinkedList<Term> po)
 	{
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
 		return ans;
 	} 


	/*public void sort(){
		Term temp=poly.getFirst();
		while(temp!=null){

		}
	}
	*/
	class NewThread implements Runnable
	{
		Term e;
		LinkedList<Term> g=new LinkedList<Term>();
		LinkedList<Term> f=new LinkedList<Term>();
		Thread t;
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
				f.add(c);
			}
		}
	}	

}	
class Term implements Comparator
{
	public int coeff,exp;
	Term(){}
	Term(int exp,int coeff)
	{
		this.exp=exp;
		this.coeff=coeff;
	}
	public int compare(Object o1,Object o2)
	{
		Term t1=(Term) o1;
		Term t2=(Term) o2;
		return t2.exp-t1.exp;
	}
	public boolean equals(Object o)
	{
		Term t=(Term) o;
		return this.exp==t.exp;
	}
}


