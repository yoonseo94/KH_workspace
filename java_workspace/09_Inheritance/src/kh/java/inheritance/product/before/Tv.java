package kh.java.inheritance.product.before;

public class Tv {
	
	private String brand;
	private String code;
	private String name;
	private int price;
	
	private String resolution; 	// 해상도 FHD UHD
	private int size; 			// 인치 정보
	
	public Tv() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tv(String brand, String code, String name, int price, String resolution, int size) {
		super();
		this.brand = brand;
		this.code = code;
		this.name = name;
		this.price = price;
		this.resolution = resolution;
		this.size = size;
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
		return brand + ", " + code + ", " + name + ", " + price + "," + resolution + ", " + size;
	}
}
