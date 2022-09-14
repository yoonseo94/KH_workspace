package com.oop.person.model;

public class Person {	
	private String name;		//이름
	private int age;			//나이
	private double height;	//키
	private double weight;	//몸무게
	private long wealth;		//재산
	
	public Person(){}
	public Person(String name, int age, double height, double weight, long wealth) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.wealth = wealth;
	}
	
	public String information(){
		return name+", "+age+", "+height+", "+weight+", "+wealth;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public long getWealth() {
		return wealth;
	}
	public void setWealth(long wealth) {
		this.wealth = wealth;
	}
	
	
	
	
	
}
