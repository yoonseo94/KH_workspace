package kh.java.oop.method;

/**
 * 변수별 생명주기
 * 1. 클래스변수	: 프로그램시작시 생성(프로그램 최초 사용시 - 동적로딩) ~ 프로그램종료시 소멸
 * 2. 인스턴스변수	: 객체 생성(new연산자 사용시기) ~ 객체 소멸(참조가 끊어졌을때, 실제로는 garbage collection에 의해서 반환) 
 * 3. 지역변수 	: 메소드호출시 생성 ~ 메소드반환시 소멸
 * 
 * 
 */
public class MethodSample {
	private int a; // 멤버변수
	public static final int b = 10; // 클래스 변수
	
	/**
	 * non-static메소드에서는 static, non-static필드를 모두 참조할 수 있다.
	 */
	public void x() {
		System.out.println("a = " + a);
		System.out.println("b = " + MethodSample.b);
	}
	
	/**
	 * static메소드 또한 공유의 성격을 가지고 있고, 객체생성 없이 클래스명으로 직접 호출이 가능
	 * 
	 * static메소드에서는 non-static필드를 참조할 수 없다.
	 * 오직 static필드만 참조가능하다.
	 * 
	 * static메소드안에는 this 참조 없다.
	 */
	public static void y() {
		// Cannot make a static reference to the non-static field a
//		System.out.println("a = " + a); 
		System.out.println("b = " + MethodSample.b);
	}
}
