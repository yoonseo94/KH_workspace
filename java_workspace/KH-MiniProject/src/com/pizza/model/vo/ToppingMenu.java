package com.pizza.model.vo;

public class ToppingMenu extends Menu {
	
	private String tNo; 			// 토핑 번호
	private String titleTooping; 	// 토핑 이름
	private int priceTooping; 		// 토핑 가격
	
	public ToppingMenu() {}
	
	public ToppingMenu(String tNo, String titleTooping, int priceTooping) {
		super();
		this.tNo = tNo;
		this.titleTooping = titleTooping;
		this.priceTooping = priceTooping;
	}
	
	public String gettNo() {
		return tNo;
	}
	public void settNo(String tNo) {
		this.tNo = tNo;
	}
	public String getTitleTooping() {
		return titleTooping;
	}
	public void setTitleTooping(String titleTooping) {
		this.titleTooping = titleTooping;
	}
	public int getPriceTooping() {
		return priceTooping;
	}
	public void setPriceTooping(int priceTooping) {
		this.priceTooping = priceTooping;
	}
	
	@Override
	public String toString() {
		return "토핑 : " + titleTooping + " / 토핑 가격 : " + priceTooping;
	}
}
