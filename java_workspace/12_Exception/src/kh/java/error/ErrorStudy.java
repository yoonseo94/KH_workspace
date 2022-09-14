package kh.java.error;

/**
 * Error
 * 프로그램 수행시 치명적상황이 발생해 소스코드상 해결이 불가능한 경우 
 *
 */
public class ErrorStudy {

	public static void main(String[] args) {
		ErrorStudy study = new ErrorStudy();
//		study.test1();
		study.test2();
	}
	
	/**
	 * StackOverflowError
	 * java.lang.StackOverflowError : 호출 스택을 모두 소진한 경우
	 */
	public void test2() {
		System.out.println("안녕");
		test2();
	}

	/**
	 * OutOfMemoryError
	 * 
	 * java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	 */
	public void test1() {
		long[] arr = new long[Integer.MAX_VALUE];
	}

}
