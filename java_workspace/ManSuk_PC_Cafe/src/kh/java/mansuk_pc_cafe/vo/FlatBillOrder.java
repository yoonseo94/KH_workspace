package kh.java.mansuk_pc_cafe.vo;

import java.util.Objects;

/**
 * 이용권 주문 VO 클래스
 * 
 * @author 지은
 */
public class FlatBillOrder implements Comparable<FlatBillOrder> {
	
	private int productNum;					// 이용권 번호
	private int productName;				// 이용권 이름
	private int price;						// 이용권 가격
	private int count;						// 이용권 개수
	private static int total;				// 총 결제 금액
	private static int additionalTime;		// 추가이용시간
	
	public FlatBillOrder() {}

	public FlatBillOrder(int productNum, int count) {
		FlatBill flatBill = new FlatBill();
		flatBill.flatBillList();
		
		flatBill = flatBill.getFlatBillList().get(productNum - 1);
		this.productNum = productNum;
		
		price = flatBill.getProductPrice();
		productName = flatBill.getProductName();
		
		this.count = count;
		
		FlatBillOrder.total += price * count;
		FlatBillOrder.additionalTime += count * productName;
	}
	
	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getProductName() {
		return productName;
	}

	public void setProductName(int productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static int getTotal() {
		return total;
	}

	public static void setTotal(int total) {
		FlatBillOrder.total = total;
	}
	
	public static int getAdditionalTime() {
		return additionalTime;
	}

	public static void setAdditionalTime(int additionalTime) {
		FlatBillOrder.additionalTime = additionalTime;
	}
	
	@Override
	public String toString() {
		return "[  " + productNum +"  |   "+productName + "시간 이용권\t|  " + count + "장\t]\n";
	}
	
	@Override
	public int compareTo(FlatBillOrder o) {
		return this.productNum - o.productNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count, price, productName, productNum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlatBillOrder other = (FlatBillOrder) obj;
		return count == other.count && price == other.price && Objects.equals(productName, other.productName)
				&& productNum == other.productNum;
	}

}
