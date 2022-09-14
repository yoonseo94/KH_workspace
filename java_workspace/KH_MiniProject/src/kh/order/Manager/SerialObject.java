package kh.order.Manager;

import java.io.Serializable;
import java.util.ArrayList;

import kh.order.vo.Order;

public class SerialObject implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList addOrder;
	Order order;
	public SerialObject() {
		
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}

	public ArrayList getList() {
		  return addOrder;
		 }
	public void setList(ArrayList list) {
		this.addOrder = list;
	}
	

}