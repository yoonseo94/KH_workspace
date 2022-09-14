package com.oop.shape.model;

public class Triangle implements IShape {
	private double base;		//밑변
	private double height;		//높이
	
	public Triangle(){}
	
	public Triangle(double base, double height) {
		super();
		this.base = base;
		this.height = height;
	}

	@Override
	public double area() {
		return (base*height)/2; //삼각형의 넓이 = (밑변 * 높이) /2
	}

	@Override
	public double perimeter() {
		return 0;
	}
	
	/**
	 * 빗변길이 구하기(직각삼각형이라 가정)
	 * 
	 * 빗변길이 = Math.sqrt(밑변*밑변+높이*높이); 
	 * 피타고라스의 정리 : a^2 + b^2 = c^2
	 * 
	 * @return
	 */
	public double getHypotenuse(){
		return Math.sqrt(base*base+height*height);
	}
	
	

}
