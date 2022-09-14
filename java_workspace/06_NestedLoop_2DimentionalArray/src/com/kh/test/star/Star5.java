package com.kh.test.star;
/*
@실습문제5

*****
   ***  
      *    

*/
public class Star5 {

	public static void main(String[] args) {
		Star5 s = new Star5();
		s.test1();
		s.test2();
		
	}

	private void test2() {
		int total = 5;
		for (int i = total/2; i >=0; i--) {
			for (int j = 0; j < total; j++) {
				System.out.print(
						(j >=(total/2)-i && j <=(total/2)+i)?"*":"  "
						);
			}
			System.out.println();
		}
	}


	public void test1() {
		int total = 5;
		System.out.println("---------@실습문제5---------");
		for (int i = 0; i < total; i+=2) {
			
			for (int j = 0; j <total-i; j++) {			
				
				for (int k = 0; k < i; k++) {
					if(j >0) break;
					System.out.print(" ");
				}				
				System.out.print("*");
			} 
			System.out.println("");
		}
		
	}

}
