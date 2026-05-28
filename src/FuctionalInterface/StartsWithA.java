package FuctionalInterface;

import java.util.function.Predicate;

public class StartsWithA {

	public static void main(String[] args) {
		Predicate<String> p =(a)->a.startsWith("a") || a.startsWith("A");
		if(p.test("Anjali")) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
	}
}
