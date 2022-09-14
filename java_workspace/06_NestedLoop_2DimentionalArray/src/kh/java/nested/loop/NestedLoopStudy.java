package kh.java.nested.loop;

import java.util.Scanner;

public class NestedLoopStudy {

	public static void main(String[] args) {
		NestedLoopStudy study = new NestedLoopStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
//		study.test6();
//		study.test7();
//		study.test8();
		study.test9();
	}
	
	/**
	 * □□□
	 * □
	 * □□□
	 * □
	 * □□□ 
	 */
	public void test9() {
		for(int i = 0; i < 5; i++) {
			if(i % 2 == 0)
				System.out.println("□□□");
			else
				System.out.println("□");
		}
		
		System.out.println();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print("□");
				if(i % 2 != 0)
					break;
			}
			System.out.println();
		}
	}
	
	/**
	 * ★
	 * ★★
	 * ★★★
	 * ★★★★
	 * ★★★★★
	 */
	public void test8() {
		/*
		 * i값에 따른 안쪽 반복문 반복횟수
		 * i=0 - 1
		 * i=1 - 2
		 * i=2 - 3
		 * i=3 - 4
		 * i=4 - 5
		 */
		for(int i = 0; i < 5; i++) {
			for(int k = 0; k <= i; k++) {
				System.out.print("★");
			}
			System.out.println();
		}
		
		
//		for(int i = 0; i < 5; i++) {
//			for(int k = 0; k < 5; k++) {
//				System.out.print("★");
//				if(i == k)
//					break;
//			}
//			System.out.println();
//		}
	}
	
	/**
	 * 라벨이 있는 반복문 continue
	 * - 각 단에서 dan 과 n이 같을때까지만 출력
	 */
	public void test7() {
		abc:
		for(int dan = 2; dan <= 9; dan++) {
			System.out.println("=== " + dan + "단 ===");
			for(int n = 1; n <= 9; n++) {
				System.out.println(dan + " * " + n + " = " + (dan * n));
				if(dan == n) {
					System.out.println();
					continue abc;					
				}
			}
			
		}
	}
	
	/**
	 * 라벨이 있는 반복문 break
	 * 
	 *  - dan * n의 결과가 50미만까지만 출력하기
	 */
	public void test6() {
		outer:
		for(int dan = 2; dan <= 9; dan++) {
			System.out.println("=== " + dan + "단 ===");
			for(int n = 1; n <= 9; n++) {
				if(dan * n > 50)
					break outer;
				
				System.out.println(dan + " * " + n + " = " + (dan * n));
			}
			System.out.println();
		}
	}
	
	/**
	 * 2~9단 구구단 출력
	 */
	public void test5() {
		
//		for(int dan = 2; dan <= 9; dan++) {
//			System.out.println("=== " + dan + "단 ===");
//			for(int n = 1; n <= 9; n++) {
//				System.out.println(dan + " * " + n + " = " + (dan * n));
//			}
//			System.out.println();
//		}
		
		for(int dan = 2; dan <= 9; dan++) {
			for(int n = 1; n <= 9; n++) {
				if(n == 1)
					System.out.println("=== " + dan + "단 ===");
				
				System.out.println(dan + " * " + n + " = " + (dan * n));
				
				if(n == 9)
					System.out.println();
			}
		}
	}
	
	/**
	 * 별찍기
	 * 3x5
	 * ♣♣♣♣♣
	 * ♣♣♣♣♣
	 * ♣♣♣♣♣
	 * 
	 * 사용자로부터 행, 열, 문자를 입력받아 출력.
	 */
	public void test4() {
		Scanner sc = new Scanner(System.in);
		System.out.print("행 : ");
		int row = sc.nextInt();
		System.out.print("열 : ");
		int col = sc.nextInt();
		System.out.print("문자 : ");
		char ch = sc.next().charAt(0);
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(ch);
			}
			System.out.println();
		}
			
		
	}
	
	/**
	 * 1. 5행 7열 표의 좌표를 출력하세요.
	 * 2. 사용자로부터 행과 열을 입력받아 표의 좌표 출력하기
	 */
	public void test3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("행 : ");
		int row = sc.nextInt();
		System.out.print("열 : ");
		int column = sc.nextInt();
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				System.out.printf("(%d,%d) ", i, j);
			}
			System.out.println();
		}
	}
	
	/**
	 * 행(가로) - 바깥반복문
	 * 열(세로) - 안쪽반복문
	 */
	public void test2() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.print("(" + i + ", " + j + ") ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 중첩반복문 Nested Loop
	 * - 바깥반복문 1번에 안쪽반복문 n번이 처리되는 구조
	 * - 행열 표현에 적합. 바깥반복문 - 행, 안쪽반복문은 - 열
	 * 
	 */
	public void test1() {
		
		for(int i = 0; i < 3; i++) {
			System.out.println(i);
			
			for(int j = 0; j < 2; j++) {
				System.out.println("  " + j);
			}
		}
		
	}
}
