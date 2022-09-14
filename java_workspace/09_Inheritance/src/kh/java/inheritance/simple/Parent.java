package kh.java.inheritance.simple;

public class Parent extends Object {
	
	String name;
	int age;
	
	public void say() {
		System.out.println("내가 애비다");
	}
	
	public String info() {
		return "name = " + name + ", age = " + age;
	}
	
}
