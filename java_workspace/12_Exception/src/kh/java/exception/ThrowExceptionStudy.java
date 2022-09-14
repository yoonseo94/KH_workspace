package kh.java.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ThrowExceptionStudy {

	Scanner sc = new Scanner(System.in);
	
	/**
	 * main메소드에서 throws예외구문을 작성하는 것은 예외처리를 안하는 것과 같다.
	 * - 예외발생시 비정상종료된다. 
	 */
	public static void main(String[] args)/* throws Exception */{
		ThrowExceptionStudy study = new ThrowExceptionStudy();
//		study.test1();
//		study.test2();
//		study.test3();
		study.test4();
		System.out.println("----- 프로그램 정상 종료 -----");
	}

	/**
	 * 1. 예외처리
	 * 2. 예외던지기
	 * 
	 * - 체크드예외는 예외처리후(선처리)에 예외를 다시 던진다.
	 * - 프로그램 흐름을 분기(예외처리의 책임)할 수 있는 메소드까지 던진다. 
	 */
	private void test4() {
		// 프로그램 흐름을 분기하는 곳일 경우
		try {
			a();
			normalFlow();
		} catch(Exception e) {
			errorFlow();			
		}
	}
	
	public void normalFlow() {
		System.out.println("정상흐름~");
	}
	
	public void errorFlow() {
		System.out.println("오류가 발생했을 경우 흐름~");
	}
	
	public void a() throws FileNotFoundException {
		System.out.println("---- a 시작 ----");
		b();
		System.out.println("---- a 끝 ----");
	}
	
	public void b() throws FileNotFoundException {
		System.out.println("---- b 시작 ----");
		// 메소드 호출부에 예외처리 책임을 전가
		try {
			FileReader fr = new FileReader("helloworld.txt");
		} catch(Exception e) {
			System.out.println("선처리!");
			throw e; // 예외 다시 던지기 -> 프로그램 흐름을 분기할 수 있는 곳까지 던져야한다.
		}
		System.out.println("---- b 끝 ----");
	}

	/**
	 * Checked Exception - 예외처리를 강제화
	 * Unchecked Exception - RuntimeException의 후손클래스, 예외처리 강제화하지 않는다.
	 */
	private void test3() {
		// 해당경로의 파일을 읽어내는 객체
		// FileNotFoundException은 Checked예외, 예외처리를 반드시 해야 한다.
		try {
			FileReader fr = new FileReader("helloworld.txt");	
			System.out.println("helloworld.txt 파일이 존재합니다.");
			
			int n = sc.nextInt();
		} catch(FileNotFoundException e) {
			// 파일이 존재하지 않을 경우 후처리 코드
			e.printStackTrace();
		}
	}

	/**
	 * exception을 통한 분기처리
	 */
	private void test2() {
		try {
			checkAge2(); // 사용자 나이검사			
			startGame();
		} catch(Exception e) {
			e.printStackTrace(); // 콘솔에서 확인용(사용자 노출되지 않는다고 가정)
			System.out.println("게임을 즐길수 있는 적정연령이 아닙니다. 종료합니다.");
		}
	}
	
	/**
	 * 나이 검사후 20세미만인 경우 예외를 던지는 메소드
	 * - 현재메소드를 호출한 쪽에 예외를 던지게 된다.
	 * 
	 * 커스텀 예외클래스 작성 가능
	 * - UnderAgeException 현재 오류상황을 잘 설명할 수 있는 예외클래스를 작성할 것!
	 */
	public void checkAge2() {
		System.out.print("나이를 입력 : ");
		int age = sc.nextInt();
		if(age < 20)
			throw new UnderAgeException("미성년자 : " + age); 
	}

	/**
	 * if...else를 통한 분기처리
	 */
	private void test1() {
		boolean bool = checkAge1();
		if(bool) {
			startGame();			
		}
		else {
			System.out.println("게임을 즐길수 있는 적정연령이 아닙니다. 종료합니다.");
		}
	}
	
	public boolean checkAge1() {
		System.out.print("나이를 입력 : ");
		int age = sc.nextInt();
		if(age >= 20)
			return true;
		else 
			return false;
	}
	
	
	/**
	 * 성인만 즐길수 있는 게임.
	 */
	public void startGame() {
		System.out.println("게임시작...");
	}

}
