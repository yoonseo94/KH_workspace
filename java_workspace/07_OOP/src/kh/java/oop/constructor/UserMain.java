package kh.java.oop.constructor;

import java.util.Date;

public class UserMain {

	public static void main(String[] args) {
		User user1 = new User();
		user1.setId("honggd");
		user1.setPassword("1234");
		user1.setName("홍길동");
		user1.setEnrollDate(new Date());
		System.out.println(user1.getUserInfo());
		
		User user2 = new User("sinsa", "1234");
		user2.setName("신사임당");
		System.out.println(user2.getUserInfo());
		
		User user3 = new User("sejong", "1234", "세종대왕", null);
		System.out.println(user3.getUserInfo());
		
	}

}
