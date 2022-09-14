package kh.java.collections.list;

import java.util.Objects;

public class Student implements Comparable<Student> {
	
	private int no;
	private String name;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Student [no=" + no + ", name=" + name + "]";
	}

	/**
	 * 학생번호를 기준으로 정렬
	 * - 음수 : 자리교환
	 * - 0
	 * - 양수
	 */
	@Override
	public int compareTo(Student other) {
		return  this.no - other.no;
	}
	
	/**
	 * equals의 결과가 true라면, 동일한 hashCode를 리턴해야 한다.
	 * - 동일한 필드를 사용해서 두 메소드를 override해야한다.
	 */
	@Override
	public boolean equals(Object obj) {
		Student other = (Student) obj;
		if(this.no == other.no && this.name.equals(other.name))
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.no, this.name);
	}
	
}
