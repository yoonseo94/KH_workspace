package com.oop.coffee.model.dto;

public class Coffee {
	private String origin;
	private String coffeeName;
	
	
	
	/**
	 * 
	 */
	public Coffee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coffee(String origin, String coffeeName) {
		super();
		this.origin = origin;
		this.coffeeName = coffeeName;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCoffeeName() {
		return coffeeName;
	}

	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}
	
	public String information() {
		return "[origin=" + origin + ", coffeeName=" + coffeeName + "]";
	}

	
	
}
