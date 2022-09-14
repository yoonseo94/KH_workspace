package com.oop.shape.controller;

import com.oop.shape.model.Circle;
import com.oop.shape.model.IShape;
import com.oop.shape.model.Rectangle;

public class TestIShape {

	public static void main(String[] args) {
		//인터페이스로 자식객체 담기
		IShape s;
		s = new Circle(15.5);
		System.out.println("원면적 : " + s.area());
		System.out.println("원둘레 : " + s.perimeter());

		s = new Rectangle(34.5, 42.7);
		System.out.println("사각형면적 : " + s.area());
		System.out.println("사각형둘레 : " + s.perimeter());
	}

}
