package com.kh.function;

import java.util.Scanner;

public class Example {
	

	/*
		   기능제공 클래스
	      패키지 : kh.java.operator.function
	      클래스명 : Example
	      메소드 :  public void opSample1() { }              
	      내용 : 
	      	국어, 영어, 수학, 총점, 평균을 변수 선언, 
	      	각 점수를 키보드로 입력 받고 합계와 평균을 계산하고 
	      	세과목의 점수와 평균을 가지고 합격을 처리
	
	      조건 : 각 점수가 40이상이면서 
	      	평균이 60점 이상이면 합격, 나머지는 다 불합격
	
	 */
	public void opSample1(){
		int kor, eng, math, total;
		double avg;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("> 국어점수를 입력하세요 : ");
		kor = sc.nextInt();
		System.out.print("> 영어점수를 입력하세요 : ");
		eng = sc.nextInt();
		System.out.print("> 수학점수를 입력하세요 : ");
		math = sc.nextInt();
		
		//합계 
		total = kor + eng + math;
		
		//평균
		avg = total / 3.0;
		
		// 합격/불합격
		// Pseudo code (의사 코드)
		//(각점수가 40점이상) && (평균이 60점이상)
		//((kor >= 40 && eng >= 40) && math >= 40) && (avg >= 60) 
		String result = 
				kor>=40 && eng>=40 && math>=40 && avg>=60 ?
						"합격" :
							"불합격";
		
		//출력
		System.out.println("---------------------");
		System.out.println(result+"입니다.");
		
	}

	
	/*
	 	기능제공 클래스
      패키지 : kh.java.operator.function
      클래스명 : Example
      메소드 :  public void opSample2() { }              
      내용 : 학생이름, 학년, 반, 번호, 성별(문자, M / 
                F), 성적(실수), 입력 받고 아래와 같이 출
               력
	출력 예) 
	  3학년 2반 24번 남학생 홍길동은 성적이 99.55이다.
	  
	    조건 : 성별이 M이면 남학생, F면 여학생출력
	              성적은 소수점 2자리까지 출력

	 */
	public void opSample2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("> 학생이름을 입력하세요 : ");
		String name = sc.next();
		System.out.print("> 학년을 입력하세요 : ");
		int grade = sc.nextInt();
		System.out.print("> 반을 입력하세요 : ");
		int cl = sc.nextInt();
		System.out.print("> 번호를 입력하세요 : ");
		int stdtNum = sc.nextInt();
		System.out.print("> 성별을 입력하세요[M/F] : ");
//		String temp = sc.next();
//		char gender = temp.charAt(0);
		char gender = sc.next().charAt(0);
		String strGender = gender == 'M' ? "남" : "여";
		System.out.print("> 성적를 입력하세요 : ");
		double stdtMark = sc.nextDouble();
		
		//출력
		System.out.printf(
				"%d학년 %d반 %d번 %s %s은/는 성적이 %.2f이다.", 
				grade, cl, stdtNum, strGender, name, stdtMark);
		
	}


	/*
		기능제공 클래스
      패키지 : kh.java.operator.function
      클래스명 : Example
      메소드 :  public void opSample3() { }              
      내용 : 정수 하나입력 받고 양수면 “양수다” 	아니면 “양수가 아니다”, 0이면 “0이다＂출력


	 */
	public void opSample3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("> 정수를 입력하세요 : ");
		int num = sc.nextInt();
		
		String result = num > 0 ? "양수다" : (num == 0 ? "0이다" : "음수다.");
		System.out.println(result);
	}


	/*
	 	기능제공 클래스
      패키지 : kh.java.operator.function
      클래스명 : Example
      메소드 :  public void opSample4() { }              
      내용 : 정수 하나입력 받고 짝수면 짝수다 홀수면 
               홀수다 출력

 * 힌트 :  짝수 홀수 구분은 %연산자 이용하면 가능

	 */
	public void opSample4() {
		Scanner sc = new Scanner(System.in);
		System.out.print("> 정수를 입력하세요 : ");
		int num = sc.nextInt();
		
		String result = num % 2 == 0 ? "짝수다" : "홀수다";
		System.out.println(result);
		
	}
	
	
}
