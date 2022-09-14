package com.oop.person.run;

import com.oop.person.cotroller.PersonController;

/**
 * 
 * <문제 1>
 *  - 클래스 : com.oop.person.model.Person.java
 *  - 테스트용 클래스 작성 : com.oop.person.controller.TestPersonArray.java
 * 		=> main() 메소드 포함함
 *  - Person 멤버변수 이름, 나이, 키, 몸무게, 재산
 * 	  기본생성자, 모든필드초기화생성자
 * 	  getter, setter
 * 	  출력메소드 information()
 * 
 *  - main() 에서 구현할 내용
 * 	1. Person 클래스에 대한 객체 배열 5개 선언함
 * 	2. 키보드로 5 사람의 정보를 입력받아, 각 객체에 기록함
 * 	3. 출력 확인함
 * 	4. 5명의 나이, 키, 몸무게, 재산의 평균을 구하여 각각 출력함
 * 
 * @author shqkel1863
 *
 */
public class Run {

	public static void main(String[] args) {
		PersonController pc = new PersonController();
		
		pc.insertPerson();
		
		pc.printPerson();
		
		pc.getPersonAvg();
	}
}
