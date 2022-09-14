package kh.java.oop.method;

public class MethodSampleMain {
	
//	static int a = 100;
	int a = 100;

	public static void main(String[] args) {
		
//		System.out.println(a); // Cannot make a static reference to the non-static field a
		// 1. a를 static필드로 변환
		// 2. 객체를 만들어 참조
		MethodSampleMain main = new MethodSampleMain();
		System.out.println(main.a);
		
		// non-static 메소드 호출
		MethodSample sample = new MethodSample();
		sample.x();
		
		// static 메소드 호출
		MethodSample.y();

	}

}
