package kh.java.basic.method;

import java.util.Scanner;

public class MethodStudy {

	public static void main(String[] args) {
		// static 객체를 생성하지 않고, 클래스명으로 호출
//		MethodStudy.test1();
		
		// non-static 객체를 생성후, 객체로부터 호출
		MethodStudy study = new MethodStudy();
//		study.test2();
		
//		study.test3();
//		study.test4();
		
		study.test5();
	}
	
	public void test5() {
//		Scanner sc = new Scanner(System.in);
//		System.out.print("정수입력 : ");
//		int n1 = sc.nextInt();		
//		System.out.print("정수입력 : ");
//		int n2 = sc.nextInt();		
//		System.out.print("정수입력 : ");
//		int n3 = sc.nextInt();
		
		int n1 = getUserInput(1);
		int n2 = getUserInput(2);
		int n3 = getUserInput(3);
		
		System.out.printf("%d + %d + %d = %d%n", n1, n2, n3, n1 + n2 + n3);
	}
	
	public int getUserInput(int nth) {
		Scanner sc = new Scanner(System.in);
		System.out.printf("정수 %d 입력 : ", nth);
		int n = sc.nextInt();
		return n;
	}
	
	/**
	 * 메소드 시그니처 : 접근제한자 static 리턴타입 메소드명(매개변수명)
	 * 
	 * - 매개변수 유무
	 * - 리턴값 유무
	 */
	public void test3() {
		// 데이터 할당부
		String name = "홍길동";
		int age = 33;
				
		// 데이터 출력부
		printPerson(name, age);

		return; // 리턴타입 void는 return시에 가져가는 값이 없다.
	}
	
	public void test4() {
		// 데이터 할당부		
		String name = "신사임당";
		int age = 48;
		
		// 데이터 출력부
		printPerson(name, age); // 메소드 호출시 매개인자(값)을 전달
	}
	
	/**
	 * 선언 -> 값대입 -> 사용
	 * 
	 * 매개변수(메소드 선언부) parameter
	 * - 메소드 호출시에 값(매개인자 argument)이 대입된다.
	 * 
	 * @param name
	 * @param age
	 */
	public void printPerson(String name, int age) {
		System.out.printf("안녕하세요, 저는 %s입니다. 나이는 %d살이에요.%n", name, age);	
		return;
	}
	
	
	
	
	public void test2() {
		System.out.println("non-staticccccccccccccccccc!");
	}
	
	public static void test1() {
		System.out.println("staticccccccccccccccccccc!");
	}
}
