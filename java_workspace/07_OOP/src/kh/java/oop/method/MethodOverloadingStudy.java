package kh.java.oop.method;

/**
 * Method Overloading
 * - 동일한 기능을 하는 메소드 여러개를 동일한 이름으로 관리하는 것.
 * - 매개변수선언부 모두 달라야한다. (타입, 순서, 개수)
 * - 리턴타입, 접근제한자, 매개변수명은 상관치 않는다.
 * 
 *
 */
public class MethodOverloadingStudy {

	public static void main(String[] args) {
		MethodOverloadingStudy study = new MethodOverloadingStudy();
		study.printParam(100);
		study.printParam(1000L);
		study.printParam("안녕", 12.34);
		
	}

//	public void printParam(int b) {}
//	public int printParam(int a) { return 100; }
//	private void printParam(int a) {}
	
	public void printParam(int a) {
		System.out.println(a);
	}
	public void printParam(long a) {
		System.out.println(a);
	}
	public void printParam(String str, double d) {
		System.out.println(str + d);
	}
	
}
