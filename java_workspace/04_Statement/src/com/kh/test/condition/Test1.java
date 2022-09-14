package com.kh.test.condition;

import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		Test1 test = new Test1();
//		test.charTest();
		test.charTest2();
	}

	public void charTest2(){
		Scanner sc = new Scanner(System.in);
		System.out.println("0~9, 알파벳, 기타특수문자를 입력하세요.");
		
		char ch = sc.next().charAt(0);
		
		String result = "";
		
		if (Character.isDigit(ch))
			result = "숫자";
		else if (Character.isUpperCase(ch) || Character.isLowerCase(ch)) //ascii code표를 참고해서 범위를 설정한다. http://d.pr/mI6z
			result = "알파벳";
		else if (Test1.isKoreanCharacter(ch))
			result = "한글";
		else 
			result = "기타특수문자";
		
		System.out.println("입력하신 문자 "+ch + "는 "+ result + "입니다.");
		
	}
	
	public static boolean isKoreanCharacter(char ch) {
		return (ch >= '가') && (ch <= '힣');
	}
	
    public void charTest(){
		Scanner sc = new Scanner(System.in);
		System.out.println("0~9, 알파벳, 기타특수문자를 입력하세요.");
		
		char ch = sc.next().charAt(0);

		String result = "";
		
		if (ch >= '0' && ch <= '9')
			result = "숫자";
		else if (('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z')) //ascii code표를 참고해서 범위를 설정한다. http://d.pr/mI6z
			result = "알파벳";
		else if (ch >= '가' && ch <= '힣')
			result = "한글";
		else 
			result = "기타특수문자";
		
		System.out.println("입력하신 문자 "+ch + "는 "+ result + "입니다.");
		
	}

}
