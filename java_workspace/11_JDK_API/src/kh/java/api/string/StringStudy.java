package kh.java.api.string;

import java.util.StringTokenizer;

public class StringStudy {

	public static void main(String[] args) {
		StringStudy study = new StringStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
		study.test6();
	}
	
	/**
	 * String api
	 */
	public void test6() {
		String str = "application";
		System.out.println(str.indexOf("ca"));	// 5
		System.out.println(str.indexOf("kkk")); // -1 존재하지 않는경우
	}
	
	/**
	 * 여러문자를 동시처리하기
	 */
	public void test5() {
		String data = "a,1,가\n"
					+ "b,2,나\n"
					+ "c,3,다";
		
		// split의 인자는 정규표현식(문자열검색표현식)
		// []는 문자하나의 옵션 - ,나 \n를 구분자로 사용하겠다. 
		String[] result = data.split("[,\n]");
		for(String s : result)
			System.out.println(s);
	}
	
	
	public void test4() {
		String data = "a,1,가\n"
					+ "b,2,나\n"
					+ "c,3,다";
//		System.out.println("[" + data + "]");
		
		// 2차원배열처럼 풀이
		String[] result1 = data.split("\n"); 
		for(int i = 0; i < result1.length; i++) {
//			System.out.println("[" + result1[i] + "]");
			String[] result2 = result1[i].split(",");
			for(int j = 0; j < result2.length; j++) {
				System.out.println("[" + result2[j] + "]");
			}
		}
		
		// 1차원배열처럼 풀이
		StringTokenizer tokenizer = new StringTokenizer(data, ",\n");
		while(tokenizer.hasMoreTokens())
			System.out.println("[" + tokenizer.nextToken() + "]");
		
		/*
		 a
		 1
		 가
		 b
		 2
		 나
		 c
		 3
		 다
		
		 */
	}
	
	/**
	 * 문자열 쪼개기
	 * - "java,oracle,html,css,javascript"
	 * - CSV Comma Seperated Value
	 * 
	 * 1. String#split
	 * 2. StringTokenizer
	 * 
	 */
	public void test3() {
		String data = "java/oracle/html//css/javascript";
		
		String[] result1 = data.split("/");
		System.out.println(result1.length);
		for(int i = 0; i < result1.length; i++) {
			System.out.println("[" + result1[i] + "]");
		}
		
		// tokenizer는 문자기준으로 사용된다. ' ' '/'
		// 값이 없는 token은 버려진다.
		/*
			java 
			
			 
			oracle
			 
			 
			html
			 
			
			css
			 
			 
			javascript
		 */
		StringTokenizer tokenizer = new StringTokenizer(data, "/");
		System.out.println(tokenizer.countTokens());
		
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken(); // 해당토큰을 tokenizer로 객체로 부터 가져온다.(제거)
			System.out.println("[" + token + "] - " + tokenizer.countTokens());
		}
		
		
		
		
		
	}
	
	/**
	 * 변경되는 String을 메모리효율적으로 관리하기 위해 mutable한 String클래스
	 * - StringBuilder : 싱글쓰레드용
	 * - StringBuffer : 멀티쓰레드용 동기화지원(여러 쓰레드에서 공유시 안전한 사용을 보장함)
	 */
	public void test2() {
		StringBuilder sb = new StringBuilder("java");
		System.out.println(sb);
		System.out.println(sb.hashCode());
		
		sb.append("oracle");
		System.out.println(sb);
		System.out.println(sb.hashCode());
	}
	
	/**
	 * String은 immutable이다.
	 * - 변경불가
	 * - Heap영역의 Literal저장소에 생성되어 관리된다.
	 */
	public void test1() {
		String s1 = "java";
		String s2 = "java";
		String s3 = new String("java");
		String s4 = new String("java");
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		
		// 동일성(주소값비교) ==
		// 등등성(객체정보비교) equals
		System.out.println(s1 == s2); // true
		System.out.println(s1 == s3); // false
		System.out.println(s3 == s4); // false
		
		// 문자열 값비교
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s3));
		System.out.println(s3.equals(s4));
		
		// equals의 결과 true라면 hashCode가 동일해야한다.
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
		System.out.println(s4.hashCode());
		
		// 문자열은 수정불가
		s1 += "oracle"; // "java"를 수정하는 것이 아니라, "javaoracle"을 추가로 생성하고 주소값을 새로 가지는 것.
		s3 += "oracle";
		System.out.println();
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
		System.out.println(s4.hashCode());
		
	}

}
