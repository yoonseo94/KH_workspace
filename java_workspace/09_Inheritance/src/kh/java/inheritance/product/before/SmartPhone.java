package kh.java.inheritance.product.before;

public class SmartPhone {

	private String brand;
	private String code;
	private String name;
	private int price;
	
	private String os;
	
	private String carrier; // 통신사

	public SmartPhone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SmartPhone(String brand, String code, String name, int price, String os, String carrier) {
		super();
		this.brand = brand;
		this.code = code;
		this.name = name;
		this.price = price;
		this.os = os;
		this.carrier = carrier;
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	public String getSmartPhoneInfo() {
		return brand + ", " + code + ", " + name + ", " + price + ", " + os + ", " + carrier;
	}
	
}
