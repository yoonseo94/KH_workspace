package com.api.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class TodayTest {

	public static void main(String[] args){
		TodayTest main = new TodayTest();
//		main.test1();
//		main.test2();
		main.test3();

	}

	private void test3() {
	    // E 목
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일", Locale.KOREA)));
	}


	private void test2() {
		LocalDate today = LocalDate.now();

		int year = today.getYear();
		int monthVal = today.getMonthValue(); // 월(1,2,3,...)
		int dayOfMonth = today.getDayOfMonth(); // 월의 몇번째 일

		DayOfWeek dayOfWeek = today.getDayOfWeek();  // 요일(MONDAY, TUESDAY,...)
		int dayOfWeekVal = dayOfWeek.getValue(); // 월 1 ~ 일 7
		String[] days = {"월", "화", "수", "목", "금", "토", "일"};
		System.out.println(dayOfWeekVal);
		System.out.println(days[dayOfWeekVal - 1]);

		System.out.println("오늘은 " + year + "년 " + monthVal + "월 " + dayOfMonth + "일 " + days[dayOfWeekVal - 1] + "요일");
	}

	public void test1(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK); //일요일:1 ~ 토요일:7
		String sDay="";

		switch(day){
			case 1: sDay = "일요일"; break;
			case 2: sDay = "월요일"; break;
			case 3: sDay = "화요일"; break;
			case 4: sDay = "수요일"; break;
			case 5: sDay = "목요일"; break;
			case 6: sDay = "금요일"; break;
			case 7: sDay = "토요일"; break;
		}

		System.out.println("오늘은 " + year + "년 " + month + "월 " + date + "일 " + sDay);
	}
}
