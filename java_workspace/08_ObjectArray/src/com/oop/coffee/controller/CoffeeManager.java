package com.oop.coffee.controller;

import java.util.Scanner;

import com.oop.coffee.model.dto.Coffee;

public class CoffeeManager {
	Scanner sc = new Scanner(System.in);
	Coffee[] top3;
	{
		top3 = new Coffee[3];
	}
	
	public void insertCoffeeData() {
		for(int i=0; i<top3.length; i++) {
			//Coffee객체 생성
			Coffee c = new Coffee();
			System.out.print("커피원산지 입력 : ");
			c.setOrigin(sc.next());
			System.out.print("커피명 입력 : ");
			c.setCoffeeName(sc.next());
			
			//객체배열에 추가
			top3[i] = c;
		}
	}
	
	public void printCoffeeData() {
		for(int i=0; i<top3.length; i++) {
			System.out.println(top3[i].information());
		}
	}
}
