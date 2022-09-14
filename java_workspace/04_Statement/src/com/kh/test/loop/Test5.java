package com.kh.test.loop;

import java.util.Scanner;

/*
	3명의 회원정보를 입력 받아 저장하고 출력하는 
	프로그램을 만드세요
	 입력 : 이름, 나이, 주소, 키, 몸무게, 연락처
	
	출력 예) 
	================ 저장회원===============
	1 홍길동 19세 서울시 강남구 170cm 68kg 01012345678
	2 임걱정 20세 경기도 수원시 180cm 75kg 01012345678

	                           
	//기능추가1
	//출력결과 마지막행에 다음 항목에 대한 평균을 구하시오.
	============================================
	평균나이 00세 / 평균 키 : 00CM / 평균 몸무게 : 00kg
.
	//기능추가2
	//입력할 회원 수를 입력하고 그 수 만큼 회원을 입력 받게 업그레이드 하세요

                             .

 */
public class Test5 {

	public static void main(String[] args) {
		Test5 t = new Test5();
//		t.test1();
//		t.test2(); // 기능추가1 : 총합/평균구하기
		t.test3(); // 기능추가2 : 회원수 지정 
		
	}

	private void test1() {
		Scanner sc = new Scanner(System.in);
		String name = "";
		int age = 0;
		String addr = "";
		int height = 0;
		int weight = 0;
		String phone = "";
		
		int memberCnt = 3;
		
		String result = "=================== 저장회원 ==================\n";
		
		for (int i = 0; i < memberCnt; i++) {
			System.out.println("-------------- " + (i + 1) + " -----------------");
			
			System.out.print("이름을 입력하세요 : ");
			name = sc.next();
			
			System.out.print("나이를 입력하세요 : ");
			age = sc.nextInt();
			
			sc.nextLine(); // 개행문자 날리기용
			System.out.print("주소를 입력하세요 : ");
			addr = sc.nextLine();
			
			System.out.print("키를 입력하세요 : ");
			height = sc.nextInt();
			
			System.out.print("몸무게를 입력하세요 : ");
			weight = sc.nextInt();
			
			System.out.print("연락처를 입력하세요 : ");
			phone = sc.next();
			
			result += (i + 1) + "\t" 
					+ name + "\t" 
					+ age + "\t" 
					+ addr + "\t" 
					+ height + "cm\t" + weight + "kg\t"
					+ phone + "\n";
		}
		
		result += "===========================================\n";
		System.out.println(result);
		
	}
	private void test2() {
		Scanner sc = new Scanner(System.in);
		String name = "";
		int age = 0;
		String addr = "";
		int height = 0;
		int weight = 0;
		String phone = "";

		int memberCnt = 3;

		String result = "=================== 저장회원 ==================\n";

		// 기능추가1 : 총합/평균구하기
		int sumAge = 0;
		int sumHeight = 0;
		int sumWeight = 0;

		double avgAge = 0;
		double avgHeight = 0;
		double avgWeight = 0;

		for (int i = 0; i < memberCnt; i++) {
			System.out.println("-------------- " + (i + 1) + " -----------------");

			System.out.print("이름을 입력하세요 : ");
			name = sc.next();

			System.out.print("나이를 입력하세요 : ");
			age = sc.nextInt();

			sc.nextLine();
			System.out.print("주소를 입력하세요 : ");
			addr = sc.nextLine();

			System.out.print("키를 입력하세요 : ");
			height = sc.nextInt();

			System.out.print("몸무게를 입력하세요 : ");
			weight = sc.nextInt();

			System.out.print("연락처를 입력하세요 : ");
			phone = sc.next();

			result += (i + 1) + "\t" + name + "\t" + age + "\t" + addr + "\t" + height + "cm\t" + weight + "kg\t"
					+ phone + "\n";

			// 기능추가1 : 총합/평균구하기
			sumAge += age;
			sumHeight += height;
			sumWeight += weight;
		}

		avgAge = (double) sumAge / memberCnt;
		avgHeight = (double) sumHeight / memberCnt;
		avgWeight = (double) sumWeight / memberCnt;

		result += "===========================================\n";

		System.out.println(result);
		System.out.printf("평균나이 : %.1f세 | 평균신장 : %.1fcm | 평균몸무게 : %.1fkg", avgAge, avgHeight, avgWeight);

	}

	private void test3() {
		Scanner sc = new Scanner(System.in);
		String name = "";
		int age = 0;
		String addr = "";
		int height = 0;
		int weight = 0;
		String phone = "";

		// 기능추가2 : 회원수 지정
		System.out.print("몇명의 회원을 등록할까요? => ");
		int memberCnt = sc.nextInt();

		String result = "=================== 저장회원 ==================\n";

		// 기능추가1 : 총합/평균구하기
		int sumAge = 0;
		int sumHeight = 0;
		int sumWeight = 0;

		double avgAge = 0;
		double avgHeight = 0;
		double avgWeight = 0;

		for (int i = 0; i < memberCnt; i++) {
			System.out.println("-------------- " + (i + 1) + " -----------------");

			System.out.print("이름을 입력하세요 : ");
			name = sc.next();

			System.out.print("나이를 입력하세요 : ");
			age = sc.nextInt();

			sc.nextLine();
			System.out.print("주소를 입력하세요 : ");
			addr = sc.nextLine();

			System.out.print("키를 입력하세요 : ");
			height = sc.nextInt();

			System.out.print("몸무게를 입력하세요 : ");
			weight = sc.nextInt();

			System.out.print("연락처를 입력하세요 : ");
			phone = sc.next();

			result += (i + 1) + "\t" + name + "\t" + age + "\t" + addr + "\t" + height + "cm\t" + weight + "kg\t"
					+ phone + "\n";

			// 기능추가1 : 총합/평균구하기
			sumAge += age;
			sumHeight += height;
			sumWeight += weight;
		}

		avgAge = (double) sumAge / memberCnt;
		avgHeight = (double) sumHeight / memberCnt;
		avgWeight = (double) sumWeight / memberCnt;

		result += "===========================================\n";

		System.out.println(result);
		System.out.printf("평균나이 : %.1f세 | 평균신장 : %.1fcm | 평균몸무게 : %.1fkg", avgAge, avgHeight, avgWeight);


	}

}
