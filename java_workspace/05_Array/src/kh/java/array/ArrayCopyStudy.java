package kh.java.array;

/**
 * 얕은복사 : 참조주소값만 복사. heap의 배열객체를 공유함.
 * 깊은복사 : 동일한 값을 가진 배열객체를 heap에 하나더 생성함.
 */
public class ArrayCopyStudy {

	public static void main(String[] args) {
		ArrayCopyStudy study = new ArrayCopyStudy();
//		study.test1();
		
//		study.test2();
//		study.test3();
//		study.test4();
		
//		study.test5();
		
		study.test6();
	}
	
	/**
	 * 메소드 호출시 call by reference
	 * - 메소드 호출시 참조형 매개변수는 얕은 복사로 처리된다.
	 * - 호출된 메소드에서 배열에 값변경이 일어나면 리턴후에도 여전히 유효하다.
	 */
	public void test6() {
		int[] arr = {1, 2, 3};
		print(arr);
		System.out.println(arr[1]);
	}
	
	public void print(int[] arr) {
		arr[1] *= 100;
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
	}
	
	/**
	 * System.arraycopy를 사용해 배열 복사 합치기
	 */
	public void test5() {
		int[] a = {1, 2, 3};
		int[] b = {4, 5, 6};
		int[] c = {7, 8, 9, 10};
		int[] d = new int[10];
		
		int start = 0;
		System.arraycopy(a, 0, d, start, a.length);
		start += a.length;
		System.arraycopy(b, 0, d, start, b.length);
		start += b.length;
		System.arraycopy(c, 0, d, start, c.length);
		
		for(int i : d)
			System.out.print(i + " ");
	}
	
	/**
	 * 깊은 복사3 - clone
	 */
	public void test4() {
		int[] arr1 = new int[] {1, 2, 3};
		int[] arr2 = arr1.clone();
		
		printArr(arr1, arr2);
		System.out.println(diffHashCode(arr1, arr2));
	}
	
	/**
	 * 깊은 복사2 - System.arraycopy
	 */
	public void test3() {
		int[] arr1 = new int[] {1, 2, 3};
		int[] arr2 = new int[arr1.length];
		

		// void java.lang.System.arraycopy(
		//			Object src, int srcPos, Object dest, int destPos, int length)
		// src배열의 srcPos번지부터 dest배열의 destPos번지로 length개 복사한다.
		System.arraycopy(arr1, 0, arr2, 0, arr1.length);
		
		printArr(arr1, arr2);
		System.out.println(diffHashCode(arr1, arr2));
		
		
		int[] arr3 = new int[10];
		System.arraycopy(arr1, 0, arr3, 4, 2);
		for(int i : arr3)
			System.out.print(i + " ");
		
		
	}
	
	/**
	 * 깊은 복사1 - 직접복사
	 * 
	 */
	public void test2() {
		int[] arr1 = {1, 2, 3};
		int[] arr2 = new int[arr1.length];
		
		for(int i = 0; i < arr1.length; i++) {
			arr2[i] = arr1[i];
		}
		
		arr1[1] *= 100;
		
		printArr(arr1, arr2);
		System.out.println(diffHashCode(arr1, arr2));
	}
	
	/**
	 * 얕은 복사
	 */
	public void test1() {
		
		int[] arr1 = {1, 2, 3};
		int[] arr2 = arr1;
		
		arr1[1] *= 100;
		
		printArr(arr1, arr2);
		
		System.out.println(diffHashCode(arr1, arr2));
	}
	
	public void printArr(int[] arr1, int[] arr2) {
		for(int i = 0; i < arr1.length; i++) {
			System.out.printf("%d: %d %d%n", i, arr1[i], arr2[i]);
		}
	}
	
	public boolean diffHashCode(int[] arr1, int[] arr2) {
		System.out.println(arr1.hashCode());
		System.out.println(arr2.hashCode());
		return arr1 == arr2; // 참조주소값 비교
	}
	
}




