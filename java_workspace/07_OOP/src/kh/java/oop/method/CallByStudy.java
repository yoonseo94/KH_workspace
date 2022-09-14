package kh.java.oop.method;

import kh.java.oop.constructor.User;

public class CallByStudy {

	public static void main(String[] args) {
		CallByStudy study = new CallByStudy();
		
		// 기본형
		double d = 3.3;
		study.test1(d); // 기본형이므로 값복사 일어나고 3.3을 변수가 두개가 된다.
		System.out.println("main#d = " + d);
		
		// 참조형 : 주소값이 복사가 되므로 heap영역의 User객체를 main#user, test2#user도 동일하게 참조한다.
		User user = new User();
		study.test2(user);
		System.out.println("main#user = " + user.getName());
		System.out.println(user.hashCode());	
	}
	
	public void test2(User user) {
		user.setName("홍길동");
		System.out.println("test2#user = " + user.getName());
		System.out.println(user.hashCode());
	}
	
	public void test1(double n) {
		n = n + 3;
		System.out.println("test1#n = " + n);
	}

}
