package com.kh.jdk8.api.lambda;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class LambdaStudy2 {

	public static void main(String[] args) {
		LambdaStudy2 study = new LambdaStudy2();
//		study.test1();
//		study.test2();
		study.test3();
	}
	
	/**
	 * 메소드참조 MethodReference
	 */
	public void test3() {
//		Consumer<String> println = (str) -> System.out.println(str);
		Consumer<String> println = System.out::println;
		println.accept("abc");
		
		// static
		Function<String, Integer> parseInt = Integer::parseInt;
		System.out.println(parseInt.apply("123") + 32);
		
		// non-static
		int len = "abc".length();
		Function<String, Integer> length = String::length;
		System.out.println(length.apply("abcde"));
		
		// 특정객체기준
		String abc = "abc";
		Predicate<String> equalToAbc = abc::equals;
		System.out.println(equalToAbc.test("abc"));
		System.out.println(equalToAbc.test("bcd"));
		
		// 생성자메소드 참조
		Supplier<Person> constr1 = Person::new;
		Person p1 = constr1.get();
		System.out.println(p1);
		
		Function<String, Person> constr2 = Person::new;
		Person p2 = constr2.apply("홍길동");
		System.out.println(p2);
		
		BiFunction<String, Integer, Person> constr3 = Person::new;
		Person p3 = constr3.apply("신사", 48);
		System.out.println(p3);
		
		// 배열생성
		Function<Integer, int[]> arrayMaker = int[]::new;
		int[] arr = arrayMaker.apply(3);
		System.out.println(arr);
	}
	
	/**
	 * 
	 */
	public void test2() {
		// 현재시각을 출력하는 HH:mm:ss 람다식을 작성
		Runnable now = () -> System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		now.run();
		Consumer<LocalTime> now2 = (time) -> System.out.println(time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		now2.accept(LocalTime.now());
		
		// 호출시 로또번호 Set<Integer> 반환하는 람다식을 작성
		Supplier<Set<Integer>> lotto = () -> {
			return new TreeSet<Integer>() {
				{
					while(size() < 6)
						add((int)(Math.random() * 45 + 1));
				}
			};
		};
		System.out.println(lotto.get());
		
		// 원화 입력시 달러를 출력하는 람다식 작성 ($1 = ￦1300) 
		Function<Double, Double> wonToDollarConverter = (won) -> {
			double rate = 1300;
			return won / rate;
		};
		System.out.println(wonToDollarConverter.apply((double) 3000));
		
	}

	/**
	 * Runnable#run
	 * - 매개변수 없음
	 * - 리턴값 없음
	 * 
	 * Supplier<R>#get
	 * - 매개변수 없음.
	 * - 리턴값 R 
	 * 
	 * Consumer<T>#accept
	 * - 매개변수 T
	 * - 리턴값 없음.
	 * 
	 * Function<T,R>#apply
	 * - 매개변수 T
	 * - 리턴값 R
	 * 
	 * Predicate<T>#test:boolean
	 * - 매개변수 T
	 * - 리턴값 boolean
	 */
	private void test1() {
		Runnable a = () -> System.out.println("hello world");
		a.run();
		
		Supplier<Integer> lotto = () -> (int)(Math.random() * 45 + 1);
		System.out.println(lotto.get());
		
		Consumer<String> p = (name) -> System.out.println("안녕하세요, 제 이름은 " + name + "입니다.");
		p.accept("홍길동");
		
		Function<String, Long> calcLen = (str) -> (long) str.length();
		System.out.println(calcLen.apply("helloworld"));
		
		Predicate<Long> isEven = (n) -> n % 2 == 0;
		System.out.println(isEven.test(10L));
		System.out.println(isEven.test(7L));
		
		
		
	}

}


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
class Person {
    
    @NonNull
    private String name;
    private int age;
    
}
