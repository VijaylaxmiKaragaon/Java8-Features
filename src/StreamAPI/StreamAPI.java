package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class StreamAPI {

	public static void main(String[] args) {
		
		//
		Stream<Integer> s1=Stream.of(2,4,5,7,8,9,12);
		s1.forEach(System.out::println);
		
		List<String> sList=Arrays.asList("virat","rajat");
		
	}

}
