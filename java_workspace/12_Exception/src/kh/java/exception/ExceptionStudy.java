package kh.java.exception;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * Exception 
 * - 프로그램 오류중 적절한 코드에 의해서 정상적인 처리흐름으로 수습가능한 미약한 오류.
 * 
 * 처리방법
 * - 1. 예외처리 try~catch
 * - 2. 예외던지기 throws
 * 
 * 예외의 종류
 * - 1. Unchecked Exception 예외처리강제화 없음. RuntimeException후손클래스 모두.
 * - 2. Checked Exception 예외처리강제화. RuntimeException후손 외 모든 클래스
 *
 */
public class ExceptionStudy {

	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		ExceptionStudy study = new ExceptionStudy();
//		study.test1();
		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
		System.out.println("---- 프로그램 정상 종료 ----");
	}
	
	/**
	 * try... catch블럭
	 *  변수 유효범위
	 */
	public void test5() {
		int a = 0;
		try {
//			int a = 10;
			a = 10;
		} catch(Exception e) {
			System.out.println(a);
		} finally {			
			System.out.println(a);
		}
		System.out.println(a);
	}
	
	/**
	 * 사용자로부터 정수 2개를 입력받아 합을 출력하세요.
	 * - 예외가 발생할 수 있는 상황을 고려해 예외처리구문 작성하세요.
	 */
	public void test4() {
		int a = 0;
		int b = 0;
		
		while(true) {
			try {
				System.out.print("정수 1 입력 : ");
				a = sc.nextInt();
				System.out.print("정수 2 입력 : ");
				b = sc.nextInt();
				
				break;
			} catch(InputMismatchException e) {
				System.out.println("정수만 입력하실 수 있습니다.");
				sc.next(); // 버퍼비우기용
			}			
		}
		
		System.out.printf("%d + %d = %d%n", a, b, a + b);
		
	}
	
	/**
	 * catch절 작성순서
	 * - 상속관계가 없을때는 순서 상관 없다.
	 * - 다형성을 적용할 수 있다. 
	 * - 부모/자식클래스 catch블럭을 모두 작성해야 하는 경우, 자식 - 부모순으로 작성해야 한다.
	 */
	private void test3() {
		try {
//			System.out.println("정수입력 : ");
//			int n = sc.nextInt(); 
//			
//			System.out.println(100 / 0); // ArithmeticException
//			
//			String s = null;
//			System.out.println(s.hashCode()); // NullPointerException
			
			int[] arr = new int[3];
			System.out.println(arr[100]); // ArrayIndexOutOfBoundsException
		
//		} catch(NullPointerException e) {
//			System.out.println("NullPointerException!!");
//		} catch(ArrayIndexOutOfBoundsException e) {
//			System.out.println("ArrayIndexOutOfBoundsException!!");
//		} catch(ArithmeticException e) {
//			System.out.println("ArithmeticException!!");
//		} catch(RuntimeException e) {
//			System.out.println("RuntimeException!");
//			System.out.println(e);
		} catch(Exception e) {
			System.out.println("Exception!!");
			System.out.println(e);
		}	
		
	}

	/**
	 * try...catch흐름
	 * 
	 * finally 블럭
	 * - 예외가 발생하든 안하든 무조건 실행되는 블럭
	 * - try에서 사용한 자원반납하는 코드등을 작성
	 */
	private void test2() {
		System.out.println(1);
		try {
			System.out.println(2);

			if(true)
				return; // 조기리턴시에도 finally절은 실행된다.
			
//			String s = null;
//			System.out.println(s.length());
			
			System.out.println(100 / 10); // ArithmeticException 유발 코드
			
			System.out.println(3);
		} catch(ArithmeticException e) {
			// 예외객체가 던져진다.
//			System.out.println(e);
//			System.out.println(e.getMessage());
//			// stack trace 
//			e.printStackTrace();
			
			System.out.println(4);
		} finally {
			System.out.println(5);
		}
		System.out.println(6);
	}



	/**
	 * try...catch 예외처리
	 * - try 예외가 발생할 수 있는 구문 작성
	 * - catch 예외가 발생했을때 처리할 구문 작성
	 */
	public void test1() {
		while(true) {
			try {
				String s = null;
				if(new Random().nextBoolean())
					s = "hello";
				System.out.println(s.length());
				
				System.out.print("정수1 입력 : ");
				int a = sc.nextInt();
				System.out.print("정수2 입력 : ");
				int b = sc.nextInt();
				System.out.printf("%d / %d => %d%n", a, b, a / b);
				// java.lang.ArithmeticException: / by zero
				
				break;
			} catch(ArithmeticException e) {
				System.out.println("0으로 나눌수 없습니다.");
			} catch(InputMismatchException e) {
				System.out.println("숫자만 입력하세요.");
				sc.next(); // 입력버퍼 비우기
			} catch(NullPointerException e) {
				System.out.println("NPE가 발생했습니다.");
			}
			System.out.println("반복문 끝!");
		}
	}

}
