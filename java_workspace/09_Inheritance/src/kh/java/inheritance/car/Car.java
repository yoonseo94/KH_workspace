package kh.java.inheritance.car;

import java.util.Objects;

public class Car {

	private String brand;
	private String name;
	private int doorNum;
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Car(String brand, String name, int doorNum) {
		super();
		this.brand = brand;
		this.name = name;
		this.doorNum = doorNum;
	}
	
	/**
	 * 복사생성자
	 * 
	 */
	public Car(Car src) {
		this.brand = src.brand;
		this.name = src.name;
		this.doorNum = src.doorNum;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDoorNum() {
		return doorNum;
	}

	public void setDoorNum(int doorNum) {
		this.doorNum = doorNum;
	}
	
	@Override
	public String toString() {
		return "Car[" + brand + ", "+ name + ", " + doorNum + "]";
	}
	
	/**
	 * 객체 동등성 비교 Override
	 * - 지정한 필드값이 동일하면 동등하니 true 리턴
	 * - 필드값이 하나라도 다르면 false 리턴
	 * 
	 * equals와 hashCode는 동시에 Override해야한다. 
	 * - equals결과가 true이면 동일한 hashCode를 가져야한다.
	 * - 두 메소드에서 동일한 컬럼을 사용해야 한다.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		Car other = (Car) obj; // 형변환
		if(this.brand.equals(other.brand) && this.name.equals(other.name))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		// 지정한 컬럼값을 가지고 hashCode재생성
		return Objects.hash(brand, name); 
	}
	
	/**
	 * Override
	 * - 메소드시그니쳐가 동일해야 한다. (메소드명, 매개변수타입/개수/순서, 리턴타입) -> @Override
	 * - 접근제한자는 부모메소드보다 넓은 범위로 변경 가능 : protected메소드를 public메소드로 재작성가능
	 * - private, final메소드는 오버라이드가 불가하다.
	 * - 부모메소드가 던지는 예외를 제거하거나 한정할 수 있다.
	 * 
	 * - 오버라이드한 부모메소드는 super키워드를 통해 호출할 수 있다.
	 * 
	 * 공변반환타입 Covariant Return Type
	 * - 부모메소드의 반환타입의 자식클래스로 변환이 가능
	 * - Object -> Car
	 */
	@Override
	public Car clone() {
		// 복사 생성자
		return new Car(this);
	}
	
	
}
