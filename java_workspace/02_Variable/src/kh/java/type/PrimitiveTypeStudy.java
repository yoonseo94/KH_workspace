package kh.java.type;

/**
 * bit : 0 또는 1을 보관
 * byte : 8 bit 0000 0000
 * kilo-byte : 1kb = 1024 byte
 * mega-byte : 1mb = 1024 kb
 * giga-byte : 1gb = 1024 mb
 * tera-byte : 1tb = 1024 gb 
 * peta-byte
 * exa-byte
 * zetta-byte
 * yotta-byte
 * 
 * 자료형
 * 1.기본형(원시형) : 변수에 실제값(literal)을 보관하는 타입 (123, 34.5, '안', true...)
 * 	- 문자형 '한' 			char (2byte) 			0 ~ 65535
 * 	- 논리형 true/false 	boolean (1byte)			
 *  - 정수형 123			byte (1byte)			-128 ~ 127
 *  					short (2byte)			-32768 ~ 32767
 *  					int (4byte) - 기본형		-2,147,483,648 ~ 2,147,483,647
 *  					long (8byte)			-922경 ~ 922경
 *  - 실수형 23.45		float (4byte)			소수점이하 7자리 표현
 * 						double (8byte) - 기본형	소수점이하 16자리 표현
 * 
 *  
 * 2.참조형 : 변수에 객체에 대한 주소값을 보관하는 타입 
 * 
 * 
 * 
 *
 */
public class PrimitiveTypeStudy {

	public static void main(String[] args) {
		PrimitiveTypeStudy study = new PrimitiveTypeStudy();
//		study.test1();
//		study.test2();
		study.test3();
	}
	
	/**
	 * Data Overflow
	 * - 자료형별 최대크기를 넘어서는 연산을 하면 다시 최소값으로 돌아간다.
	 */
	public void test3() {
		int i = Integer.MAX_VALUE; // 2147483647
		i = i + 1;
		System.out.println(i);
	}
	
	/**
	 * 초기화 - 변수선언과 값대입을 동시에 처리
	 * 
	 * 상수 Constant Variable
	 *  - 초기화 이후 값을 변경할수 없는 변수
	 */
	public void test2() {
//		int num;
//		num = 100;
		int num = 100;
		System.out.println(num);
		
		// 대입연산자 : 좌항 공간에 우항의 값을 대입한다.
		num = num + 100; // num 공간에 num값과 100을 더하기한 값을 대입한다.
		System.out.println(num);
		
		// 상수
		final int MY_AGE = 20;
		System.out.println(MY_AGE);
//		MY_AGE = MY_AGE + 1; // 값변경불가
		
		// jdk api의 선언된 상수
		// 숫자 + 문자열 -> 문자열
		System.out.println(Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
		System.out.println(Short.MIN_VALUE + " ~ " + Short.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
		System.out.println(Long.MIN_VALUE + " ~ " + Long.MAX_VALUE);
		
		
	}
	
	/**
	 * 변수 사용절차
	 * 1. 선언
	 * 2. 값대입
	 * 3. 사용 (출력, 다른변수에 대입)
	 */
	public void test1() {
		// 1.선언
		char ch;
		boolean bool;
		
		byte bnum;
		short sh;
		int num;
		long lnum;
		
		float fnum;
		double dnum;
		
		
		// 2.값대입
		ch = '한';
		bool = true;
		bool = false;
		dnum = 0.12345678901234567890;
		bnum = 123;
		sh = 3000;
		num = 100;
		fnum = 0.1234567890f; // 실수 기본타입인 double이 아닌 float 명시
		lnum = 12_345_689_012_345L; // 이 값을 long타입으로 명시
		
		// 3.사용
		System.out.println(ch);
		System.out.println(bool);
		
		System.out.println(bnum);
		System.out.println(sh);
		System.out.println(num);
		System.out.println(lnum);
		
		System.out.println(fnum);
		System.out.println(dnum);
	}
	
}





