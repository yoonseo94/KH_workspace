package kh.java.inheritance.product.after;

public class SmartPhone extends Computer {

	private String carrier; // 통신사
	
	public SmartPhone() {
		super();
		// TODO Auto-generated constructor stub
	}

	// source - generate constructor using fields - super constructor to invoke
	public SmartPhone(String brand, String code, String name, int price, String os, String carrier) {
		super(brand, code, name, price, os);
		this.carrier = carrier;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	public String getSmartPhoneInfo() {
		return this.getComputerInfo() + ", " + carrier;
	}
	
	
	
}
