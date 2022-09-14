package com.kh.jdk8.api.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBasicStudy {

	public static void main(String[] args) {
		StreamBasicStudy study = new StreamBasicStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
		study.test6();
	}
	
	
	/**
	 * anyMatch
	 * - 요소중에 조건을 만족하는 요소가 하나라도 있으면 true반환
	 * 
	 * noneMatch
	 * - 요소중에 조건을 만족하는 요소가 하나도 없으면 true반환
	 * 
	 */
	private void test6() {
		System.out.println(
			Stream.of(1,3,5)
				.anyMatch((n) -> n % 2 == 0)
			);
		
		System.out.println(
				Stream.of(1,3,5)
				.noneMatch((n) -> n % 2 == 0)
			);
		
	}



	/**
	 * collect
	 * - stream 단말연산
	 */
	private void test5() {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		
		List<Integer> result = 
			list.stream()
				.map(n -> n * 100)
				.collect(Collectors.toList());
		
		System.out.println(list);
		System.out.println(result);
		
		Map<Integer, Integer> map = 
			list.stream()
				.collect(Collectors.toMap(n -> n, n -> n * 100));
		System.out.println(map);
		
		Map<String, Integer> snackMap = 
			Stream.of("고래밥", "계란과자", "초코송이", "고래밥", "초코송이")
				.collect(Collectors.toMap(snack -> snack, snack -> 1, (v1, v2) -> v1 + v2));
		
		System.out.println(snackMap);
	}

	/**
	 * peek
	 * 
	 * stream
	 * 1. stream 생성 
	 * 2. 중간연산 Intermediate Operation
	 * 3. 단말연산 Terminal Operation
	 * 
	 * 최종 단말연산 전에 중간연산을 완료하지 않는다.
	 */
	private void test4() {
		Stream.of(1,2,3,4,5,6,7,8,9,10)
			.peek(n -> System.out.println("Before : " + n))
			.filter(n -> n % 2 == 0)
			.forEach(System.out::println);
	}

	/**
	 * Stream은 모두 읽기전용. 원래의 배열, 컬렉션변경하지 않는다.
	 * 
	 * map
	 * - 해당요소를 연산후 바꿔서 반환
	 * - 타입이 변환되는 경우는 적절한 메소드를 사용해야 함.
	 */
	private void test3() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		list.stream() // Stream<Integer>
			.map((n) -> n * n)
			.map((n) -> n / 2.0)
			.forEach(System.out::println);
		System.out.println(list);
		
		String[] names = {"홍길동", "신사임당", "세종", "꺅", "abcdefu"};
		Arrays.stream(names)
			.map(name -> name.length()) // Stream<Integer>
			.forEach(System.out::println);
		
		
		List<String> strList = Arrays.asList("a b c d", "안 녕", "제 이름은 신사입니다.", "    ㅋㅋㅋ");
		strList.stream()
			.map(str -> str.replaceAll(" ", ""))
			.forEach(System.out::println);
		
	}

	/**
	 * distinct 
	 * - 중복값 제거
	 * 
	 * filter
	 * - true를 리턴한 요소만 추려내는 것
	 * 
	 */
	private void test2() {
		Stream.of(1,2,3,4,5,4,3,2,1,1)
			.distinct()
			.filter((n) -> n % 2 == 0)
			.forEach(System.out::print);
		
		System.out.println();
		
		// @실습문제 : 다음 이름중 홍씨만 골라서 중복값 없이 출력하세요
		// 홍길동 홍진경 고길동 홍난파 홍진경 
		Stream.of("홍길동", "홍진경", "고길동", "홍난파", "홍진경")
			.filter((name) -> name.startsWith("홍"))
			.distinct()
			.forEach(System.out::println);
		
	}

	/**
	 * Stream 생성하기
	 * - 배열
	 * - Collection
	 */
	private void test1() {
		String[] arr = {"a", "b", "c"};
		// String[] -> Stream<String>
		Stream<String> stream1 = Stream.of(arr);
		Stream<String> stream2 = Stream.of("a", "b", "c");
		Stream<String> stream3 = Arrays.stream(arr);
		
		System.out.println(stream1); // java.util.stream.ReferencePipeline$Head@15db9742
		System.out.println(stream2);
		System.out.println(stream3);
		
//		stream1.forEach(System.out::println);
//		stream2.forEach(System.out::println);
//		stream3.forEach(System.out::println);
		
		
		List<String> list = Arrays.asList("가", "나", "다");
		// List<String> -> Stream<String> 
		Stream<String> stream4 = list.stream();
		
		System.out.println(stream4); // java.util.stream.ReferencePipeline$Head@4e25154f
		
		stream4.forEach(System.out::println);
		
	}

}
