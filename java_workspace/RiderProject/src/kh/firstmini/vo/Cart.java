package kh.firstmini.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cart {
	private int totalPrice;
	private Map<Menu, Integer> menuCountMap = new HashMap<>(); // 메뉴 별 수량 저장
	private List<Menu> cartList = new ArrayList<>(menuCountMap.keySet());
	
	// 장바구니 초기화
	public void resetCart() {
		setTotalPrice(0);
		menuCountMap.clear();
		cartList.clear();
	}

	// 수량정정
	public void modifyMenuCountMap(String menuName, int afterNum) {
		Set<Map.Entry<Menu, Integer>> menuEntrySet = menuCountMap.entrySet();
		for (Map.Entry<Menu, Integer> menuEntry : menuEntrySet) {
			// 이미 카트 안에 담겨있는 메뉴만
			Menu menu = menuEntry.getKey();
			if (menu.getMenuName().equals(menuName)) {
				int beforeNum = menuCountMap.get(menu);
				int diff = afterNum - beforeNum;

				if (afterNum == 0) {
					menuCountMap.remove(menu);
				} else {
					menuCountMap.put(menu, afterNum);
				}
				cartList = new ArrayList<>(menuCountMap.keySet());
				totalPrice += diff * menu.getPrice();
				return;
			}
		}
		System.out.println("장바구니에 없는 메뉴입니다.");
	}

	public void addCartList(Menu m) {
		for (Menu menu : cartList) {
			// 이미 카트 안에 담겨있는 메뉴
			if (menu.getMenuName().equals(m.getMenuName())) {
				int afterCount = menuCountMap.get(menu) + 1;
				menuCountMap.put(menu, afterCount);
				totalPrice += menu.getPrice();
				return;
			}
		}

		// 카트 안에 없는 메뉴
		cartList.add(m); // 카트 안에 추가
		menuCountMap.put(m, 1); // 수량 추가
		totalPrice += m.getPrice();

	}

	public Map<Menu, Integer> getMenuCountMap() {
		return menuCountMap;
	}

	public void setMenuCountMap(Map<Menu, Integer> menuCountMap) {
		this.menuCountMap = menuCountMap;
	}

	public List<Menu> getCartList() {
		return cartList;
	}

	public void setCartList(List<Menu> cartList) {
		this.cartList = cartList;
	}

	public Cart() {
		super();
		totalPrice = 0;
		menuCountMap = new HashMap<>();
		cartList = new ArrayList<>(menuCountMap.keySet());
		// TODO Auto-generated constructor stub
	}

	public Cart(int totalPrice) {
		super();
		this.totalPrice = totalPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Cart [totalPrice=" + totalPrice + "]";
	}

}