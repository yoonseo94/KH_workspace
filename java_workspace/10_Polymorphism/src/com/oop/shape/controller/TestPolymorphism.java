package com.oop.shape.controller;

import com.oop.shape.model.Circle;
import com.oop.shape.model.IShape;
import com.oop.shape.model.Rectangle;
import com.oop.shape.model.Triangle;

/**
 * 5개의 객체를 무작위로 생성함(Circle, Rectangle, Triangle)	
 * for loop 문으로 각 객체의 면적과 둘레를 출력 처리함
 *  단, Triangle 객체는 빗변길이도 출력 처리함	
 *  		 
 * @author shqkel1863
 *
 */
public class TestPolymorphism {

	public static void main(String[] args) {
		IShape[] iarr = new IShape[5];
		
		iarr[0] = new Rectangle(100, 350);
		iarr[1] = new Circle(50);
		iarr[2] = new Rectangle(70, 200);
		iarr[3] = new Triangle(100, 350);
		iarr[4] = new Triangle(50, 350);
		
		for(int i=0; i<iarr.length; i++){
			System.out.println(i+"----------------------");
			System.out.println("면적 : "+iarr[i].area());
			System.out.println("둘레 : "+iarr[i].perimeter());
			if(iarr[i] instanceof Triangle)
				System.out.println("빗변길이 : "+((Triangle)iarr[i]).getHypotenuse());
		}
		
	}

}
