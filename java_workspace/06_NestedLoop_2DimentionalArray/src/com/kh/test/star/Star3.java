package com.kh.test.star;

public class Star3 {

	public static void main(String[] args) {
		Star3 s = new Star3();
		s.test1();
	}
	/*
		@실습문제3
			
	 	  *
		 **
		***
	   ****
	  *****
	*/	
	public void test1() {
		int total = 5;
		
		// i : 1 2 3 4 5
		for (int i = 1; i <= total; i++) {
			
			// j : 1 2 3 4 5
			for (int j = 1; j <= total; j++) {				
				System.out.print((j<=(total-i))?" ":"*");
//				System.out.print((j>(total-i))?"*":" ");//동일한 결과
			}
			System.out.println("");
		}	
	}

}
