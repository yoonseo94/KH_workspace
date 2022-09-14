package kh.java.api.format;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateNumberStudy {

	public static void main(String[] args) throws ParseException {
		FormatDateNumberStudy study = new FormatDateNumberStudy();
//		study.test1();
		study.test2();
	}
	
	/**
	 * 숫자
	 * - 세자리마다 콤마찍기
	 * - 소수점이하 n번째까지 표현
	 * 
	 * - 0 자리수가 비어있으면 0으로 처리
	 * - # 자리수가 비어있으면 공란처리
	 * 
	 * 숫자를 잘라 표현해야 하는 경우 반올림처리된다.
	 */
	public void test2() {
		double n = 123.456;
//		DecimalFormat df = new DecimalFormat("0.##"); // 123.46
//		DecimalFormat df = new DecimalFormat("0.#####"); // 123.46
		DecimalFormat df = new DecimalFormat("0.00000"); // 123.46
		String result = df.format(n);
		System.out.println(result);
		
		// 세자리마다 콤마
		// ㄹ + 한자
		df.applyPattern("￦#,###원");
		System.out.println(df.format(1234567890));
	}

	/**
	 * SimpleDateFormat
	 * 1. SimpleDateFormat 포맷형식지정
	 * 2. SimpleDateFormat#format에 Date객체 전달
	 * @throws ParseException 
	 */
	private void test1() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-M-dd E요일 hh시 mm분 ss초");
		String formattedDate = sdf.format(new Date());
		System.out.println(formattedDate);
		
		Date date = sdf.parse(formattedDate);
		System.out.println(date);
	}
	
	

}
