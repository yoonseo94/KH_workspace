package com.oop.shape.model;

public class Circle /* extends Shape */ implements IShape {
	private double radius; // 반지름

	public Circle() {
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * 반지름 * 반지름 * PI
	 */
	@Override
	public double area() {
		return Math.PI * Math.pow(radius, 2);
	}

	/**
	 * 지름 * PI
	 */
	@Override
	public double perimeter() {
		return Math.PI * radius * 2;
	}
}
