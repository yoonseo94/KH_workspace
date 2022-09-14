package kh.firstmini.manager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import kh.firstmini.vo.Cart;
import kh.firstmini.vo.Menu;
import kh.firstmini.vo.Store;

/**
 * 
 * @author ej_lee
 * 
 * 기능
 *  장바구니 출력, 메뉴제외, 주문량 수정, 주문검증 및 주문완료
 * 
 * 실행방법 
 *  1. 객체 생성
 *  	- 생성자 : CartManager(Cart c, Map<String, Store> storeMap)
 *  2. cartManagerStart() 메소드 호출
 *  	- 주문확정 이후 true 리턴
 *  	- 단순 뒤로가기 누르면 false 리턴
 *
 */

public class CartManager {

	private Cart myCart;
	private Scanner sc = new Scanner(System.in);
	private String inputLine = "";
	private Map<String, Store> storeMap; // 프로그램이 관리하는 전체 점포 목록

	// 생성자
	public CartManager(Cart c, Map<String, Store> storeMap) {
		super();
		myCart = c; // 프로그램 공통으로 쓰일 카트 객체
		this.storeMap = storeMap;
	}

	// CartManager기능들 실행하는 메소드
	public boolean cartManagerStart() {

		while (true) {
			// 현재 장바구니 상태 출력
			printCart();

			System.out.print("선택 > ");
			String cartMenuChoice = sc.nextLine();

			// 사용자 입력에 따른 분기
			switch (cartMenuChoice) {
			case "1":
				removeCartMenu(); // 메뉴제외
				break;
			case "2":
				modifyMenu(); // 주문수량정정
				break;
			case "3":
				if (minAndTimeCheck() == false)
					continue;
				orderCheck(); // 주문확정
				return true;
			case "4":
				return false; // 뒤로가기 =MenuManager로 복귀
			default:
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	// 현재 장바구니 출력
	public void printCart() {
		String topStr = "\n============================== 당신의 장바구니 ==============================\n";
		String underStr = "-------------------------------------------------------------------------\n"
				+ "1. 메뉴제외\t\t2.주문량 수정\t\t3.주문하기\t\t4.뒤로가기\n"
				+ "=========================================================================\n";

		System.out.print(topStr);

		if (isCartEmpty() != true) {
			// 메뉴와 수량 출력
			Set<Map.Entry<Menu, Integer>> menuEntrySet = myCart.getMenuCountMap().entrySet();
			for (Map.Entry<Menu, Integer> menuEntry : menuEntrySet) {
				Menu key = menuEntry.getKey();
				int count = (int) menuEntry.getValue();

				String storeName = "";
				Set<Map.Entry<String, Store>> storeEntrySet = storeMap.entrySet();
				for (Map.Entry<String, Store> storeEntry : storeEntrySet) {
					// 매장목록 안의 storeID와 카트 내 메뉴의 storeID가 일치하면
					if (storeEntry.getKey().equals(key.getStoreID())) {
						storeName = storeEntry.getValue().getStoreName();
					}
				}

				System.out.printf("[%s] %8s\t%d개\t%,d원\n", storeName, key.getMenuName(), count, key.getPrice() * count);
			}
			// 총액 출력
			DecimalFormat df = new DecimalFormat("#,###");
			System.out.println("합계금액(배달비제외): " + df.format(myCart.getTotalPrice())+"원");
		}
		System.out.print(underStr);
	}

	// 메뉴제외 기능
	public void removeCartMenu() {
		// 카트가 비어있으면
		if (isCartEmpty() == true)
			return;

		while (true) {
			System.out.print("[뒤로가기 : exit]\t제외할 메뉴 이름 > ");
			inputLine = sc.nextLine();

			if (inputLine.equals("exit"))
				return;

			// 사용자가 입력한 메뉴가 카트 내에 있을 경우
			if (removeMenu(inputLine) != false) {
				return;
			} else {
				// 사용자가 입력한 메뉴가 없을 경우
				System.out.println("메뉴를 찾을 수 없습니다. 다시 입력하세요.");
				continue;
			}
		}
	}

	// 선택메뉴 삭제 : 삭제 성공하면 t리턴, 아니면 f리턴
	// - 조건식 searchMenu(String) 으로 바꿔야함
	public boolean removeMenu(String removeName) {

		for (Menu m : myCart.getCartList()) {
			// 제외할 메뉴를 찾으면 카트목록에서 제거 && true리턴
			if ((m.getMenuName().equals(removeName)) != false) {
				myCart.getCartList().remove(m);
				myCart.modifyMenuCountMap(removeName, 0);
				System.out.println("장바구니에서 제외 되었습니다.");
				return true;
			}
		}
		// 제외할 메뉴가 카트에 없으면 false
		return false;
	}

	// 메뉴수량(주문량) 수정
	public void modifyMenu() {

		// 장바구니에 아무것도 없으면 탈출
		if (isCartEmpty() == true)
			return;

		while (true) {
			System.out.print("[뒤로가기 : exit]\t수정할 메뉴 이름 > ");
			inputLine = sc.nextLine();

			if (inputLine.equals("exit"))
				return;

			modifyCount(inputLine);
			return;
		}

	}

	// 선택메뉴 수량 정정 : 성공하면 t리턴, 아니면 f리턴
	// - 조건식 searchMenu(String) 으로 바꿔야함
	public void modifyCount(String updateName) {
		int afterCount = 0;

		// 양수만 입력받기
		while (true) {
			System.out.print("몇 개로 정정하시겠습니까?(양수입력) > ");
			String tmp = sc.nextLine();
			try {
				afterCount = Integer.parseInt(tmp);
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력하세요.");
			}
			if (afterCount > 0)
				break;
			else
				System.out.println("정정값이 0보다 작습니다.");
		}

		myCart.modifyMenuCountMap(updateName, afterCount);
		return;
	}

	// 주문확정
	// 주문내역(메뉴금액+배달팁+총액) 보여주고 주문
	public void orderCheck() {
		if (isCartEmpty() == true)
			return;

		System.out.println("---------------------------------------------");
		System.out.println("(배달안내) 고객님의 주문이 접수되었습니다.\n");

		SimpleDateFormat sdf = new SimpleDateFormat("M월 d일 a h:mm");
		String today = sdf.format(new GregorianCalendar().getTime());
		System.out.println("- 주문일시: " + today);

		DecimalFormat df = new DecimalFormat("#,###");
		System.out.println("- 상품금액: " + df.format(myCart.getTotalPrice()) + "원");

		// 배달팁
		System.out.println("- 배달팁: ");
		Set<String> cartIdSet = new HashSet<>();
		// 카트에 있는 메뉴들의 storeID로 Set 만들기
		for (Menu m : myCart.getCartList()) {
			cartIdSet.add(m.getStoreID());
		}

		int totalRiderTip = 0;
		for (String s : cartIdSet) {
			Store store = storeMap.get(s);
			System.out.printf("  %s %,d원%n", store.getStoreName(), store.getRiderTip());
			totalRiderTip += store.getRiderTip();
		}

		int lastPrice = myCart.getTotalPrice() + totalRiderTip;
		System.out.println("- 총 결제금액: " + df.format(lastPrice) + "원");
		System.out.println("---------------------------------------------\n");

		// 장바구니 초기화
		myCart.resetCart();
	}

	// 최소주문금액&시간요건 불충족 시 false 리턴하도록 
	public boolean minAndTimeCheck() {

		Map<String, Integer> cartStoreMap = new HashMap<>(); // <storeId, 점포별금액합>

		Set<Map.Entry<Menu, Integer>> entrySet = myCart.getMenuCountMap().entrySet();
		for (Map.Entry<Menu, Integer> entry : entrySet) {
			String menuStoreId = entry.getKey().getStoreID();
			int menuPrice = entry.getKey().getPrice();
			int count = entry.getValue();

			if (cartStoreMap.containsKey(menuStoreId)) {
				int beforePrice = cartStoreMap.get(menuStoreId);
				int afterPrice = beforePrice + (menuPrice * count);
				cartStoreMap.put(menuStoreId, afterPrice);
			} else {
				cartStoreMap.put(menuStoreId, menuPrice * count);
			}
		}

		// 요건 검사 기준
		int minOrderPrice = 0;
		LocalTime openTime = LocalTime.of(0, 0);
		LocalTime closeTime = LocalTime.of(23, 59);

		// 요건 검사
		Set<Map.Entry<String, Store>> entrySet2 = storeMap.entrySet(); // 점포목록
		for (Map.Entry<String, Store> entry : entrySet2) {
			String key = entry.getKey(); // storeID
			Store value = entry.getValue();

			if (cartStoreMap.containsKey(key)) {
				// 검사 기준을 해당 점포의 정보로 변경
				minOrderPrice = value.getMinOrderPrice();
				openTime = value.getOpenTime();
				closeTime = value.getCloseTime();

				// 최소주문금액 충족검사
				int storePrice = cartStoreMap.get(key);
				if (storePrice < minOrderPrice) {
					System.out.printf("[%s]의 최소주문 금액은: %,d, 현재 주문금액은: %,d 입니다.%n", value.getStoreName(), minOrderPrice,
							storePrice);
					return false;
				}

				// 영업시간 충족검사
				LocalTime now = LocalTime.now();
				// 분단위 차이값
				long openDiff = Duration.between(openTime, now).getSeconds() / 60;
				long closeDiff = Duration.between(now, closeTime).getSeconds() / 60;
				if (openDiff < 0 || closeDiff < 0) {
					System.out.printf("[%s]의 영업시간이 아닙니다%n", value.getStoreName());
					return false;
				}
			}
		}
		return true;
	}
	
	// 장바구니 비어있으면 출력 & t리턴
	// 장바구니에 뭔가 있으면 f리턴
	public boolean isCartEmpty() {
		if (myCart.getCartList().size() <= 0) {
			System.out.println("장바구니가 비어있습니다.");
			return true;
		} else
			return false;
	}

}
