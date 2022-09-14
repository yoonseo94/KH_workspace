package kh.java.object.array.student;

import java.util.Scanner;

/**
 * 
 * Student[] 관리
 * 
 * has a 포함관계
 * - StudentManager클래스가 필드로 다른 클래스 Student를 참조하는 경우
 * - StudentManager has a Student.
 * 
 * UML상의 연관관계(Assciation)
 *  StudentManager -------(실선)--------> Student 
 */
public class StudentManager {
	
	private Scanner sc = new Scanner(System.in);
	private static final int LENGTH = 3;
	private Student[] studentArr = new Student[LENGTH];
	private int index = 0; // studentArr의 인덱스관리
	
	public void menu() {
		String menu = "--- 학생정보관리 ---\n"
					+ "1. 학생정보 입력\n"
					+ "2. 학생정보 출력\n"
					+ "0. 종료\n"
					+ "---------------\n"
					+ "> ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			switch(choice) {
			case "1" : 
				if(index == LENGTH) {
					System.out.println("모든 학생 정보를 입력했습니다.");
					break;
				}
				insertData(); 
				break;
			case "2" : printData(); break;
			case "0" : System.out.println("프로그램 종료!"); return;
			default: System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void insertData() {
		System.out.println("> 학생정보 입력하세요...");
		while(true) {
			System.out.println("> 학생 " + (index + 1));
			System.out.print("번호 : ");
			int no = sc.nextInt();
			System.out.print("이름 : ");
			String name = sc.next();
			System.out.print("자바점수 : ");
			int javaScore = sc.nextInt();
			studentArr[index++] = new Student(no, name, javaScore);
			
			if(index < LENGTH) {
				System.out.print("더 입력하시겠습니까?(y/n) : ");
				char yn = sc.next().charAt(0);
				if(yn == 'n')
					break;
			}
			else 
				break;
			
		}
		System.out.println("> 입력완료!");
	}
	
	public void printData() {
		for(int i = 0; i < index; i++) {
			Student s = studentArr[i];
			System.out.println(s.getStudentInfo());
		}
	}
	
	

}
