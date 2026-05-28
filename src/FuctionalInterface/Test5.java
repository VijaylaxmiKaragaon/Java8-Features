package FuctionalInterface;

import java.util.function.Predicate;

public class Test5 {

	public static void main(String[] args) {
		String [] str= {"Vinutha","Ananya","Hamsha","Vijju","Teji"};
		//Display the names if the names has atleast 5 characters
		Predicate<String> p=(a)->a.length()>=5;
//		for(int i=0;i<str.length;i++) {
//		  if(p.test(str[i])) {
//			  System.out.println(str[i]);
//		  }``````````
//		}
		
		for(String s:str) {
			if(p.test(s)) {
				System.out.println(s);
			}
		}
		

	}

}
