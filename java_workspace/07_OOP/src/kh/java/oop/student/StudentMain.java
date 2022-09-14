package kh.java.oop.student;

public class StudentMain {

	public static void main(String[] args) {
		// 학생객체 1
		Student s1 = new Student();
		// 상태(속성) 값대입
		s1.setName("홍길동");
		s1.setGender('남');
		s1.setAge(16);
		// 행동(기능) 실행
		s1.introduce();
		System.out.println(s1.getName());
		
		// 학생객체 2
		Student s2 = new Student();
		s2.setName("신사임당");
		s2.setGender('여');
		s2.setAge(17);
		s2.introduce();
	}

}
