package kh.java.array;

import java.util.Scanner;

public class ArrayStudy {

	public static void main(String[] args) {
		ArrayStudy study = new ArrayStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
		study.test6();
	}
	
	/**
	 * 배열의 크기는 변경이 불가하다.
	 * 배열(객체)은 주소값을 null로 변경해서 삭제한다. -> Garbage Collector
	 */
	public void test6() {
		double[] arr = new double[3];
		
		// hashCode 객체를 관리하기 위한 고유값 key
		System.out.println(arr.hashCode());
		
		// 크기를 변경한게 아니라 크기가 10인 새로운 배열 할당. 
		arr = new double[10];
		System.out.println(arr.hashCode());
		
		// 주소값을 null 지정해서 객체 삭제
		double[] temp = arr;
		arr = null;
		
		// 연결된 배열이 없을때
		System.out.println(arr.length); // java.lang.NullPointerException
//		System.out.println(arr[0]);
	}
	
	/**
	 * String[]
	 * - 참조형타입의 기본값은 null이다.
	 * 
	 * 사용자에게 친구3명의 이름을 입력받고, 배열에 저장후 출력!
	 */
	public void test5() {
		Scanner sc = new Scanner(System.in);
		String[] names = new String[3];
		
		// 입력
		for(int i = 0; i < names.length; i++) {
			System.out.print("친구 이름 입력 : ");
			names[i] = sc.next();
		}
		
		// 출력
		// 당신의 친구는 홍길동, 신사임당, 장영실입니다.
		System.out.print("당신의 친구는 ");
		for(int i = 0; i < names.length; i++) {
			System.out.print(names[i]);
			if(i != (names.length - 1))
				System.out.print(", ");
			
		}
		System.out.print("입니다.");
		
	}
	
	/**
	 * 배열 초기화
	 *  - 배열변수선언, 배열할당, 인덱스별 값대입
	 */
	public void test4() { 
//		int[] scores = new int[] {100, 90, 80, 98, 65};
		int[] scores = {100, 90, 80, 98, 65};
		
		int sum = 0;
		double avg = 0.0;
		// 변수 : 배열(반복접근할 객체)
		for(int score : scores) {
			System.out.println(score);
			sum += score;
		}
		
		System.out.println("총점 : " + sum);
		System.out.println("평균 : " + ((double) sum / scores.length));
		
	}
	
	/**
	 * 관리할 데이터의 규칙이 있다면, 값대입도 반복문을 사용가능하다.
	 * 
	 */
	public void test3() {
		// 배열변수선언 & 배열할당
		char[] alphabet = new char[26];
		
		for(int i = 0; i < alphabet.length; i++) {
			alphabet[i] = (char)('A' + i); // 값대입
//			System.out.println(alphabet[i]); // 출력
		}
		
//		alphabet[0] = 'A';
//		alphabet[1] = 'B';
//		alphabet[2] = 'C';
		
		// foreach : 향상된 for문 
		// 하나의 요소를 담을 변수 : 반복접근할 배열
		// 인덱스를 사용할 수 없다.
		for(char ch : alphabet) {
			System.out.println(ch);
		}
		
	}
	
	/**
	 * 홍길동 학생 점수
	 * 100, 90, 80, 98, 65 
	 */
	public void test2() {
		final int LENGTH = 10;
		// 1. 배열변수 선언
		int[] scores;
		// 2. 배열할당
		scores = new int[LENGTH];
		// 3. 인덱스별 값대입 및 출력
		scores[0] = 100;
		scores[1] = 90;
		scores[2] = 80;
		scores[3] = 98;
		scores[4] = 65;
		
		System.out.println(scores[0]);
		System.out.println(scores[1]);
		System.out.println(scores[2]);
		System.out.println(scores[3]);
		System.out.println(scores[4]);
//		System.out.println(scores[5]); //  java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
		
		System.out.println("배열의 길이 속성 : " + scores.length);
		for(int i = 0; i < scores.length; i++) {
			System.out.println(scores[i]);
		}
		
	}
	
	/**
	 * 변수 : 값하나를 보관
	 * 배열 : 값여러개를 보관
	 * 
	 * 1.배열선언 : stack영역에 변수(공간)을 생성
	 * 2.배열할당 : heap영역 배열을 실제 생성하고, 그 주소값을 stack영역의 변수에 대입.
	 * 3.사용 : 변수와 인덱스를 이용해서 값대입하거나 사용
	 * 
	 */
	public void test1() {
		int a; // stack영역에 생성된 변수 자동으로 초기화되지 않는다.
		a = 10;
		System.out.println(a);
		
		// 1.배열변수 선언 - 참조형(4byte) 공간 생성
		int[] n;
		
		// 2.배열할당 - heap영역에 배열을 생성
		// heap영역에 생성된 int공간 4개는 0으로 초기화된다.
		// stack을 제외한 heap, static영역은 변수생성시 자동으로 기본값으로 초기화된다.
		// 타입별 기본값(0) 
		// - boolean(false)
		// - char(' ')
		// - int(0)
		// - double(0.0)
		n = new int[4];
		
		// 3.사용
//		n[0] = 10;
//		n[1] = 20;
//		n[2] = 30;
//		n[3] = 40;
		
		System.out.println(n[0]);
		System.out.println(n[1]);
		System.out.println(n[2]);
		System.out.println(n[3]); // 마지막 인덱스 : 길이 - 1
	}
	
}




