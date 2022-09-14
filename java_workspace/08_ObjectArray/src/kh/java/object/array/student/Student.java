package kh.java.object.array.student;

/**
 * VO
 * 
 * - 학생번호
 * - 학생이름
 * - 자바점수
 *
 */
public class Student {
	private int no;
	private String name;
	private int javaScore;
	
	public Student() {}
	
	public Student(int no, String name, int javaScore) {
		this.no = no;
		this.name = name;
		this.javaScore = javaScore;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setJavaScore(int javaScore) {
		this.javaScore = javaScore;
	}
	public int getJavaScore() {
		return javaScore;
	}
	
	public String getStudentInfo() {
		return "Student{no = " + no + ", name = " + name + ", javaScore = " + javaScore + "}";
	}

}
