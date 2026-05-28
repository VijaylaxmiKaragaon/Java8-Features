package FuctionalInterface;

interface Addition{
	int add(int a,int b);
}


public class Test2 {

	public static void main(String[] args) {
		Addition t=(a,b)->(a+b);
		System.out.println(t.add(10,20));

	}

}
