package kh.firstmini.manager;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kh.firstmini.vo.Cart;
import kh.firstmini.vo.Menu;
import kh.firstmini.vo.Store;

public class MenuManager {
    
	private Scanner sc = new Scanner(System.in);
	private String choice;
	private Cart myCart;
	private List<Menu> list = new ArrayList<>();
	private Map<String, Store> storeMap; // 프로그램이 관리하는 전체 매장
	private String storeID;

	// 생성자
	public MenuManager(String ID, Cart cart, Map<String, Store> storeMap) {
		addMenu();
		storeID = ID;
		this.storeMap = storeMap;
		this.myCart = cart;
		this.storeMap = storeMap;
	}	
	
	public void addMenu() {

		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("allMenu.ser")))) {
			list = (List<Menu>) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 매장의 모든 메뉴 출력
	public void allMenuPrint() {
		int i = 1;
		for (Menu m : list) {
			if (storeID.equals(m.getStoreID()))
				System.out.printf("%d. %s  --------------  %,d원%n", i++, m.getMenuName(),m.getPrice());
		}
		i = 1;
	}

	// cart의 addCartList로 추가
	public void addCart(String name) {

		// 사용자가 입력한 이름이 현재 선택 점포의 메뉴인지 검사
		for (Menu m : list) {
			if (m.getStoreID().equals(storeID) && m.getMenuName().equals(name)) {
				myCart.addCartList(m);
				System.out.printf("장바구니에 [%s]를 담았습니다.%n", choice);
				return;
			}
		}
		System.out.println("선택한 점포의 메뉴가 아닙니다.");
	}

	public boolean menuChoice() {
		while(true) {			
			String str = "----------------------------------------------------\n";		   
			
			System.out.println("\n----------------------- menu -----------------------");
			allMenuPrint();
			System.out.println(str);
			
			System.out.print("[뒤로가기 : exit, 주문완료 : ok] 메뉴이름 입력 > ");
			choice = sc.nextLine();
	
			 if(choice.equals("ok")) {
	                CartManager cm = new CartManager(myCart, storeMap);
	                if(cm.cartManagerStart()==true) {
	                    return true;
	                } else {
	                    continue;
	                }
	         }
			else if(choice.equals("exit")) {
				System.out.println("처음으로 돌아갑니다.");
				return false;
			}
			else{
				addCart(choice);
			}
			
		}	
	}
	
 }