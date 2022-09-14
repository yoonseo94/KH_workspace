package kh.java.object.array.person;

/**
 * VO 데이터를 관리할 목적의 클래스
 * - Value Object
 * - DO Domain Object
 * - DTO Data Tranfer Object
 * - Entity DB Table과 상응하는 의미
 * - bean jsp/EJB
 * 
 *  VO
 *  - private 필드
 *  - 기본생성자
 *  - 모든 필드생성자
 *  - getter
 *  - setter
 */
public class Person {
	
	private String name;
	private int age;
	
	public Person() {
		
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	
	public String getPersonInfo() {
		return "Person[name = " + name + ", age = " + age + "]";
	}
	
	
}
