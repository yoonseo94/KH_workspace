package kh.java.api.wrapper;

import java.util.ArrayList;
import java.util.List;

public class WrapperClassStudy {

	public static void main(String[] args) {
		WrapperClassStudy study = new WrapperClassStudy();
//		study.test1();
//		study.test2();
		study.test3();
	}

	
	/**
	 * 각타입을 String변환
	 */
	private void test3() {
		print(String.valueOf(1));
		print(String.valueOf(12.34));
		print(String.valueOf(true));
	}
	
	public void print(String s) {
		System.out.println(s);
	}


	/**
	 * 문자열값을 해당 Wrapper의 값타입으로 변환
	 */
	private void test2() {
		System.out.println(Integer.parseInt("123") + 123);
		System.out.println(Double.parseDouble("12.34") + 4.6);
		System.out.println(Boolean.parseBoolean("true") ? 1 : 2);
		System.out.println("Character.parseChar()는 없다.");
		System.out.println("abcde".charAt(0));
		
	}

	/**
	 * wrapper class
	 * - java.lang패키지에 존재하는 기본형 8가지에 1:1대응되는 클래스
	 * - 기본형값을 감싼 클래스
	 * 
	 * Byte(byte)
	 * Short(short)
	 * Integer(int)
	 * Long(long)
	 * Character(char)
	 * Boolean(boolean)
	 * Float(float)
	 * Double(double)
	 * 
	 * - 참조형을 반드시 써야하는 경우
	 * - 부가기능 (형변환) 
	 * 
	 * - auto-boxing 자동으로 기본값을 Wrapper객체에 담아 생성
	 * - auto-unboxing 자동으로 Wrapper객체에서 값을 꺼내어 처리
	 * 
	 */
	private void test1() {
		int n = 100;
//		Integer m = new Integer(100); // Deprecated
		Integer m = Integer.valueOf(100);
		Integer l = (Integer) 100;
		
		Integer k = 100; // auto-boxing 자동으로 기본값을 Integer객체에 담아 생성
		System.out.println(k);
		k = null;
		System.out.println(k);
		
		Integer i = 50;
		System.out.println(n + i); // auto-unboxing 자동으로 Integer객체에서 값을 꺼내어 처리
//		System.out.println(n + i.intValue());
		
		System.out.println(n == i); // int == Integer.intValue() -> int == int
		
		// 기본형이 아닌 참조형을 반드시 써야하는 경우
		// Generics문법 - 기본형을 허용하지 않는다.
		List<Integer> list = new ArrayList<Integer>();
		
	}

}





