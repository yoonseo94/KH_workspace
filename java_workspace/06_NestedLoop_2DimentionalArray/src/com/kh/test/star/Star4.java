package com.kh.test.star;

public class Star4 {

	public static void main(String[] args) {
		Star4 s = new Star4();
		s.test1();
	}
	/*
		  @실습문제4
		    
		    *     
		   ***    
		  *****   
		 *******  
		*********
		 
	  */
	public void test1() {
		int total = 5;
		// i : 0 1 2 3 4
		for (int i = 0; i < total; i++) {
			
			// j : 0 1 2 3 4
			for (int j = 0; j < total*2; j++) {
				
				// *이 찍힌 좌표의 i,j값을 비교해 다음과 같은 공식을 도출했다.
				System.out.print((j >=4-i && j<=4+i)?"*":" ");		
			}
			System.out.println();
		}
	}

}
