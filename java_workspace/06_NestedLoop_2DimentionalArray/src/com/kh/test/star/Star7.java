package com.kh.test.star;

/*
@실습문제7 : 다이아몬드
	
	  *  
	 *** 
	*****
	 *** 
	  *  
*/
public class Star7 {

	public static void main(String[] args) {
		Star7 s = new Star7();
		s.test1();
		s.test2();
		s.test3();
	}
	/*
	 *  i - 공백 - 별개수
	 *  ------------------
		 0-2-1: 	  *  
		 1-1-3: 	 *** 
		 2-0-5:		*****
		 3-1-3:	 	 *** 
		 4-2-1: 	  *  
	 */
	public void test3() {
		System.out.println("------------  test3 ------------ ");
		int total = 5;
		int space = 2; //행별 공백수
		int star = 1;	//행별 찍어야 할 별의 수
		int mid = total/2; //행의 가운데: i와 mid를 비교해서 space,star값을 변경한다.
		
		for(int i=0; i<total; i++) {
			
			for(int j=0; j<space+star; j++) {
				System.out.print(j<space?" ":"*");
			}
			
			if(i<mid) {
				space--;
				star += 2;
			}	
			else {
				space++;
				star -= 2;
			}
			System.out.println();
		}
	}

	private void test2() {
		System.out.println("------------  test2 ------------ ");
		int total = 5;
		for (int i = 0, j = 0; i <= total; i++) {
			for (int k = 0; k <= total/2+j; k++) 
				System.out.print((k>=total/2- j)?"*":" ");
			if(i<total/2) j++;
			else j--;
			System.out.println();
		}
	}




	/*
 	
		"*"이 출력되어질 부분
		"  "이 출력되어질 부분을 나누어 생각한다.
			
	  문제7      (i,j)좌표
	 -----------------------------------------
	   *       	 (0,0) (0,1) (0,2) (0,3) (0,4)   
	  *** 	     (1,0) (1,1) (1,2) (1,3) (1,4) 
	 *****       (2,0) (2,1) (2,2) (2,3) (2,4)
	  ***        (3,0) (3,1) (3,2) (3,3) (3,4)
	   *         (4,0) (4,1) (4,2) (4,3) (4,4)
		        
	    1. "*" 이 출력되어져야할 대상
				  (0,2)
			(1,1) (1,2) (1,3)
	   (2,0) (2,1) (2,2) (2,3) (2,4)
	   
	   ∴ (i+j >= 2) && (j-i <= 2) 일때만 "*" 출력한다. //정해진 좌표의 관계를 고려해서 rule을 찾아낸다.
	   
	   
	    2. " " 이 출력되어져야할 대상
	   (0,0) (0,1)       (0,3) (0,4)
	   (1,0)                   (1,4) 
	   
	    3. "*" 이 출력되어져야할 대상
	         (0,1) (0,2) (0,3)
	               (0,2)
	      
	  ∴ (i+j<=3) && (i-j <0) 일때만 "*" 출력한다.
	   
	    4. " " 이 출력되어져야할 대상
	    (0,0)                  (0,4)
	    (1,0) (1,1)      (1,3) (1,4)
		
	*/
	public void test1() {
		System.out.println("------------  test1 ------------ ");
		
		/*
		 * 위 삼각형
		 */
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j <5 ; j++) {
				System.out.print((i+j>=2) && (j-i <=2)?"*":" ");				
			}
			System.out.println();
			
		}
		
		/*
		 * 아래 삼각형
		 */
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print((i+j<=3) && (i-j <0)?"*":" ");
			}
			System.out.println();
		}
		
		
	}

}
