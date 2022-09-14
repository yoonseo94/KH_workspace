package com.kh.java.value;

public class ValueMain {

	public void test2() {
		System.out.println("홍길동"); // 문자열 String 문자0개이상
	}
	
	/**
	 * 자바프로그램은 클래스에 선언된 메소드를 연달아 호출하며 실행된다.
	 * main메소드가 그 시작과 끝. jvm이 main메소드를 호출.
	 * main메소드의 모든 코드를 실행하면 프로그램은 종료된다.
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("시작");
		
		// 1.객체(메모리조각) 생성
		// 객체레시피 : 클래스 -> 객체
		// 클래스명 변수명 = new 클래스명();
		ValueMain main = new ValueMain();
		
		// 2.객체의 메소드 호출
		main.test1();
		main.test2();
		
		System.out.println("끝");
	}
	
	/**
	 * 자바의 값 literal
	 */
	public void test1() {
		System.out.println(123);
		System.out.println(123.45);
		System.out.println(1 + 2 + 3);
		System.out.println('캬'); // 문자 char 한글자
		System.out.println(true); // 논리값(참/거짓)
		System.out.println(false);
	}
	
	
	
}
