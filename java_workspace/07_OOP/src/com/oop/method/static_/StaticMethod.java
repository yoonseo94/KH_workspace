package com.oop.method.static_;

public class StaticMethod {

	/**
	 * 1. 전달한 문자열을 모두 대문자로 바꾸는 static 메소드
	 * @param value
	 */
	public static String toUpper(String s){
		return s.toUpperCase();
	}
	
	/**
	 * 2. 문자열(1)에서 전달받은 인덱스(2)의 문자를 전달받은 문자(3)로 변경하는 static 메소드
	 * @param str
	 * @param index
	 * @param ch
	 * @return
	 */
	public static String _setChar(String str, int index, char ch){
		char[] arr = str.toCharArray(); // String -> char[]
		arr[index] = ch; 
		// char[] -> String
		return new String(arr); /*String 클래스 소스코드참조*/
	}
	
	public static String __setChar(String str, int index, char ch){
		// index이전 문자열 
		String before = str.substring(0, index); // index가 2인 경우 0~1읽어서 문자열로 반환
		System.out.println("before = " + before);
		
		// index이후 문자열
		String after = str.substring(index + 1);
		System.out.println("after = " + after);
		
		return before + ch + after;
	}
	
	public static String setChar(String str, int index, char ch){
		String newStr = "";
		for(int i = 0; i < str.length(); i++) {
			newStr += i == index ? ch : str.charAt(i);
		}
		return newStr;
	}
	
	/**
	 * 3. 전달한 문자열에서 영문자의 개수를 리턴하는 static 메소드
	 *
	 */
	public static int getAlphabetLength(String s){
		int cnt = 0;
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if(Character.isUpperCase(ch) || Character.isLowerCase(ch))
				cnt++;
		}
		return cnt; 
	}
	
	
	/**
	 * 4. 전달한 문자열값을 하나로 합쳐서 리턴 
	 * 
	 * @param str
	 * @return
	 */
	public static String concat(String s1, String s2){
		return s1 + s2;
	}
}
