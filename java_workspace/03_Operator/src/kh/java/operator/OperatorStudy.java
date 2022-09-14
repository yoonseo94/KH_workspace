package kh.java.operator;

import java.util.Scanner;

public class OperatorStudy {

	public static void main(String[] args) {
		OperatorStudy study = new OperatorStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
		study.test6();
		
	}

	/**
	 * 복합대입연산자
	 * 
	 * += 
	 * -=
	 * *=
	 * /=
	 * %=
	 * 
	 */
	public void test6() {
		int a = 5;
//		a = a + 5;
		a += 5;
		System.out.println(a);
		
//		a = a - 3;
		a -= 3;
		System.out.println(a);
		
//		a = a * 2;
		a *= 2;
		System.out.println(a);
		
//		a = a / 5;
		a /= 5;
		System.out.println(a);
		
//		a = a % 2;
		a %= 2;
		System.out.println(a);
		
	}
	
	/**
	 * 삼항연산자
	 * 
	 * (조건식) ? (참일때 값) : (거짓일때 값)
	 *  - 조건식 : boolean 또는 boolean으로 평가될 연산식
	 * 
	 */
	public void test5() {
		int a = 100;
		String s = (a < 50) ? "a는 50보다 크다" : "a는 50보다 작다";
		System.out.println(s);
		
		// 사용자에게 입력받은 정수가 홀수/짝수인지 출력
		// 100은 짝수입니다.
		// 99는 홀수입니다.
		Scanner sc = new Scanner(System.in);
		System.out.print("정수입력 : ");
		int n = sc.nextInt();
		String nType = (n % 2 != 0) ? "홀" : "짝" ;
		System.out.printf("%d은/는 %s수입니다.%n", n, nType);
		
	}
	
	
	/**
	 * 이항연산자
	 * 
	 * 
	 * 
	 * && and &  
	 *  - a && b
	 *  - a b 모두 true일때, true를 반환 
	 *  
	 *  true && true 	-> true
	 *  true && false 	-> false
	 *  false && true 	-> false (우항을 검사하지 않음) 
	 *  false && false 	-> false (우항을 검사하지 않음)
	 * 
	 * || or | 
	 * 	- a || b
	 * 	- a, b중에 하나라도 true라면, true를 반환
	 * 
	 * 	true || true	-> true (우항을 검사하지 않음)
	 * 	true || false	-> true (우항을 검사하지 않음)
	 * 	false || true	-> true
	 * 	false || false 	-> false
	 * 
	 */
	public void test4() {
		int a = 10;
		int b = 7;
		System.out.println(a > 5 && b == 7);
		System.out.println(a > b && b-- < 7);
		System.out.println(a == b && b > 0);
		System.out.println(a < 0 && a - b == 100);
		System.out.println();
		
		System.out.println(a != b || a == 10);
		System.out.println(a * 2 > 0 || b - 7 != 0);
		System.out.println(b - a > 0 || a++ > b);
		System.out.println(a == b || b == a);
		
		int x = 1;
		int y = 1;
		
//		System.out.println(x != 1 && ++y == 2);
		System.out.println(x == 1 || ++y == 2);
		
		System.out.printf("x = %d, y = %d%n", x, y);
	}
	
	
	/**
	 * 이항연산자
	 * +
	 * -
	 * *
	 * /
	 * %
	 * 
	 */
	public void test3() {
		int a = 10;
		int b = 3;
		System.out.println(a + b);
		System.out.println(a - b);
		System.out.println(a * b);
		System.out.println(a / b); // 몫
		System.out.println(a % b); // 나머지
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int n = sc.nextInt();
		System.out.println("짝수인가? " + (n % 2 == 0));
	}
	
	public void test2() {
		int x = 10, y = 10;
		int z = ++x;
		
		System.out.println(z);
		System.out.println(x);
		
		z = y++;
		System.out.println(z);
		System.out.println(y);
		
		x = 20;
		z = x++ + --y;
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		
		int m = 20;
		int n = m++ + m;
		System.out.println(m);
		System.out.println(n);
		
		
	}
	
	/**
	 * 1. 단항연산자
	 * + -
	 * 
	 * ++
	 * --
	 */
	public void test1() {
		int a = +3;
		int b = -3;
		System.out.println(a);
		System.out.println(b);
		
		// 증감연산자
		// 1. 전위증감연산자 ++c --c : 먼저 1증감후에 인접연산처리
		// 2. 후위증감연산자 c++ c-- : 먼저 인접연산에 사용된후 1증감처리
		int c = 10;
//		++c;
//		c = c + 1;
		
//		--c;
//		c = c - 1;
//		System.out.println(c);
		
		
		int k = 1;
		System.out.println(k++ + 1);
		System.out.println(k);
		
		int m = 3;
		int n = ++m;
		System.out.println(m);
		System.out.println(n);
		
	}
	
}
