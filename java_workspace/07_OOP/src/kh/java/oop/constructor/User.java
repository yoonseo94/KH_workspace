package kh.java.oop.constructor;

import java.util.Date;

/**
 * 생성자
 * - new 연산자와 함께 호출하는 메소드.
 * - 객체생성시, 필드값 초기화를 담당한다.
 * - 리턴타입 없고, 클래스명과 생성자명은 동일해야 한다.
 * - 기본생성자(파라미터 없음)와 파라미터생성자로 구분 (생성자 오버로딩을 지원)
 * 
 * - 어떠한 생성자도 만들지 않은 경우, JVM은 기본생성자를 자동생성해준다.
 * - 파라미터생성자를 하나라도 만들면, 기본생성자를 자동으로 생성해주지 않는다.
 * - 파라미터생성자 만들때, 기본생성자도 반드시 작성해둔다.
 * 
 * 기본생성자는 꼭 필요하다.
 * - 상속시에 자식클래스가 부모클래스의 기본생성자를 자동으로 호출
 * - jsp/springframework의 bean객체를 생성시에 기본생성자를 호출.
 * 
 *
 */
public class User {
	
	// 필드
	private String id;
	private String password;
	private String name;
	private Date enrollDate;
	
	// 기본 생성자
	public User() {
		System.out.println("User 생성자 호출!");
	}
	
	/**
	 * this() : 생성자 this
	 * - 다른 생성자를 호출해서 반복된 코드를 제거 가능
	 * - 생성자 코드 몸통 첫줄에 딱 한번만 사용가능
	 * 
	 * @param id
	 * @param password
	 */
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public User(String id, String password, String name) {
		this(id, password);
		this.name = name;
	}
	
	public User(String id, String password, String name, Date enrollDate) {
		this(id, password, name);
		this.enrollDate = enrollDate;
	}
	
	// 메소드
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	
	public String getUserInfo() {
		return "id = " + id + ", password = " + password 
			 + ", name = " + name + ", enrollDate = " + enrollDate;
	}
}
