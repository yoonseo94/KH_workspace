package kh.java.oop.field;

/**
 * 선언위치에 따른 변수구분
 * a.전역변수(global variable | field) : 클래스영역에 선언. 접근제한자 사용
 * 	1. static field 클래스변수 - 클래스별로 지정해서 객체간 공유
 *  2. non-static field 멤버변수 인스턴스변수 - 객체별로 지정
 *  
 * b.지역변수(local variable) : 메소드영역에 선언. 접근제한자 없음.
 * 	- 매개변수 또한 지역변수이다.
 * 
 * 변수별 생명주기
 * 1. 클래스변수	: 프로그램시작시 생성(프로그램 최초 사용시 - 동적로딩) ~ 프로그램종료시 소멸
 * 2. 인스턴스변수	: 객체 생성(new연산자 사용시기) ~ 객체 소멸(참조가 끊어졌을때, 실제로는 garbage collection에 의해서 반환) 
 * 3. 지역변수 	: 메소드호출시 생성 ~ 메소드반환시 소멸
 * 
 *
 */
public class IPhone13 {
	
	public static final int WIDTH = 5; 	// 너비
	public static final int HEIGHT = 15; 	// 높이

	private String owner;
	private String number;
	private boolean powerOn;
	
	// boolean의 getter는 is로 시작
	public void setPowerOn(boolean powerOn) {
		this.powerOn = powerOn;
	}
	public boolean isPowerOn() {
		return powerOn;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumber() {
		return number;
	}
	
	public void printInfo() {
		System.out.printf("%s님의 전화기 : %s%n", owner, number);
	}
	
	/**
	 * this -> other
	 * 
	 * other의 타입이 같은 IPhone13클래스이므로 private필드에도 직접 접근할 수 있다.
	 * @param other
	 */
	public void callTo(IPhone13 other) {
		System.out.printf(
				"%s(%s)님이 %s(%s)으로 전화를 겁니다...%n", 
				this.owner, this.number, 
				other.owner, other.number);
	}
	
}
