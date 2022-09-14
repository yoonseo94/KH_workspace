package com.kh.java._import;

import java.util.Date;

import com.kh.java._import.other.Bar;

import a.b.c.ABC;

import java.lang.*;

public class ImportMain {

	public static void main(String[] args) {

		// A. 같은 package인 경우
		// 1.객체생성 : 클래스 -> 객체
		// 객체recipe 
		ImportMain main = new ImportMain(); 
		// 2.메소드호출
		main.test1();

		// 1.객체생성
		Foo foo = new Foo();
		// 2.메소드호출
		foo.test2();

		// B. 다른 package인 경우, import문이 필요하다.
		Bar bar = new Bar();
		bar.test3();
		
		// java api의 다른 클래스도 import문 작성후 사용가능하다.
		Date date = new Date();
		System.out.println(date);
		
		// a.b.c.ABC.hello 호출
		// Ctrl + Shift + o : 자동import
		ABC abc = new ABC();
		abc.hello();
		
		// C. 예외 : java.lang.*
		// import java.lang.*; 생략되어 있다.
		// 문자열 객체 생성
		String str = new String("자바 개발자");
		System.out.println(str);
		
	}
	
	public void test1() {
		System.out.println("안녕");
	}
	
}
