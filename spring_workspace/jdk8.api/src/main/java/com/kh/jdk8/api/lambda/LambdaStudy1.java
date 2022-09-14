package com.kh.jdk8.api.lambda;

public class LambdaStudy1 {
	static Func1 func100 = () -> (int)(Math.random() * 100 + 1);
	static Func1 func1000 = () -> (int)(Math.random() * 1000 + 1);
	static Func1 func10000 = () -> (int)(Math.random() * 10000 + 1);

	/**
	 * 람다의 조건
	 * - 추상메소드가 단 하나인 인터페이스 필요. 
	 * - 클래스레벨에 @FunctionalInterface를 사용하면, 람다사용가능여부 검사 
	 * - 접근제한자, 리턴타입, 매개변수타입 생략가능 : 추상메소드에서 추론할수 있으므로.
	 */
	public static void main(String[] args) {
//		Pita pita = (double a, double b) -> {
//			return Math.sqrt(a * a + b * b);
//		};
		Pita pita = (a, b) -> Math.sqrt(a * a + b * b);
		System.out.println(pita.calc(3, 4)); // 5.0
		
		
		Foo f1 = (n) -> n * n;
		System.out.println(f1.calc(3)); // 9
		
		Foo f2 = (n) -> {
			int sum = 0;
			for(int i = 1; i <= n; i++)
				sum += i;
			return sum;
		};
		System.out.println(f2.calc(10)); // 55
		
		// @실습문제 : 난수(1 ~ 100)를 생성하는 람다식을 작성하세요.
		// 인터페이스 -> 람다식 작성 -> 호출
		System.out.println("난수 : " + func100.get());
		System.out.println("난수 : " + func1000.get());
		
		// @실습문제 : 두 정수를 입력받고 큰수를 리턴하는 람다식과 작은수를 리턴하는 람다식을 작성하세요.
		// 인터페이스 -> 람다식 작성 -> 호출
		Func2 max = (a, b) -> (a > b ? a : b);
		Func2 min = (a, b) -> (a < b ? a : b);
		System.out.println(max.which(10, 3)); // 10
		System.out.println(min.which(10, 3)); // 3
		
	}

}

@FunctionalInterface
interface Foo {
	int calc(int n);
}

@FunctionalInterface
interface Func1 {
	int get();
}

@FunctionalInterface
interface Func2 {
	int which(int a, int b);
}
