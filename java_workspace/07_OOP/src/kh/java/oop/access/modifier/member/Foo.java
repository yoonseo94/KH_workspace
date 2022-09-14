package kh.java.oop.access.modifier.member;

/**
 * member level 접근제한자
 *  - field, method 통칭
 *  
 *  1. public : 모든 패키지에서 접근 가능
 *  2. protected : 같은 패키지에서 접근 가능, 다른 패키지에서 상속관계는 접근 가능
 *  3. default	: 같은 패키지에서만 접근 가능
 *  4. private : 현재 클래스에서 접근이 가능
 *
 */
public class Foo {

	public int a;
	protected int b;
	int c; // default키워드 작성안함.
	private int d;
	
}
