package com.kh.java.print;

public class PrintMain {

	public static void main(String[] args) {
		PrintMain main = new PrintMain();
//		main.test1();
		main.test2();
	}
	
	/**
	 * System.out.println() - 내용출력후 개행처리 
	 * System.out.print() - 내용출력(개행처리 없음)
	 * 
	 * escaping문자 : 인쇄되지 않고, 키보드로 표현불가한 문자
	 *  (\ + 문자)
	 *  - \n 개행
	 *  - \t 탭
	 *  - \\ 역슬래시문자출력
	 *  - \" 쌍따옴표출력
	 *  
	 */
	public void test1() {
		System.out.print("안녕\n\n");
		System.out.print("\t잘가\n");
		System.out.print("배고프니\n");
		System.out.println("\\"); 
		System.out.println("철수가 내게 \"안녕\"이라고 말했음.");
		
		System.out.println("D:\\dev\\BANDIZIP-SETUP.EXE");
	}
	
	public void test2() {
		System.out.print("\t\t\"ShakeIt 알람\"\n---------------------------------------------------------------\n\t흔들기\t쏴리질러\t터치하기\t원터치\n---------------------------------------------------------------\n홍길동\n\t\"신사임당\"\n\t\t세종대왕");
	}
	
}
