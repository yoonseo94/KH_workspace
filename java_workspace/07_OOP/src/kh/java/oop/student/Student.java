package kh.java.oop.student;

/**
 * 
 * n명의 학생객체를 위한 클래스 설계
 * - 상태 : 속성 -> 필드
 * - 행동 : 기능 -> 메소드
 *
 */
public class Student {

	// 속성
	private String name;
	private char gender;
	private int age;
	
	// 기능 (속성활용)
	/**
	 * this 
	 * - 모든 non-static 메소드에 존재하는 현재객체를 가리키는 숨은 참조변수
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return this.name;
	}
	public char getGender() {
		return this.gender;
	}
	public int getAge() {
		return this.age;
	}
	
	
	public void introduce() {
		System.out.printf("안녕하세요, [%s]입니다. 저는 [%c]자, [%d]살입니다. 반갑습니다.%n",
						  name,
						  gender,
						  age);
	}
	
}








