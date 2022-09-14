package kh.java.mansuk_pc_cafe.vo;

import java.util.Objects;

/**
 * 스낵바 VO 클래스
 * 
 * @author 민서
 */
public class Snack {
	
	private int number;
	private String food; // 밥, 물, 라면, 감자, 핫도그, 햄버거, 칩(과자), 기타음식
	private String topping;
	private int price;
	
	public Snack() {
		super();
	}
	
	public Snack(int number, String food, String topping, int price) {
		super();
		this.number = number;
		this.food = food;
		this.topping = topping;
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[" + number + " : " + food + ", 토핑추가 : " + topping + ", 가격 : " + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(food, number, price, topping);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Snack other = (Snack) obj;
		return Objects.equals(food, other.food) && number == other.number && price == other.price
				&& Objects.equals(topping, other.topping);
	}

}
