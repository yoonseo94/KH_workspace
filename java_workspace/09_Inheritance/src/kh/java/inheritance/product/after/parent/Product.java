package kh.java.inheritance.product.after.parent;

/**
 * 
 *  Desktop, SmartPhone, Tv의 공통적인 속성/기능을 가지고 있는 부모클래스
 *
 */
public class Product {

	// protected 같은패키지에서 접근 가능. 다른 패키지여도 자식클래스는 접근 가능
	protected String brand;
	protected String code;
	protected String name;
	protected int price;
	
	public Product() {
		super();
	}

	public Product(String brand, String code, String name, int price) {
		super();
		this.brand = brand;
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductInfo() {
		return brand + ", " + code + ", " + name + ", " + price;
	}
	
	
}
