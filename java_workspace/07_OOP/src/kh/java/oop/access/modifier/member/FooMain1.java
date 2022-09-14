package kh.java.oop.access.modifier.member;

public class FooMain1 {

	public static void main(String[] args) {
		Foo foo = new Foo();
		foo.a = 100;
		foo.b = 200;
		foo.c = 300;
//		foo.d = 400; private 필드는 다른 클래스에서 접근불가하다.
	}
}
