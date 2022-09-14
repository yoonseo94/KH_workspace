package kh.java.inheritance.simple;

/**
 * 
 * Parent를 상속한다.
 * - Child가 Parent를 확장한다.
 * - Child extends Parent 
 * 
 * Parent의 멤버(필드, 메소드)를 선언없이 Child에서 상속받아 사용할 수 있다.
 * 
 * 
 * 특징
 * 1. 부모클래스의 필드, 메소드를 선언없이 사용할 수 있다.
 * 2. 하나의 부모클래스만 상속할 수 있다. 단일상속
 * 3. 모든 클래스는 Object클래스의 후손클래스
 * 	- 모든 클래스는 Object의 메소드를 사용 또는 재작성할 수 있다.
 * 	- equals, hashCode, toString...
 * 4. 부모클래스 생성자, 초기화블럭은 상속되지 않는다.
 * 	- 자식클래스 생성자를 별도로 작성해야 한다.
 * 5. 부모클래스의 private필드는 상속받았다해도 직접 접근할 수 없다. 
 *  - public메소드
 *  - super() 부모생성자호출시 값전달
 * 
 */
public class Child extends Parent {
	
	public void game() {
		System.out.println("자식이 게임을 한다....");
	}
	
	/**
	 * 부모메소드 재작성  Override
	 */
	public void say() {
		System.out.println("제가 자식입니다.");
	}
	
}
