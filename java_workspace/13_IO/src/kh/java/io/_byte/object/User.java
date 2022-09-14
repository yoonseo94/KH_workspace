package kh.java.io._byte.object;

import java.io.Serializable;

/**
 * 객체입출력할 클래스는 반드시 Serializable인터페이스를 구현해야 한다.
 * - 참조형 필드인 경우 해당 클래스 또한 Serializable을 구현해야 한다.
 * - 사용자 클래스인 경우 주의할 것.
 * 
 */
public class User implements Serializable {

	/**
	 * 직렬화된 정보와 일치하는 클래스인지 비교하는 고유값
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private transient String password; // transient jvm이 관리하되 직렬화시에는 제외한다.
	private int point;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String id, String password, int point) {
		super();
		this.id = id;
		this.password = password;
		this.point = point;
	}

	/**
	 * 복사생성자
	 * @param other
	 */
	public User(User other) {
		this.id = other.id;
		this.password = other.password;
		this.point = other.point;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", point=" + point + "]";
	}
	
}
