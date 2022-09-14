package kh.java.oop.field;

/**
 * 
 * kh정보교육원 학생정보관리
 * 
 * - 교육원이름
 * - 학생이름
 * - 전화번호
 * 
 * 출력메소드 printKHStudent를 통해
 *  예) KH정보교육원 - 홍길동(01012341234)
 *
 */
public class KHStudent {
	
	public static final String ACADEMY_NAME = "KH정보교육원";
	
	private String name;
	private String phone;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}

	/**
	 * KH정보교육원 - 홍길동(01012341234)
	 */
	public void printKHStudent() {
		System.out.printf(
				"%s - %s(%s)%n", 
				ACADEMY_NAME,
				name,
				phone);
	}
}
