package kh.java.polymorphism.product;

public class Desktop extends Computer {

	private String monitor;
	private String keyboard;
	private String mouse;
	
	public Desktop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Desktop(String brand, String code, String name, int price,
			String os, String monitor, String keyboard, String mouse) {
//		this.brand = brand;
//		this.code = code;
//		this.name = name;
//		this.price = price;
		
		// 1.setter 이용해서 private필드에 값대입하기
//		setBrand(brand);
//		setCode(code);
//		setName(name);
//		setPrice(price);
		
		// 2.부모생성자를 호출 : 생성자에 딱한번, 맨위줄에서만 사용가능
		// this와 같이 사용불가. 생략해도 자동으로 super() 호출된다.
		super(brand, code, name, price, os);
		
		this.monitor = monitor;
		this.keyboard = keyboard;
		this.mouse = mouse;
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
	
	/**
	 * brand, code, name, price는 상속받았지만, private필드라 직접접근할 수 없다.
	 * public method를 통해 접근할 수 있다.
	 * @return
	 */
	public String getDesktopInfo() {
		return this.getComputerInfo() + ", " + this.monitor + ", " + this.keyboard + ", " + this.mouse;
	}
	
	@Override
	public String getProductInfo() {
		return "[" + super.getProductInfo() + ", " + this.monitor + ", " + this.keyboard + ", " + this.mouse + "]";
	}
	
	
}
