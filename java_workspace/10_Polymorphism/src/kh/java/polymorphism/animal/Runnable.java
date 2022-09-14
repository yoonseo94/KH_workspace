package kh.java.polymorphism.animal;

/**
 * 
 * 인터페이스
 * - 인터페이스도 하나의 클래스
 * - 컴파일된 결과는 동일하게 .class이다.
 * - 일반필드/메소드를 가질 수 없다. 객체를 생성할 수 없다. -> new Runnable() X
 * - 상수/추상메소드만 가질수 있다. 오로지 규격제공용 클래스라고 볼수있다.
 * - jdk8부터 default/static 메소드를 사용할 수 있다.
 * 		- default메소드 : 일반메소드처럼 사용가능. 자식객체에서 호출가능
 * 		- static메소드
 * 
 * - 자식클래스(구현클래스)에서는 implements 키워드를 사용한다.
 * - 다중구현을 지원한다.
 *
 */
public interface Runnable {

	// 상수 (public static final 생략가능)
//	public static final 
	int LEG_NUM = 4;
	
	// 추상메소드 (public abstract 생략가능)
//	public abstract 
	void run();
	
	/**
	 * 인터페이스의 default메소드
	 * - 인터페이스의 일반메소드
	 */
	public default void walk() {
		System.out.println("뛰는 자는 걸을 수 도 있지~");
	}
	
	/**
	 * static 메소드
	 */
	public static void warmup() {
		System.out.println("뛰기 전에 몸을 풉니다~");
	}
	
}
