package kh.java.two.dimentional.array;

public class JaggedArrayStudy {

	public static void main(String[] args) {
		JaggedArrayStudy study = new JaggedArrayStudy();
		study.test1();
	}
	
	/** 
	 * 가변배열
	 * - 2차원배열(참조의 참조)의 길이가 다른 2차원배열
	 * 
	 */
	public void test1() {
		// 1차원배열(참조)까지 생성
		int[][] arr = new int[3][];
		
		arr[0] = new int[3];
		arr[1] = new int[2];
		arr[2] = new int[10];
		
		System.out.println(arr);
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		
		int v = 1;
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				arr[i][j] = v++;
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
}
