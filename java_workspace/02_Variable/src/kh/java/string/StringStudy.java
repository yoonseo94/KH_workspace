package kh.java.string;

public class StringStudy {

	public static void main(String[] args) {
		StringStudy study = new StringStudy();
		study.test1();
	}
	
	/**
	 * String은 참조형이지만, 기본형처럼 사용가능한 유일한 타입
	 * - 값(literal)을 직접 대입하는 형태로 사용가능
	 */
	public void test1() {
		String s1 = "기차"; // 문자열 리터럴 - 쌍따옴표로 감싸서 표현
		String s2 = new String("기차");
		
		
		
		// 문자열 더하기 연산을 지원
		s1 = s1 + "칙칙폭폭";
		s2 = s2 + "칙칙푹푹";
		
		System.out.println(s1);
		System.out.println(s2);
		
		String s3 = s1 + " " + s2; 
		System.out.println(s3);
		
		String s4 = 1 + 2 + 3 + s1; // 6 + s1 
		String s5 = s1 + 1 + 2 + 3; // s1 + "1" + "2" + "3"
		
		System.out.println(s4);
		System.out.println(s5);
		
		
	}
}
