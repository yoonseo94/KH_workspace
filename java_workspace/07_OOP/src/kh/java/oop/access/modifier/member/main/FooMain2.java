package kh.java.oop.access.modifier.member.main;

import kh.java.oop.access.modifier.member.Foo;

public class FooMain2 {

	public static void main(String[] args) {
		Foo foo = new Foo();
		foo.a = 100;
//		foo.b = 200; protected 필드는 다른 패키지에서 접근불가하다.
//		foo.c = 300; default 필드는 다른 패키지에서 접근불가하다.
//		foo.d = 400; private 필드는 다른 클래스에서 접근불가하다.
	}
}
