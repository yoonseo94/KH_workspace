package kh.java.oop.field;

public class KHStudentMain {

	public static void main(String[] args) {
		// kh정보교육원 학생 2명 생성후 각각 출력
		KHStudent stdt1 = new KHStudent();
		// 값설정
		stdt1.setName("홍길동");
		stdt1.setPhone("01012341234");
		stdt1.printKHStudent();
		
		KHStudent stdt2 = new KHStudent();
		// 값설정
		stdt2.setName("신사임당");
		stdt2.setPhone("01067896789");		
		stdt2.printKHStudent();
	}

}
