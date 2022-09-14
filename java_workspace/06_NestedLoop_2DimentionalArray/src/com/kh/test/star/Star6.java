package com.kh.test.star;


public class Star6 {

	public static void main(String[] args) {
		Star6 s = new Star6();
		s.test1();
		s.test2();
		s.test3();
		s.test4();
		s.test5();//배포 및 풀이과정 설명
	}

	/*
	 	i - 공백 - *개수
	 -----------------
	    0-4-1: 	    *
		1-2-3:    ***
		2-0-5:	*****
		3-2-3:    ***        
		4-4-1:      *  

	 */
	public void test5() {
		System.out.println("------------  test5 ------------ ");
		
		int total = 5;//5행, 5*
		int space = 4;//공백
		int mid = total/2;//가운데기준으로 i와 mid의 값을 비교해 space값을 변경한다.
		
		//i : 0 1 2 3 4 
		for(int i=0; i<total; i++) {
			//j : 0 1 2 3 4
			for(int j=0; j<total; j++) {
				System.out.print(j<space?" ":"*");
			}
			
			//space : 4 - 2 - 0 - 2 - 4
			if(i < mid) space -= 2;
			else space += 2;
			
			System.out.println();
		}
		
	}
	/*
	 1씩 순차적으로 증가하는 반쪽다이아몬드
	 
		 *
	    **
	   ***
	  ****
	 *****
	******
	 *****
	  ****
	   ***
	    **
	     *
	 */
	private void test4() {
		System.out.println("------------  test4 ------------ ");

		int total = 11;
		int k = 1;
		for (int i = 0; i < total; i++) {
			for (int j = 0; j < total; j++) {
				if (j < total / 2)
					continue;// 앞부분 공란제거

				System.out.print(j < total - k ? " " : "*");// 공란 혹은 * 출력
			}
			if (i < total / 2)
				k++;
			else
				k--;
			System.out.println();
		}

	}

	/*
	         *
	      ***
	    *****
	  *******
	*********
	  *******
	    *****
	      ***
	        *
	 */
	private void test3() {
		System.out.println("------------  test3 ------------ ");

		for (int i = 0, j = 0; i < 9; i++) {
	         for (int k = 0; k < j + 5; k++) {
	           System.out.print((k < (4 - j)) ? "  " : "*");
	         }
	         
	         if (i < 4) j++;
	         else j--;
	         
	         System.out.println();
		}
	}

	/*
	               *
	            ***
	          *****
	        *******
	      *********
	    ***********
	  *************
	***************
	  *************
	    ***********
	      *********
	        *******
	          *****
	            ***
	              *
	 */
	private void test2() {
		System.out.println("------------  test2 ------------ ");
		int total = 15;
		for (int i = 0, j = 0; i < total; i++) {
			for (int k = 0; k < total/2+1+j; k++) {
				System.out.print( (k<total/2-j)?"  ":"*" );
			}
			if (i<total/2) j++;
			else j--;
			System.out.println();
		}
		
	}

	/*
	   @실습문제6
	   
	      *
	    ***
	  *****
	*******
	  *****
	    ***
	      *
	 */
	public void test1() {
		System.out.println("------------  test1 ------------ ");
		int total = 7;//홀수에 한해 유효하다.
		for (int i = 1, a = -(total/2); i <= total; i++, a++) {
			
			for (int j = 0; j < total-Math.abs(a)*2; j++) {	//Math.abs()는 파라미터의 절대값을 반환한다.		
				//j 는 행마다 찍힐 별의 개수를 정한다. 행마다 개수의 차이는 2개씩이다.
				for (int k = 0; k <Math.abs(a)*2; k++) {//k는 *이 찍히기 전까지 공백의 개수이다.
					if(j >0) break;
					System.out.print(" ");
				}				
				System.out.print("*");
			} 
			System.out.println("");
		}
		
	}

}
