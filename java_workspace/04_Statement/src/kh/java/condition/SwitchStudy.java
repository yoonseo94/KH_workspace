package kh.java.condition;

import java.util.Scanner;

/**
 * switch문 - 값에 따라 분기처리하는 제어문
 * 
 * - 괄호안에는 값으로 처리될 변수 또는 계산식이 들어와야한다.
 * - byte, short, int, char, String(jdk 1.7부터), enum타입.
 * - 위에서 아래로 case문 검사. 해당하는 case구문 실행.
 * - break를 만나면 실행중지, switch문을 탈출.
 * - 제시한 case문이 실행되지 않으면 default구문 실행.
 *
 */
public class SwitchStudy {

	public static void main(String[] args) {
		SwitchStudy study = new SwitchStudy();
//		study.test1();
//		study.test2();
//		study.test3();
		study.test4();
	}
	
	/**
	 * 회원관리
	 * 1. Gold   - 스마트TV, 전자렌지, 전기다리미
	 * 2. Silver - 전자렌지, 전기다리미
	 * 3. Bronze - 전기다리미
	 */
	public void test4() {
		String menu = "회원등급을 입력하세요.\n"
					+ "1.Gold   2.Silver   3.Bronze\n"
					+ "입력 : ";
		System.out.print(menu);
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		String present = "";
		switch(num) {
		case 1: present += "스마트TV, ";
		case 2: present += "전자렌지, "; 
		case 3: present += "전기다리미"; break;
		default: System.out.println("잘못 입력하셨습니다."); return;
		}
		System.out.printf("축하합니다. [%s]를 드립니다.", present);
		
	}
	
	/**
	 * fall through
	 */
	public void test3() {
		int n = 100;
		char grade = 'F'; // char의 기본값 
		
		switch(n / 10) {
		case 10 : 
		case 9 : grade = 'A'; break;
		case 8 : grade = 'B'; break;
		case 7 : grade = 'C'; break;
		case 6 : grade = 'D'; break;
		}
	
		System.out.printf("점수 : %d, 학점 : %c%n", n, grade);
	}
	
	public void test2() {
		String menu = "=======================\n"
					+ "a.된장찌게 -------- 5000원\n"
					+ "b.김치찌게 -------- 6000원\n"
					+ "c.순대국  --------- 7000원\n"
					+ "-----------------------\n"
					+ "선택 : ";
		
		System.out.print(menu);
		Scanner sc = new Scanner(System.in);
		char choice = sc.next().charAt(0);

		String name = "";
		int price = 0;
		
		switch(choice) {
		case 'a': name = "된장찌게"; price = 5000;  break; 
		case 'b': name = "김치찌게"; price = 6000; break; 
		case 'c': name = "순대국"; price = 7000; break;
		default: System.out.println("잘못 입력하셨습니다."); return; 
		}
		
		System.out.printf("%s는 %d원입니다. 감사합니다 :) ", name, price);
	}
	
	public void test1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("과일을 선택하세요 1.사과(500)  2.바나나(700)  3.키위(600)  4.아보카도(1300)\n> ");
		int num = sc.nextInt();

		String fruitName = "";
		int price = 0;
		
		switch(num) {
		case 1: 
			fruitName = "사과";
			price = 500;
			break;
		case 2: 
			fruitName = "바나나";
			price = 700;
			break;
		case 3: 
			fruitName = "키위";
			price = 600;
			break;
		case 4: 
			fruitName = "아보카도";
			price = 1300;
			break;
		default: 
			System.out.println("잘못된 번호입니다.");
			return; // 조기리턴. 현재메소드 호출부
		}
		
		System.out.printf("%s를 선택하셨네요~ %n", fruitName);
		System.out.printf("%d원입니다.", price);			
		
		// 문자열 값비교는 equals메소드를 사용할 것
//		if(price != 0 && "".equals(fruitName)) {
//			
//		}
	}
}
