package kh.java.polymorphism.animal;

/**
 * 추상클래스
 * - 미완성클래스. 객체생성 할수 없다. -> new Animal() X
 * - 필드/메소드를 가질 수 있는 일반 클래스
 * - 0개이상 추상메소드를 가질수 있다.(단, 추상메소드는 반드시 추상클래스 안에 있어야 한다.)
 * - 규격제공. 자식클래스는 반드시 추상메소드를 재작성해야한다.  
 *
 * 추상메소드
 * - abstract키워드를 가진다.
 * - 메소드 몸통이 없다. 자식클래스의 재작성메소드에서 구현예정.
 * - 자식클래스간의 표준화된 규격을 제공하고 이를 활용할수 있게 한다.
 *
 *
 * - 추상클래스 Animal는 부모의 추상메소드Soundable#getSound를 구현하지 않아도 된다. 
 * - 해당추상메소드는 자식클래스 Lion Bear Tiger Eagle 가 반드시 구현해야 한다.
 * 
 */
public abstract class Animal implements Soundable {
	
//	public void say() {
//		System.out.println("안녕하세요, 저는 동물입니다.");
//	}
	

	
	/**
	 * 추상메소드 
	 * - abstract 키워드를 사용한다.
	 * - 메소드 몸통이 없다.
	 * - 추상클래스 안에서만 정의될 수 있다.
	 */
	public abstract void say();
	
	public abstract void attack();
	
}
