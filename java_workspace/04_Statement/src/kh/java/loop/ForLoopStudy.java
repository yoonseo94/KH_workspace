package kh.java.loop;

import java.util.Scanner;

/**
 * for
 * 
 * while
 * 
 *
 */
public class ForLoopStudy {

	public static void main(String[] args) {
		ForLoopStudy study = new ForLoopStudy();
//		study.test1();
//		study.test2();
//		study.test3();
		study.test4();
//		study.test5();
	}
	
	/**
	 * 1 ~ 100 사이의 정수의 합
	 */
	public void test5() {
		int sum = 0; // 초기화
		
		for(int i = 1; i <= 100; i++) {
			sum += i;
			System.out.printf("i = %d, sum = %d%n", i, sum);
		}
		
		System.out.println("합 : " + sum);
	}
	
	/**
	 * 반복횟수를 동적으로 결정하기
	 * 1. limit 변수를 지정
	 * 2. 무한반복해두고, 사용자가 반복중지를 결정
	 * 
	 * 무한반복 만들기
	 * for(;;) {}
	 * 
	 */
	public void test4() {
		Scanner sc = new Scanner(System.in);
		String menu = "=============\n"
					+ "1. 떡뽁기(3000원)\n"
					+ "2. 순대(3500원)\n"
					+ "3. 튀김(2500원)\n"
					+ "----------\n"
					+ "입력 : ";
		
		int total = 0;
		
		for(;;) {
			// 메뉴 노출 및 선택
			System.out.print(menu); 
			int choice = sc.nextInt(); 
			System.out.printf("%d 번을 선택하셨습니다.%n", choice);
			
			// 결제금액 합산
			switch(choice) {
			case 1: total += 3000; break;
			case 2: total += 3500; break;
			case 3: total += 2500; break;
			default: System.out.println("잘못 누르셨습니다.");
			}
			
			// 계속 여부 입력
			System.out.print("주문을 계속 하시겠습니까? (y/n) : ");
			char yn = sc.next().charAt(0);
			if(yn == 'n')
				break; // for블럭을 중지/탈출
		}
		
		System.out.printf("주문끝. 결제금액은 %d원입니다.%n", total);
	}
	
	/**
	 * 사용자이름과 반복횟수를 입력받아서 출력!
	 *  - 이름 : 홍길동
	 *  - 횟수 : 3
	 *  
	 *  홍길동
	 *  홍길동
	 *  홍길동
	 */
	public void test3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("횟수 : ");
		int cnt = sc.nextInt();
		
		for(int i = 0; i < cnt; i++) {
			System.out.println(name);
		}
		
		
	}
	
	
	public void test2() {
		// 10 9 8 7 6 5 4 3 2 1
		for(int i = 10; i > 0; --i) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		// 20 18 16 14 12 10 8 6 4 2
		for(int i = 20; i > 0; i -= 2) {
			System.out.print(i + " ");
		}
		System.out.println();

		// 5 6 7 8 9
		for(int k = 0; k < 5; k++) {
			System.out.print((k + 5) + " ");
		}
		
		System.out.println();
		
		// 구구단 8단 
		// 8 * 1 = (8 * 1)
		// 8 * 2 = (8 * 2)
		// 8 * 3 = (8 * 3)
		// 8 * 4 = (8 * 4)
		// 8 * 5 = (8 * 5)
		// 8 * 6 = (8 * 6)
		// 8 * 7 = (8 * 7)
		// 8 * 8 = (8 * 8)
		// 8 * 9 = (8 * 9)
		for(int n = 1; n <= 9; n++) {
			System.out.printf("8 * %d = %d%n", n, n * 8);
		}
		
		
		
	}
	
	/**
	 * for(초기식; 조건식; 증감식){
	 * 		// 실행코드
	 * }
	 *
	 * - 초기식 : for문실행시 처음에 한번만 실행. 증감변수를 초기화.
	 * - 조건식 : boolean으로 평가될 연산. true이면 블럭을 실행, false면 반복문 중지/탈출
	 * - 증감식 : 블럭실행이후 증감변수에 대한 증감식. 매턴의 마지막에 실행
	 * 
	 * 
	 * 증감변수는 어떻게 변해가는가?
	 * n번 반복할건가?
	 * 
	 */
	public void test1(){
		// 1 3 5 7 9 11 13 15 17 19
		for(int i = 1; i <= 20; i++) {
			if(i % 2 != 0) {				
				System.out.println(i);
			}
		}
	}
	
}
