package kh.java.printf;

public class PrintfStudy {
	public static void main(String[] args){
		PrintfStudy study = new PrintfStudy();
		study.test();	
	}

	private void test() {
		boolean b = true;
		
		char c = '헐';//(int)c : 54736
		char ga = '\uac00';//유니코드를 \\u(이스케이핑)+ac00으로 표현함. 
		char na = '\uB098'; 
		char da = '\ub2e4';
		char rak = '\uF914';
		String str = "가나다";
		
		int i = 12345;
		
		float f = 1234.567f;
		double d = 345.678;
			
		/*
		 * 문자형
		 * %c
		 * %s
		 * 
		 */
		System.out.printf("%c, %c, %c, %c, %c, %s", c, ga, na, da, rak, str);
		System.out.println();
		
		/*
		 * 숫자형-정수형
		 * %d
		 * %o
		 * %x
		 * 단, 2진수는 포맷지원하지 않음. => Integer.toBinaryString(i)
		 */
		System.out.printf("%d, 0x%x, 0%o, %s ",i, i, i, Integer.toBinaryString(i));
	
		System.out.printf("%,d\n", 1000000000); 
        System.out.printf("%,d\n", 1234);

		System.out.println();

		/*
		 * 
		 * 숫자형-실수형
		 * %f : 소수점아래6자리(기본값) 더 줄이거나 늘릴수 있음.
		 * %e : 지수형태표현
		 */
		System.out.printf("%f, %e", f, d);
		System.out.println();
		
		/*
		 * 논리형
		 * %b
		 */
		System.out.printf("%b", b);
		System.out.println();
		
		/*
		 * 너비 및 정렬방법
		 * %[flag][width]포맷
		 * flag생략시 우측정렬: +작성하면 안됨. 
		 * flag = -(음수) 좌측정렬
		 * %10d
		 * %-10d
		 */
		System.out.printf("%10d, %-10d.", i, i);
		System.out.println();
		/*
		 * 소수점아래 표시
		 * %.2f 소수점 둘째자리까지 표현
		 */
		System.out.printf("%.3f",123.45678789);
		System.out.println();
	}
}
