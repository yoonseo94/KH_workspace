package kh.java.oop.field;

public class FieldMain {

	public static void main(String[] args) {
		IPhone13 phone1 = new IPhone13();
		phone1.setOwner("홍길동");
		phone1.setNumber("01012341234");
		phone1.printInfo();
		
//		IPhone13.WIDTH = 7;
		
		IPhone13 phone2 = new IPhone13();
		phone2.setOwner("신사임당");
		phone2.setNumber("01022223333");
		phone2.printInfo();
		
		// 클래스변수는 클래스이름.변수명
		System.out.println(IPhone13.WIDTH);
		System.out.println(IPhone13.HEIGHT);
		
		// 다음과 같이 출력되도록 메소드 작성
		// 홍길동(01012341234)님이 신사임당(0102222333)으로 전화를 겁니다...
		phone1.callTo(phone2);
		
	}

}
