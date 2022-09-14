package com.oop.field;

public class Test {
	int a = 100;
	static int s = 99;

	public static void main(String[] args) {
		Test t = new Test();
//		t.test1();

//		t.test2();
//
//		Test t3 = new Test();
//		t.test3(t3);
//		System.out.println(t3.a);
//
		t.test4(t);

	}
	
	public void test4(Test t) {
		t.a = 1000;
		System.out.println(this.a);
	}


	public void test3(Test t) {
		t.a = 10;
		this.a = 200;
		System.out.println(t.a);
	}
	
	public void test2() {
		Test t = new Test();
		t.a = 1000;
		System.out.println(a); // 100
	}

	public void test1() {
		int a = 1000;
		int s = 999;
		System.out.println(this.a); 
		System.out.println(Test.s);
	}



}
