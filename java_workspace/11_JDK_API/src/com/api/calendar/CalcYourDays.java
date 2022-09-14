package com.api.calendar;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * [문제1] 
 * 패키지 com.api.calendar.CalcYourDays.java
 * 사용자로부터 생일(년, 월, 일)을 입력받고, 오늘이 태어난지 몇일째 되었는지 출력하세요.
 *
 * @author shqkel1863
 *
 */
public class CalcYourDays {

	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		CalcYourDays main = new CalcYourDays();
//		main.test1();
		main.test2();

		
	}

	public void test1(){

		System.out.print("생일을 입력하세요.\n년도(yyyy) : ");
		int yyyy = sc.nextInt();
		System.out.print("월 : ");
		int mm = sc.nextInt();
		System.out.print("일 : ");
		int dd = sc.nextInt();

		Calendar today = Calendar.getInstance(); // 시분초 정보 있음
		Calendar birth = new GregorianCalendar(yyyy, mm - 1, dd); // 시분초 정보 없음

		//날짜차이 계산
		double diff = (double)(today.getTimeInMillis()-birth.getTimeInMillis()) / 1000 / 60 / 60 / 24;
		System.out.println(diff);

		System.out.println("오늘은 " + (int) Math.ceil(diff) + "번째 날입니다.");
	}

	/**
	 * ChronoUnit.DAYS.between
	 */
	public void test2(){
		System.out.print("생일을 입력하세요.\n년도(yyyy) : ");
		int yyyy = sc.nextInt();
		System.out.print("월 : ");
		int mm = sc.nextInt();
		System.out.print("일 : ");
		int dd = sc.nextInt();

		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(yyyy, mm, dd );

		long diff = ChronoUnit.DAYS.between(birthday, today); // 몇일차를 표현.
		System.out.println("오늘이 태어난지 " + (diff + 1) + "일째입니다."); // 오늘은 (diff + 1)일째 (네이버 dday와 동일)

	}
}
