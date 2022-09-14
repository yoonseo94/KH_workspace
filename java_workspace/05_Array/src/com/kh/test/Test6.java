package com.kh.test;

import java.util.Scanner;

/*
	배열의 크기로 홀수인 양의 정수를 입력 받아 배열을 만드세요. 
	배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고, 
	중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하는 프로그램을 작성하세요.

 */
public class Test6 {
	public static void main(String[] args) {
		Test6 t = new Test6();
		t.test();
	}

	private void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("홀수인 양의정수를 하나 입력하세요 ==> ");
		int num = sc.nextInt();
		
		//int배열생성,할당
		int[] intArr = new int[num];
		
		//홀수면 실행
		if(num%2 == 1){
			int mid = num/2; //몫의 값이 배열의 가운데 인덱스 값과 동일하다.
			
			for (int i = 0; i < num; i++) {
				if (i<=mid) {
					intArr[i] = i+1;
				}
				else{
					intArr[i] = num-i;
				}
			}
		} // end of if(num%2 == 1)
		
		//확인용
		System.out.print("[");
		for (int i = 0; i < intArr.length; i++) {
			System.out.print(intArr[i]);
			System.out.print(i!=intArr.length-1?", ":"");
		}
		System.out.print("]");
	}
}
