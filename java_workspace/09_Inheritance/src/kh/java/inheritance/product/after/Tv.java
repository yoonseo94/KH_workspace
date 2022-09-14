package kh.java.inheritance.product.after;

import kh.java.inheritance.product.after.parent.Product;

public class Tv extends Product {

	private String resolution; 
	private int size;
	
	public Tv() {
		super();
	}

	public Tv(String brand, String code, String name, int price, String resolution, int size) {
		// 1.Product필드가 private인 경우 
//		super(brand, code, name, price);
		
		// 2.Product필드가 protected인 경우 패키지가 달라도 자식클래스에서 직접 접근 할 수 있다.
		this.brand = brand;
		this.code = code;
		this.name = name;
		this.price = price;
		
		this.resolution = resolution;
		this.size = size;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String getTvInfo() {
		return getProductInfo() + ", " + resolution + ", " + size;
	}
	
	
	
	
}
