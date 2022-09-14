package kh.java.mansuk_pc_cafe.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 이용권 VO 클래스
 * 
 * @author 지은
 */
public class FlatBill implements Comparable<FlatBill> {

	private int productNum;		// 상품 번호
	private int productName;	// 상품 이름
	private int productPrice;	// 가격
	
	private List<FlatBill> flatBillList = new ArrayList<>(); 
	
	public FlatBill() {}

	public FlatBill(int productNum, int productName, int productPrice) {
		this.productNum = productNum;
		this.productName = productName;
		this.productPrice = productPrice;
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

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	public List<FlatBill> getFlatBillList() {
		return flatBillList;
	}

	public void setFlatBillList(List<FlatBill> flatBillList) {
		this.flatBillList = flatBillList;
	}

	public void flatBillList() {
	flatBillList.add(new FlatBill(1, 1, 1000));
	flatBillList.add(new FlatBill(2, 4, 3800));
	flatBillList.add(new FlatBill(3, 10, 9000));
	flatBillList.add(new FlatBill(4, 50, 43000));
	flatBillList.add(new FlatBill(5, 100, 80000));
	
//	Collections.sort(flatBillList);

	}
	
	@Override
	public String toString() {
		return "FlatBill [productNum=" + productNum + ", productName=" + productName + ", productPrice=" + productPrice
				+ "]";
	}

	@Override
	public int compareTo(FlatBill o) {
		return this.productNum - o.productNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flatBillList, productName, productNum, productPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlatBill other = (FlatBill) obj;
		return Objects.equals(flatBillList, other.flatBillList) && Objects.equals(productName, other.productName)
				&& productNum == other.productNum && productPrice == other.productPrice;
	}
	
}