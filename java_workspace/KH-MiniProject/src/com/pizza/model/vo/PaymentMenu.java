package com.pizza.model.vo;

public class PaymentMenu {
	private String orderWay;

	public PaymentMenu() {
		
	}
	public PaymentMenu(String orderWay) {
		this.orderWay = orderWay;
	}

	public String getOrderWay() {
		return orderWay;
	}
	public void setOrderWay(String orderWay) {
		this.orderWay = orderWay;
	}

	@Override
	public String toString() {
		return "주문 방법 : " + orderWay;
	}
}
