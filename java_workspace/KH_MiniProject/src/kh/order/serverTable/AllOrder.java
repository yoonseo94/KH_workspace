package kh.order.serverTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kh.order.vo.Order;

public class AllOrder {
	// Icloud 같은 공유 객체
	private ArrayList<Order> addList = new ArrayList<Order>();		// 추가 주문
	private ArrayList<Order> allList = new ArrayList<Order>();		//최종 전체 주문
	
	Map<Integer, ArrayList> addOrderMap = new HashMap<>();
	Map<Integer, ArrayList> allOrderMap = new HashMap<>();
	public AllOrder() {
		
	}
	
	// 보미님 - 1. 추가 주문이 들어오면 => 전체 주문에 추가해준다. (갱신)
	public void AddToAll() {
		allList.addAll(addList);
	}
	
	// 서영님 - 2. 추가 주문을 비워줘야겠죠???
	public void clearAddList() {
		addList.clear();
	}
	
	// 서영님 - addMap에 추가
	public void addOrder(int table) {
		addOrderMap.put(table, this.addList);
	}
	// 서영님 - allMap에 추가
	public void allOrder(int table) {
		allOrderMap.put(table, this.allList);
	}
	
	
	public ArrayList<Order> getAddList() {
		return addList;
	}
	public void setAddList(ArrayList<Order> addList) {
		this.addList = addList;
	}
	public ArrayList<Order> getAllList() {
		return allList;
	}
	public void setAllList(ArrayList<Order> allList) {
		this.allList = allList;
	}
	
	public Map<Integer, ArrayList> getAddOrderMap() {
		return addOrderMap;
	}
	public void setAddOrderMap(Map<Integer, ArrayList> addOrderMap) {
		this.addOrderMap = addOrderMap;
	}
	public Map<Integer, ArrayList> getAllOrderMap() {
		return allOrderMap;
	}
	public void setAllOrderMap(Map<Integer, ArrayList> allOrderMap) {
		this.allOrderMap = allOrderMap;
	}
	

	
}
