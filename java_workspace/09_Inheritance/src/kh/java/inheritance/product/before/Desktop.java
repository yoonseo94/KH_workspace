package kh.java.inheritance.product.before;

public class Desktop {
	
	private String brand;
	private String code;
	private String name;
	private int price;
	
	private String os;
	
	private String monitor;
	private String keyboard;
	private String mouse;
	
	// source - Generate constructor from super class
	public Desktop() {

	}

	// source - Generate constructor using fields
	public Desktop(String brand, String code, String name, int price, 
			String os, String monitor, String keyboard, String mouse) {
		this.brand = brand;
		this.code = code;
		this.name = name;
		this.price = price;
		this.os = os;
		this.monitor = monitor;
		this.keyboard = keyboard;
		this.mouse = mouse;
	}

	// source - generate getters/setters
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

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(String keyboard) {
		this.keyboard = keyboard;
	}

	public String getMouse() {
		return mouse;
	}

	public void setMouse(String mouse) {
		this.mouse = mouse;
	}
	
	// 필드정보 확인용
	public String getDesktopInfo() {
		return brand + ", " + code + ", " + name + ", " + price + ", " 
			 + os + ", " + monitor + ", " + keyboard + ", " + mouse;
	}
	
	
	
	
	
	
}
