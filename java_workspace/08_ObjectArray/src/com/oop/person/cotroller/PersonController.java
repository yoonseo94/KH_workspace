package com.oop.person.cotroller;

import java.util.Scanner;

import com.oop.person.model.Person;

public class PersonController {
	Person[] pArr = new Person[3];
	Scanner sc = new Scanner(System.in);
	
	/**
	 * 입력메소드
	 */
	public void insertPerson() {
		for (int i = 0; i < pArr.length; i++) {
			System.out.print("[사람"+(i+1)+"] 이름 : ");
			String name = sc.next();
			System.out.print("[사람"+(i+1)+"] 나이 : ");
			int age = sc.nextInt();
			System.out.print("[사람"+(i+1)+"] 키 : ");
			double height = sc.nextDouble();
			System.out.print("[사람"+(i+1)+"] 몸무게 : ");
			double weight = sc.nextDouble();
			System.out.print("[사람"+(i+1)+"] 재산 : ");
			long wealth = sc.nextLong();
			
			pArr[i] = new Person(name, age, height, weight, wealth);
			
		}
	}
	
	/**
	 * 출력메소드
	 */
	public void printPerson() {
		//출력
		for (int i = 0; i < pArr.length; i++) {
			String pInfo = pArr[i].information();
			System.out.println(pInfo);
		}
	}
	
	/**
	 * 평균구하는 메소드
	 * 모두 소수점이하 첫번째자리까지만 출력함.
	 */
	public void getPersonAvg() {
		int sumAge=0;
		double sumHeight=0, sumWeight=0;
		long sumWealth=0;
		
		double len = pArr.length*1.0;
		
		for(int i=0; i<pArr.length; i++) {
			sumAge += pArr[i].getAge();
			sumHeight += pArr[i].getHeight();
			sumWeight += pArr[i].getWeight();
			sumWealth += pArr[i].getWealth();		
		}
		
		//평균구하기
		System.out.printf(" [ 평균정보 ] 나이=%.1f, 키=%.1f, 몸무게=%.1f, 재산=%.1f", sumAge/len, sumHeight/len, sumWeight/len, sumWealth/len);
		
	}
}
