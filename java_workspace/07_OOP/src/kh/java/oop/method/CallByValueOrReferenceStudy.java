package kh.java.oop.method;

import com.oop.emp.model.Employee;

/**
 * 
 * Call By Value 값에 의한 호출
 * 	- 기본타입의 값을 전달하는 경우, 동일한 값이 매개변수에 복사된다.
 * 
 * Call By Reference 참조에 의한 호출 
 * 	- 참조타입의 값을 전달하는 경우, 동일한 주소값이 매개변수에 복사된다.
 * 
 *
 */
public class CallByValueOrReferenceStudy {

	public static void main(String[] args) {
		int a = 10;
		int[] b = {1, 2, 3, 4, 5};
		
		CallByValueOrReferenceStudy study = new CallByValueOrReferenceStudy();
		
		a = study.test1(a); // 기본형 -> call by value
		System.out.println(a); // 100
		
		study.test2(b); // 참조형 -> call by reference 
		for(int n : b)
			System.out.print(n + " ");
		
		System.out.println();
		
		Employee emp = new Employee();
		System.out.println("전 :  " + emp.getEmpName() + " " + a);
		study.test3(emp, a);
		System.out.println("후 : " + emp.getEmpName() +  " " + a);
		
		// 예외적으로 String은 call by value인 것처럼 작동한다.
		String name = "안녕"; // call by value
		study.test4(name);
		System.out.println(name);
	}
	
	public void test4(String s) {
		s += "잘가";
	}
	
	public void test3(Employee e, int a) {
		e.setEmpName("홍길동");
		a += 2000;
	}
	
	public void test2(int k[]) {
		for(int i = 0; i < k.length; i++)
			k[i] *= 10;
	}
	
	public int test1(int n) {
		n *= 10;
		return n; // 리턴값
	}

}
