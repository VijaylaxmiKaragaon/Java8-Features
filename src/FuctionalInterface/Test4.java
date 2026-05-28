package FuctionalInterface;

import java.util.function.Predicate;

public class Test4 {

	public static void main(String[] args) {
		Predicate<Integer> p =(a)->a%2==0;
		if(p.test(21)) {
			System.out.println("Even");
		} else {
			System.out.println("odd");
		}

	}

}
