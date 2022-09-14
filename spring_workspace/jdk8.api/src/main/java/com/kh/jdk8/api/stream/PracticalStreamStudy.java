package com.kh.jdk8.api.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PracticalStreamStudy {

	public static void main(String[] args) {
		PracticalStreamStudy study = new PracticalStreamStudy();
//		study.test1();
//		study.test2();
		study.test3();
	}
	
	
	
	/**
	 * 중첩반복문처리
	 */
	private void test3() {
		IntStream.range(2, 10)
			.forEach(dan -> 
					IntStream.range(1, 10)
						.forEach(n -> System.out.println(dan + " * " + n + " = " + (dan * n))));
		
	}




	/**
	 * 타입변환
	 */
	private void test2() {
		Stream.of("a1", "a20", "a300")
			.map((s) -> s.substring(1))
			.mapToInt(Integer::parseInt)
			.forEach(System.out::println);
		
		IntStream.range(0, 10)
			.mapToObj((n) -> "안녕" + n)
			.forEach(System.out::println);
	}



	/**
	 * IntStream
	 * - range(startInclusive, endExclusive)
	 * - rangeClosed(startInclusive, endInclusive)
	 */
	private void test1() {
		IntStream.range(1, 10)
			.forEach(System.out::println);
		
		IntStream.rangeClosed(1, 10)
		.forEach(System.out::println);
		
		
		int sum =
			IntStream.rangeClosed(1, 10)
				.sum();
		System.out.println(sum);
		
		IntStream.rangeClosed(1, 10)
			.average() // OptionalDouble 
			.ifPresent(System.out::println);
	}

}
