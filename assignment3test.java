import java.util.Scanner;
import java.util.LinkedList;

public class assignment3test
{
	public static void main(String args[]) throws Exception
	{
		//Polynomial a=new Polynomial();
		Scanner sc =new Scanner(System.in);
		Polynomial a=new Polynomial();
		float f[]=new float[100];
		Term b=new Term(2,3),c=new Term(5,3),d=new Term(0,1);
		LinkedList<Term> q=new LinkedList<>();
		q.add(b);q.add(c);q.add(d);
		LinkedList<Term> v=new LinkedList<>();
		LinkedList<Term> da=new LinkedList<>();
		v.add(b);v.add(c);v.add(d);

		da=a.addition(q,v);
		Polynomial x=new Polynomial(da);
		x.display();
		da=a.multiply(v);
		Polynomial st=new Polynomial(da);
		st.display();

 //		System.out.println("!)Enter")
 		//int x=sc.nextInt();
		/*while(true)
		{
			System.out.println("\n1)Enter\n2)Display\n3)Find Rational roots\n");
			int x=sc.nextInt();
			switch(x)
			{
				case 1: System.out.println("Enter exponent and coefficient");
				//a.enter();
				break;

				case 2:a.display();
				break;

				case 3:f=a.rationalRoot();
				break;		
				default:System.exit(0);
			}					
		}*/
		
	}
}


