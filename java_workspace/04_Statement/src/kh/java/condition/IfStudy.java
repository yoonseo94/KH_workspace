package kh.java.condition;

import java.util.Scanner;

/**
 * 조건문
 * 
 * 1. if(조건식){}
 * 2. if(조건식){} else {}
 * 3. if(조건식1){} else if(조건식2){} ... [else {}]
 * 
 *
 */
public class IfStudy {

	public static void main(String[] args) {
		IfStudy study = new IfStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
		study.test6();
	}
	
	/**
	 * 중첩된 if문
	 * - nested if문
	 * 
	 */
	public void test6() {
		int n = 66;
		String grade = "F"; // char의 기본값 
		
		if(n >= 90) {
			grade = "A";
			
			if(n >= 95) {
				grade += "+"; // grade = grade + "+"
			}
			
		}
		else if(n >= 80) {
			grade = "B";
			
			if(n >= 85) {
				grade += "+";
			}
			
		}
		else if(n >= 70) {
			grade = "C";
			
			if(n >= 75) {
				grade += "+";
			}
			
		}
		else if(n >= 60) {
			grade = "D";
			
			if(n >= 65) {
				grade += "+";
			}
			
		}
		System.out.printf("점수 : %d, 학점 : %s%n", n, grade);
	}
	
	/**
	 * - if.. else if 블럭
	 * - multiple if블럭
	 */
	public void test5() {
		int n = 85;
		char grade = 'F'; // char의 기본값 
		
		if(n >= 90) {
			grade = 'A';
		}
		
		if(n >= 80 && n < 90) {
			grade = 'B';
		}
		
		if(n >= 70 && n < 80) {
			grade = 'C';
		}
		
		if(n >= 60 && n < 70) {
			grade = 'D';
		}
		
		System.out.printf("점수 : %d, 학점 : %c%n", n, grade);
	}
	
	/**
	 * 나이를 입력받고, 연령대를 출력하세요.
	 * - 70~ 노인
	 * - 40~ 중년
	 * - 20~ 젊은이
	 * - 14~ 청소년
	 * - 어린이
	 */
	public void test4() {
	
		Scanner sc = new Scanner(System.in);
		System.out.print("나이를 입력하세요 : ");
		int age = sc.nextInt();
		
		String type = "";
		
		if(age < 14) {
			type = "어린이";
		}
		else if(age < 20) {
			type = "청소년";
		}
		else if(age < 40) {
			type = "젊은이";
		}
		else if(age < 70) {
			type = "중년";
		}
		else {
			type = "노인";	
		}
		
		System.out.printf("당신은 %d세, %s입니다.%n", age, type);
	}
	
	/**
	 * if(조건식1){ 1 } else if(조건식2){ 2 } ...
	 * - 조건식1이 참인 경우, 1 블럭 실행
	 * - 조건식1이 거짓인 경우,
	 * 		- 조건식2가 참인 경우, 2 블럭 실행
	 * 		- 조건식2가 거짓인 경우, 
	 * 			- 조건식 3이 참인 경우, ... 
	 * 
	 *  A : 90 ~ 100
	 *  B : 80 ~ 89
	 *  C : 70 ~ 79
	 *  D : 60 ~ 69
	 *  F : ~ 59
	 */
	public void test3() {
		int n = 85;
		char grade = 'F'; // char의 기본값 
		
		if(n >= 90) {
			grade = 'A';
		}
		else if(n >= 80) {
			grade = 'B';
		}
		else if(n >= 70) {
			grade = 'C';
		}
		else if(n >= 60) {
			grade = 'D';
		}
		System.out.printf("점수 : %d, 학점 : %c%n", n, grade);
	}
	
	/**
	 * if(조건식){} else {}
	 * - 참인 경우, if블럭 실행
	 * - 거짓인 경우, else블럭 실행
	 */
	public void test2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int n = sc.nextInt();
		
		if(n % 2 == 1) {
			System.out.println("홀수");
		} 
		else {
			System.out.println("짝수");
		}
	}
	
	/**
	 * if(조건식){}
	 *  - 참인 경우, if블럭실행
	 *  - 거짓인 경우, 그냥 통과한다.
	 */
	public void test1() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int n = sc.nextInt();
		if(n > 0) {
			System.out.println("참참참!");
		}
		System.out.println("test1 끝!");
	}
	
}






