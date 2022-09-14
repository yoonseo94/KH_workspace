package com.kh.test.random;

import java.util.Random;
import java.util.Scanner;

public class RandomTest {

	public static void main(String[] args) {
		RandomTest r = new RandomTest();

		//@실습문제 : 가위바위보게임
		r.test1();
	}

	/**
	 * 가위바위보게임
	 */
	public void test1() {
		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		
		System.out.println("=== 가위 바위 보 게임 ===");
		System.out.print("숫자를 선택하세요(1.가위/2.바위/3.보) : ");
		
		int userNo = sc.nextInt();
		int comNo = rnd.nextInt(3) + 1;
		
		// 1, 2, 3이 아닌 경우 무효처리하기
		if(userNo != 1 && userNo !=2 && userNo !=3) {
			System.out.println("잘못 입력하셨습니다.");
			return; // 조기리턴 - 현재메소드를 실행중지하고, 호출부로 즉시 리턴!
		}
//		if(userNo < 1 || userNo > 3) {}
//		if(!(userNo == 1 || userNo == 2 || userNo == 3)) {}
		
		String user = userNo == 1 ? "가위" : (userNo == 2 ? "바위" : "보"); 
		String com = comNo == 1 ? "가위" : (comNo == 2 ? "바위" : "보"); 
		
		System.out.println("======= 결과 =======");
		System.out.println("당신은 " + user + "를 냈습니다");
		System.out.println("컴퓨터는 " + com + "를 냈습니다");
		
		System.out.println("===================");
		
		//실행결과 : 실행코드가 딱 한줄일때 중괄호 생략가능
		if (comNo == userNo)
			System.out.print("비겼습니다."); 			
		else if ((userNo == 1 && comNo == 3) || 
				(userNo == 2 && comNo == 1 ) ||
				(userNo == 3 && comNo == 2))
			System.out.print("당신이 이겼습니다."); 
		else if ((comNo == 3 && userNo == 2) ||
				 (comNo == 1 && userNo == 3) ||
				 (comNo == 2 && userNo == 1))
			System.out.print("당신이 졌습니다."); 
	}	

}

















