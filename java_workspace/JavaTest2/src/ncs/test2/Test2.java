package ncs.test2;

public class Test2 {

	/**
	 * 프로그램 시작시 어떤 문자열 전달~
	 * 
	 * 입력한 값은 공백을 기준으로 잘라서 문자열 배열로 main메소드에 전달된다.
	 * 
	 * "abc def 123" -> {"abc", "def", "123"}
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 1. char[]로 변경해 풀이
//		char[] chArr = args[0].toCharArray();
//
//		for (int i = chArr.length - 1; i >= 0; i--) {
//			char temp = chArr[i];
////			System.out.println(temp);
//
//			// 대문자로 변경 : 'a' - 'A'
//			if (temp >= 'a' && temp <= 'z')
//				temp -= 32;
//			System.out.print(temp);
//		}

		// 2. StringBuilder
		String str = args[0];
		System.out.println(str);
		
		// 뒤집기
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		System.out.println(sb);
		
		// 대문자로 변경
		// String의 toUpperCase를 사용하기 위해 StringBuilder를 String으로 변환
		String result = sb.toString().toUpperCase();
		System.out.println(result);

	}

}
