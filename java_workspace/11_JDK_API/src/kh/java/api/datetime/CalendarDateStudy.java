package kh.java.api.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * Date - 기본생성자, Long타입생성자를 제외하고는 거의 Deprecated
 * Calendar
 * 
 * java.time패키지에서 LocalDate, LocalDateTime...을 제공
 *
 */
public class CalendarDateStudy {

	public static void main(String[] args) {
		CalendarDateStudy study = new CalendarDateStudy();
//		study.test1();
//		study.test2();
//		study.test3();
		study.test4();
	}
	
	/**
	 * Calendar -> Date
	 * Date -> Calendar
	 */
	public void test4() {
		// 1.Calendar -> Date
		Calendar cal = Calendar.getInstance();
		long epochTime = cal.getTimeInMillis();
		Date date = new Date(epochTime);
		
		System.out.println(cal);
		System.out.println(date);
		
		// 2.Date -> Calendar
		Date now = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(now);
		printCalendar(calendar);
		
	}
	
	/**
	 * 날짜 차이 계산하기
	 * 
	 * - Epoch Time
	 * 	- 1970년 1월 1일 자정 기준으로 누적된 밀리초를 반환 
	 * 
	 *  올림 		Math.ceil
	 *  반올림 	Math.round
	 *  내림 		Math.floor
	 */
	public void test3() {
		// 오늘
		Calendar today = Calendar.getInstance();
		// D-day 2/21 자정
		Calendar dday = new GregorianCalendar(2022, 2 - 1, 21);
		
		// millis초단위 변환후 계산
		long _dday = dday.getTimeInMillis();
		long _today = today.getTimeInMillis();
		System.out.println(_dday);
		System.out.println(_today);
		double diff = ((double) _dday - _today) / 1000 / 60 / 60 / 24; // 초 -> 분 -> 시 -> 일
		System.out.println(diff);
		
		// 올림처리
		System.out.println("오늘은 D" + (diff > 0 ? "-" : "+") + (int) (Math.ceil(diff)) + "일입니다.");
		
		
	}
	
	/**
	 * 특정일자를 가리키는 Calendar객체만들기
	 * 
	 * 2022/08/29 수료실
	 */
	public void test2() {
		// 설정1
		Calendar dday1 = Calendar.getInstance();
		dday1.set(2022, 8 - 1, 29); // 시분초 정보유지
		printCalendar(dday1);
		
		// 설정2
		Calendar dday2 = new GregorianCalendar(2022, 8 - 1, 29);
		printCalendar(dday2);
		
	}
	
	
	public void printCalendar(Calendar cal) {
		System.out.printf("%d년 %d월 %d일 %d시 %d분 %d초%n", 
						  cal.get(Calendar.YEAR),
						  cal.get(Calendar.MONTH) + 1,
						  cal.get(Calendar.DATE),
						  cal.get(Calendar.HOUR_OF_DAY),
						  cal.get(Calendar.MINUTE),
						  cal.get(Calendar.SECOND));
	}
	
	/**
	 * Calendar
	 */
	public void test1() {
		// Calendar객체 생성하기
//		Calendar cal1 = new Calendar(); // Calendar는 추상클래스
		Calendar cal1 = Calendar.getInstance();
		System.out.println(cal1);
		
		Calendar cal2 = new GregorianCalendar();
		System.out.println(cal2);
		
		// 년 월 일 시 분 초
		System.out.println(cal1.get(Calendar.YEAR));
		System.out.println(cal1.get(Calendar.MONTH) + 1); // 0-based value
		System.out.println(cal1.get(Calendar.DATE));
		System.out.println(cal1.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal1.get(Calendar.DAY_OF_WEEK)); // 일(1), 월(2), 화(3), 수(4), 목(5), 금(6), 토(7)
		String[] weeks = {"일", "월", "화", "수", "목", "금", "토"};
		System.out.println(weeks[cal1.get(Calendar.DAY_OF_WEEK) - 1]);
		
		System.out.println(cal1.get(Calendar.HOUR)); 		// 12시간제 0 ~ 11
		System.out.println(cal1.get(Calendar.HOUR_OF_DAY)); // 24시간제 0 ~ 23
		System.out.println(cal1.get(Calendar.MINUTE));
		System.out.println(cal1.get(Calendar.SECOND));
		System.out.println(cal1.get(Calendar.MILLISECOND));
		
		System.out.println(cal1.getActualMaximum(Calendar.DATE)); //말일 구하기
	}

}







