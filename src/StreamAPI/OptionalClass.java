package StreamAPI;

import java.util.Optional;

public class OptionalClass {

	public static void main(String[] args) {
		String name = "Vijaylaxmi";
		Optional<String> opt_class = Optional.ofNullable(name);
		System.out.println(opt_class);
		
		System.out.println(opt_class.isPresent());
		//weather the data is present of return type boolean,if the data is present then return true
		//else return false
		
		System.out.println(opt_class.isEmpty()); 
		//if the content is empty or not 
		
		System.out.println(opt_class.orElse("No names available"));
		//if the data is present then it will display otherwise it will display the "no more available"
		
		

	}

}
