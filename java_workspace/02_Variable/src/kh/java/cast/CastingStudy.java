package kh.java.cast;

/**
 * 컴퓨터 연산원리
 * 1. 값(literal)은 같은 타입의 변수에만 대입할 수 있다.
 * 2. 같은 타입끼리만 연산할 수 있다.
 * 3. 같은 타입간 연산결과값의 타입 또한 동일하다.
 * 
 *
 */
public class CastingStudy {

	public static void main(String[] args) {
		CastingStudy study = new CastingStudy();
//		study.test1();
//		study.test2();
		study.test3();
	}
	
	/**
	 * 연산간 자동형변환
	 *  
	 * > 크다 gt greater than
	 * < 작다 lt less than 
	 * >= 크거나 같다 ge greater than or equal to
	 * >= 작거나 같다 le less than or equal to
	 * 
	 * == 같다
	 * != 같지 않다
	 * 
	 */
	public void test3() {
		// true/false로 평가될수 있는 어떤 계산식
		boolean bool = 5 < 2; 
		System.out.println(bool);
		
		bool = ((5 / 2) == 2.0); // (int / int) == double -> 2 == 2.0
		System.out.println(bool);
		
		bool = ('C' == 67); // char == int -> int == int
		System.out.println(bool);
		
		bool = 'A' + 2 == 'B' + 1; // char + int == char + int -> 67 == 67
		System.out.println(bool);
		
		System.out.println(">" + !true);
		System.out.println(">" + !false);
		
		bool = !('a' != 97); // char == int -> int == int
		System.out.println(bool);
		
	}
	
	/**
	 * 명시적 형변환(강제) - 형변환에 따라 값이 유실될 수 있다.
	 */
	public void test2() {
		// 1.작은 타입으로 형변환
		int num = (int) 3.7; // double을 int로 강제형변환
		System.out.println(num);
		
		// 2.큰 타입으로 형변환
		int k = 10;
		int m = 3;
		System.out.println(((double) k) / m); // double / double -> double
		
		int i = Integer.MAX_VALUE;
		System.out.println((long) i + 1); // 2147483648
		// long + int -> long + long -> long
		System.out.println(i + 1L);
		
		// 3.예외적인 형변환
		// byte, short, char(int보다 작은 자료형) 연산시 자동으로 int로 우선 변환되어 처리
		byte b1 = 10;
		byte b2 = 20;
		byte b3 = (byte) (b1 + b2); // byte + byte -> int + int -> int 
		
		// int값을 char에 명시적 형변환 없이 대입가능
		char ch = 97; // int -> char
		System.out.println(ch);
		
	}
	
	/**
	 * 암묵적 형변환 (자동) - 형변환에 따라 값을 유실하지 않는다.
	 *  (실수는 지수표현식방식으로 적은공간에 훨씬 많은 수를 표현할 수 있다.)
	 * byte(1) -> short(2) -> int(4) -> long(8) -> float(4) -> double(8)
	 * 			  char(2)
	 *  
	 */
	public void test1() {
		// 다른 타입끼리 연산하는 경우, 크기가 큰 타입으로 자동형변환 된다.
		System.out.println(1 + 3.3); // int + double 
//		System.out.println(1.0 + 3.3); // double + double
		
		// 다른 타입의 변수에 값대입하는 경우(대입연산도 연산)
		double d = 3; // double = int
//		double d = 3.0;
		System.out.println(d);
		
		char ch = 'a'; // ascii --> ascii-extended --> unicode table
		int aNum = ch; // aNum공간에 ch값을 대입한다.
		System.out.println(aNum);
		
		// char는 정수형과는 숫자연산, 문자열과는 문자열 더하기 연산처리된다.
		System.out.println(ch + 100); // 197
		System.out.println(ch + "A"); // "aA"
		
	}
	
}




