package kh.java.object.array.person;

import java.util.Scanner;

/**
 * 
 * Person객체를 Person[]로 관리하는 클래스
 * 
 * has a 포함관계(연관관계 Association)
 * - PersonManager ----실선----> Person
 * 
 */
public class PersonManager {
	private Scanner sc = new Scanner(System.in);
	private static final int LENGTH = 3;
	private Person[] persons;
	
	public PersonManager() {
		persons = new Person[LENGTH]; // PersonManager객체 생성시 필드 persons에 Person[] 할당됨.
	}
	
	public void inputPerson() {
		System.out.println(LENGTH + "명의 정보를 입력하세요...");
		
		for(int i = 0; i < persons.length; i++) {
			System.out.println("----- 인물 " + (i + 1) + " -----");
			Person p = new Person();
			System.out.print("이름 : ");
			String name = sc.next();
			p.setName(name);
			System.out.print("나이 : ");
			int age = sc.nextInt();
			p.setAge(age);
			persons[i] = p;
		}
		
		System.out.println("> 입력 완료!");
	}
	
	public void printPerson() {
		for(Person p : persons)
			System.out.println(p.getPersonInfo());
	}
}
