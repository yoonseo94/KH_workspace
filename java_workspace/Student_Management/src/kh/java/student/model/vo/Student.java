package kh.java.student.model.vo;

public class Student implements Comparable<Student>  {
	
	private String name;
	private int age;
	private String school;
	private int no;
	private String major;
	private String phone;
	private String address;
	
	public Student() {
		super();
	}
	
	public Student(String name, int age, String school, int no, String major, String phone, String address) {
		super();
		this.name = name;
		this.age = age;
		this.school = school;
		this.no = no;
		this.major = major;
		this.phone = phone;
		this.address = address;
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
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
    @Override
   public String toString() {
       return   name + "\t" + age + "\t" + school + "\t" + no + "\t" + major
               + "\t" + phone + "\t" + address ;
   }

	@Override
	public int compareTo(Student o) {
		return this.no - o.no; // 학번 기준 정렬
	}
	
	
}