package InterfaceEnhancement;

public class DCLTest {

	public static void main(String[] args) {
		System.out.println("For yalahanka branch");
		Yelahanka y=new Yelahanka();
		y.JFS();
		y.PFS();
		y.MernStack();
		
		System.out.println("For BTM Branch");
		BTM b=new BTM();
		b.JFS();
		b.PFS();
		b.MernStack();
		
		System.out.println("For Rajajinagar branch");
		Rajajinagar r=new Rajajinagar();
		r.JFS();
		r.PFS();
		r.MernStack();
		
		System.out.println("For all branches");
		DCLab.DevOps();

	}

}
