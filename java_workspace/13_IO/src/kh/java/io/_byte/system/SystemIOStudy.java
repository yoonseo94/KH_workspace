package kh.java.io._byte.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SystemIOStudy {

	public static void main(String[] args) {
		SystemIOStudy study = new SystemIOStudy();
//		study.test1();
//		study.test2();
		study.test3();
		System.out.println("---- 정상적으로 종료되었습니다. ----");
	}

	/**
	 * 사용자로 부터 정수를 여러개 입력 받고, 입력받은 정수의 합을 출력하세요.
	 * - System.in과 BufferedReader사용하기
	 */
	private void test3() {
		System.out.println("정수를 입력하세요...");
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			int sum = 0;
			
			while((input = br.readLine()) != null) {
				if("exit".equals(input)) break;
				
				try {
					sum += Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("정수만 입력할 수 있습니다.");
				}
//				System.out.println(sum);
			}
			
			System.out.println("총합은 " + sum + "입니다.");
		} catch(IOException e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 * System.in 주스트림(표준입력)
	 * InputStreamReader 브릿지스트림
	 * BufferedReader 보조스트림(줄단위를 읽어오는 readLine메소드) 
	 * 
	 * - 대상과 연결된 주스트림객체를 보조스트림객체에 전달하고,
	 * - 보조스트림을 통해 제어한다.
	 * 
	 * 사용자입력값을 개행문자단위 읽어온다. 개행문자를 버리고 리턴.
	 */
	private void test2() {
		InputStream systemIn = System.in;
		InputStreamReader isr = new InputStreamReader(systemIn);
		BufferedReader br = new BufferedReader(isr);
		
		String input = null;
		
		try {
			System.out.println("아무말 입력 : ");
			while((input = br.readLine()) != null) {
				if("exit".equals(input))
					break;
				System.out.println(input);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 표준입력 - 키보드입력 System.in
	 * 	- Scanner(jdk1.5)를 사용해 예외처리 없이 사용가능
	 *  - 그이전에는 BuffererReader를 이용해 처리
	 * 표준출력 - 콘솔출력 System.out
	 * 
	 * 스트림객체는 자원을 사용후에 반드시 반납해야 한다.
	 * 다만, 표준입출력은 반납하면 안된다.(한번 반납하면 재사용이 표준입출력 불가하다.)
	 * 
	 * 
	 */
	private void test1() {
		int input = 0;
		try {
			System.out.println("키보드로 값을 입력하세요 : ");
			while((input = System.in.read()) != -1) {
				System.out.println(input);
			}
		} catch(IOException e) {
			e.printStackTrace(); // 예외로그
		} 
	}

}
