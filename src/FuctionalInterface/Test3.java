package FuctionalInterface;


interface EvenOrOdd{
	String validate(int a);
}
public class Test3 {

	public static void main(String[] args) {
		EvenOrOdd e=a->(a%2==0)? "even" :"odd";
		System.out.println(e.validate(23));
		}
	}


