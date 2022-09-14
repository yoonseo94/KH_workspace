package kh.java.two.dimentional.array;

public class TwoDimentionalArrayStudy {

	public static void main(String[] args) {
		TwoDimentionalArrayStudy study = new TwoDimentionalArrayStudy();
//		study.test1();
//		study.test2();
		study.test3();
	}
	
	/**
	 * 2차원배열 초기화
	 * - 배열할당 + 번지수별 초기화
	 * 
	 */
	public void test3() {
//		int[][] arr = {{1, 2, 3}, {4, 5, 6}};
		int[][] arr = new int[][]{{1, 2, 3}, {4, 5, 6}};
		
//		for(int i = 0; i < arr.length; i++) {
//			for(int j = 0; j < arr[0].length; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		
//		System.out.println(arr);
		// for each문
		for(int[] ar : arr) {
//			System.out.println(ar);
			
			for(int a : ar) {
				System.out.print(a + " ");
			}
			System.out.println();
			
		}
		
	}
	
	/**
	 * 알파벳배열 char[2][26]
	 * - 0행 대문자
	 * - 1행 소문자
	 * 
	 */
	public void test2() {
		char[][] alphabet;
		alphabet = new char[2][26];
		
		System.out.println(alphabet.length); // outer
		System.out.println(alphabet[0].length); // inner
		
		// 값대입
		for(int i = 0; i < alphabet.length; i++) {
			for(int j = 0; j < alphabet[0].length; j++) {
				alphabet[i][j] = (char)((i == 0 ? 'A' : 'a') + j);
			}
		}
		
		// 출력
		for(int i = 0; i < alphabet.length; i++) {
			for(int j = 0; j < alphabet[0].length; j++) {
				System.out.print(alphabet[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * 2차원배열
	 */
	public void test1() {
		// 1.배열 참조변수 선언
		int[][] arr;
		
		// 2.배열 할당 - int의 기본값 0으로 초기화됨
		arr = new int[3][2]; // 3행 2열
		
		// 3.값대입 & 사용
		// 배열요소간 규칙성이 발견되는 경우는 중첩반복문과 별도의 증감변수를 이용해 값대입!
		int k = 1;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				arr[i][j] = k++;
			}
		}
		
//		arr[0][0] = 1;
//		arr[0][1] = 2;
//		arr[1][0] = 3;
//		arr[1][1] = 4;
//		arr[2][0] = 5;
//		arr[2][1] = 6;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.printf("arr[%d][%d] : %d%n", i, j, arr[i][j]);
			}
		}
		
		
//		System.out.println(arr[0][0]);
//		System.out.println(arr[0][1]);
//		System.out.println(arr[1][0]);
//		System.out.println(arr[1][1]);
//		System.out.println(arr[2][0]);
//		System.out.println(arr[2][1]);
	}
	
}
