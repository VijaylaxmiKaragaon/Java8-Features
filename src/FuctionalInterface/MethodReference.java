package FuctionalInterface;

interface DCL{
	void add(int a,int b);
}

class Imp implements DCL{

	@Override
	public void add(int a, int b) {
		
		int c=a+b;
		System.out.println("Sum is " + c);
	}
	
}
public class MethodReference {
    
	public MethodReference() {
		System.out.println("This is 0-param cons");
	}
	
	public MethodReference(int a,int b) {
		System.out.println("This is param cons");
	}
	
	 void Multiply(int a,int b) {
		System.out.println("Product is"+(a*b));
	}
	 
	 void substract(int a,int b) {
		 System.out.println("Substract is"+(a-b));
	 }
	public static void main(String[] args) {
		System.out.println("---1---");
		DCL d1=new Imp();
		d1.add(10,20);
		
		System.out.println("---2---");
		DCL d2=(a,b)->{
			int c=a+b;
			System.out.println("The output is"+c);
		};
		d2.add(10, 20);
		
		System.out.println("---3---");
		MethodReference m=new MethodReference();
		m.Multiply(10, 12);
		
		System.out.println("---4---");
		DCL d4=new Imp();
		d4.add(12, 12);
		

	}

}
