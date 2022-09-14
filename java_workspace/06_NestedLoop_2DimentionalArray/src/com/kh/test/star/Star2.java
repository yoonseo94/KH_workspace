package com.kh.test.star;

public class Star2 {

	public static void main(String[] args) {
		Star2 s = new Star2();
		s.test1();
		s.test2();
	}
	
	private void test2() {
		int total = 5;
		for (int i = 0; i < total; i++) {
            for (int j = 0; j <= i; j++)
                    System.out.print("*");
            System.out.println();
        }		
	}

	/*	
	 	@실습문제2
	 
	 	*
	 	**
	 	***
	 	****
	 	*****
	 */
	public void test1() {
		int total = 5;
		for (int i = 1; i <= total; i++) {			
			for (int j = 0; j < i  ; j++) {
				System.out.print("*");				
			}
			System.out.println("");
		}
	}

}
