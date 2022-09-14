package member.model.vo;

public abstract class Member implements Buyable {
	/*protected*/
	private String name;
	private String grade;
	private int point;
	
	public Member(){}
	
	public Member(String name, String grade, int point) {
		this.name = name;
		this.grade = grade;
		this.point = point;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	// 추상메소드를 통한 메소드 구현을 강제화
	public abstract double getEjapoint();
	
	
}
