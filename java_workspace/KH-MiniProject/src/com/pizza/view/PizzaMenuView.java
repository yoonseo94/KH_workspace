package com.pizza.view;

import java.util.Scanner;

import com.pizza.controller.PizzaMenuManager;
import com.pizza.mail.SendMail;

public class PizzaMenuView {

	private PizzaMenuManager pmm = new PizzaMenuManager();
	Scanner sc = new Scanner(System.in);

	private String choiceSetMenu;
	private String choiceSingleMenu;
	private String choiceSideMenu;
	private String choiceDrinkMenu;
	private String choiceSauceMenu;

	private boolean bool = true;
	int priceTotal = 0;

	public void pizzaMenu() {
		String strPizzaMenu = "==========================================================\n"
				+ "                            메뉴                           \n"
				+ "==========================================================\n"
				+ "1. 메뉴 선택\n"
				+ "2. 주문\n"
				+ "0. 프로그램 종료\n"
				+ "==========================================================\n"
				+ ">> ";
		outer:
			while(bool) {
				System.out.print(strPizzaMenu);
				String selected = sc.next();

				switch(selected) {
				case "1": foodMenuList(); break; 
				case "2": orderMenu(); break;
				case "0": System.out.print("프로그램 종료 !"); break outer;
				default : System.out.println("잘못 입력하셨습니다.");
				}
			}
	}

	public void foodMenuList() {
		String strMenuList = "==========================================================\n"
				+ "                         메뉴 목록                           \n"
				+ "==========================================================\n"
				+ "1. 세트\n"
				+ "2. 단품\n"
				+ "3. 사이드\n"
				+ "4. 음료\n"
				+ "5. 소스\n"
				+ "6. 주문\n"
				+ "0. 이전 메뉴로 돌아가기\n"
				+ "==========================================================\n"
				+ ">> ";

			System.out.print(strMenuList);
			String menuSelected = sc.next();
			
			switch(menuSelected) {
			
			case "1": pmm.addSetMenu(setMenu()); break;
			case "2": pmm.addSigleMenu(singleMenu()); break;
			case "3": pmm.addSideMenu(sideMenu()); break;
			case "4": pmm.addDrinkMenu(drinkMenu()); break;
			case "5": pmm.addSauceMenu(sauceMenu()); break;
			case "6": orderMenu(); break;
			case "0": return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
	}


	public void orderMenu() {
		String strOrderMenu1 = "==========================================================\n"
				+ "                          장바구니                           \n"
				+ "==========================================================\n"
				+ "카테고리\t메뉴이름\t\t\t가격\t  \t";

		String strOrderMenu2 = "총 가격 : ";

		String strOrderMenu3 = "==========================================================\n";

		String strOrderMenu4 = "==========================================================\n"
				+ "1. 메뉴 추가\n"
//				+ "2. 메뉴 삭제\n"
				+ "2. 결제하기\n"
				+ "0. 이전 메뉴로 돌아가기\n"
				+ "==========================================================\n"
				+ ">> ";

		while(bool) {
			System.out.print(strOrderMenu1);
			System.out.print(strOrderMenu2);
			pmm.totalPirce();
			System.out.print(strOrderMenu3);
			pmm.sortCart();
			pmm.printCart();
			System.out.print(strOrderMenu4);
			String OrderSelected = sc.next();

			switch(OrderSelected) {
			case "1" : foodMenuList(); break;
//			case "2" : removeCart(); break;
			case "2" : paymentMenu(); break;
			case "9" : break;
			case "0" : return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

//	public void removeCart() {
//		boolean result = false;
//		result = pmm.removeCart(inputTitle());
//		System.out.println(result ? "> 삭제 성공!" : "> 삭제 실패! 찾으시는 메뉴가 없습니다.");
//	}
//
//	public String inputTitle() {
//		System.out.print("메뉴 이름을 입력하세요 > ");
//		return sc.next();
//	}

	public void paymentMenu() {
		String strPaymentMenu = "==========================================================\n"
				+ "                        주문 방식 선택                        \n"
				+ "==========================================================\n"
				+ "1. 포장 주문 (포장 할인 적용)\n"
				+ "2. 배달 주문 (배달 비용 추가)\n"
				+ "3. 장바구니 확인\n"
				+ "9. 이전 메뉴로 돌아가기\n"
				+ "0. 프로그램 종료\n"
				+ "==========================================================\n"
				+ ">> ";

		while(bool) {
			System.out.print(strPaymentMenu);
			String choice = sc.next();
			sc.nextLine();

			switch(choice) {
			case "1" : // 포장 주문
				System.out.println("포장 주문을 선택하셨습니다.");
				pmm.paymentOrderWay_Packing();
				pmm.joined();
				pmm.sortCart();
				pmm.totalPirce();
				orderCheckPacking();
				break;
			case "2" : // 배달 주문
				System.out.println("배달 주문을 선택하셨습니다.");
				pmm.paymentOrderWay_Deliver();
				pmm.joined();
				pmm.sortCart();
				pmm.totalPirce();
				orderCheckDeliver();
				break;
			case "3" : 
				orderMenu();
				break;
			case "9" :
				return;
			case "0" : // 주문 취소
				System.out.println("주문이 취소되었습니다.");
				bool = false;
				return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	private void orderCheckPacking() {
		String orderCheckMenu1 = "==========================================================\n"
				+ "                          주문 확인서                         \n"
				+ "===========================================================\n"
				+ "카테고리\t메뉴이름\t\t가격\t / \t";
		String orderCheckMenu2 = "총 가격 : ";
		String orderCheckMenu3 = "==========================================================\n";
		String orderCheckMenu4 = "1. 주문 하기\n"
				+ "0. 주문 취소\n"
				+ "==========================================================\n"
				+ ">> ";
		SendMail send = new SendMail();

		while(bool) {
			System.out.print(orderCheckMenu1);
			System.out.print(orderCheckMenu2);
			pmm.packing();
			System.out.print(orderCheckMenu3);
			pmm.sortCart();
			pmm.printCart();
			System.out.print(orderCheckMenu3);
			System.out.print(orderCheckMenu4);
			String choice = sc.next();
			sc.nextLine();
			switch(choice) {
			case "1" : // 주문 하기
				System.out.println("주문 완료 !");
				pmm.fileSave();
				pmm.fileRead();
				send.gmailSend();
				bool = false;
				break;
			case "0" : // 주문 취소
				System.out.println("주문이 취소되었습니다.");
				bool = false;
				break;
			default : System.out.println("잘못 입력하셨습니다.");
			}
			System.out.println("프로그램 종료");
		}
	}

	private void orderCheckDeliver() {
		String orderCheckMenu1 = "==========================================================\n"
				+ "                          주문 확인서                         \n"
				+ "===========================================================\n"
				+ "카테고리\t메뉴이룸\t\t가격\t / \t";
		String orderCheckMenu2 = "총 가격 : ";
		String orderCheckMenu3 = "==========================================================\n";
		String orderCheckMenu4 = "1. 주문 하기\n"
				+ "0. 주문 취소\n"
				+ "==========================================================\n"
				+ ">> ";

		SendMail send = new SendMail();
		while(bool) {
			System.out.print(orderCheckMenu1);
			System.out.print(orderCheckMenu2);
			pmm.deliver();
			System.out.print(orderCheckMenu3);
			pmm.sortCart();
			pmm.printCart();
			System.out.print(orderCheckMenu3);
			System.out.print(orderCheckMenu4);
			String choice = sc.next();
			sc.nextLine();
			switch(choice) {
			case "1" : // 주문 하기
				System.out.println("주문 완료 !");
				pmm.fileSave();
				pmm.fileRead();
				send.gmailSend();
				bool = false;
				break;
			case "0" : // 주문 취소
				System.out.println("주문이 취소되었습니다.");
				bool = false;
				break;
			default : System.out.println("잘못 입력하셨습니다.");
			}
			System.out.println("프로그램 종료");
		}
	}

	public String setMenu() {
		String strSetMenu = "==========================================================\n"
				+ "                         세트 메뉴                           \n"
				+ "==========================================================\n"
				+ "1. A세트 (불고기 피자 + 치킨텐더 + 코카콜라)-----------------20,900원\n"
				+ "2. B세트 (페퍼로니 피자 + 웨지감자 + 스프라이트)--------------17,400원\n"
				+ "3. C세트 (콤비네이션 피자 + 버팔로윙 + 환타)---------- ------20,900원\n"
				+ "4. D세트 (포테이토피자 + 스파게티 + 제로 코카콜라)-------------19,900원\n"
				+ "5. E세트 (치즈피자 + 치즈 볼 + 콜라)----------------------17,900원\n"
				+ "==========================================================\n"
				+ "0. 이전 메뉴로 돌아가기\n"
				+ "==========================================================\n"
				+ ">> ";

		System.out.print(strSetMenu);
		choiceSetMenu = sc.next();

		return choiceSetMenu;
	}

	public String singleMenu() {
		String strSingleMenu = "==========================================================\n"
				+ "                         단품 메뉴                           \n"
				+ "==========================================================\n"
				+ "1. 불고기 피자---------------------------------------16,900원\n"
				+ "2. 페퍼로니 피자--------------------------------------13,900원\n"
				+ "3. 콤비네이션 피자-------------------------------------13,900원\n"
				+ "4. 포테이토 피자--------------------------------------15,900원\n"
				+ "5. 치즈 피자-----------------------------------------13,900원\n"
				+ "==========================================================\n"
				+ "0. 이전 메뉴로 돌아가기\n"
				+ "==========================================================\n"
				+ ">> ";

		System.out.print(strSingleMenu);
		choiceSingleMenu = sc.next();

		return choiceSingleMenu;
	}

	public String sideMenu() {
		String strSideMenu = "==========================================================\n"
				+ "                        사이드 메뉴                           \n"
				+ "==========================================================\n"
				+ "1. 치킨 텐더------------------------------------------4,000원\n"
				+ "2. 웨지 감자------------------------------------------3,500원\n"
				+ "3. 버팔로 윙------------------------------------------6,000원\n"
				+ "4. 오븐 스파게티---------------------------------------5,000원\n"
				+ "5. 치즈볼--------------------------------------------4,000원\n"
				+ "==========================================================\n"
				+ "0. 이전 메뉴로 돌아가기\n"
				+ "==========================================================\n"
				+ ">> ";

		System.out.print(strSideMenu);
		choiceSideMenu = sc.next();

		return choiceSideMenu;
	}

	public String drinkMenu() {
		String strDrinkMenu = "==========================================================\n"
				+ "                         음료 메뉴                           \n"
				+ "==========================================================\n"
				+ "1. 코카콜라-------------------------------------------1,500원\n"
				+ "2. 스프라이트-----------------------------------------1,500원\n"
				+ "3. 환타---------------------------------------------1,500원\n"
				+ "4. 제로 코카콜라---------------------------------------1,500원\n"
				+ "==========================================================\n"
				+ "0. 이전 메뉴로 돌아가기\n"
				+ "==========================================================\n"
				+ ">> ";

		System.out.print(strDrinkMenu);
		choiceDrinkMenu = sc.next();

		return choiceDrinkMenu;
	}

	public String sauceMenu() {
		String strSauceMenu = "==========================================================\n"
				+ "                         소스 메뉴                           \n"
				+ "==========================================================\n"
				+ "1. 핫 소스---------------------------------------------200원\n"
				+ "2. 갈릭 소스--------------------------------------------200원\n"
				+ "==========================================================\n"
				+ "0. 이전 메뉴로 돌아가기\n"
				+ "==========================================================\n"
				+ ">> ";

		System.out.print(strSauceMenu);
		choiceSauceMenu = sc.next();

		return choiceSauceMenu;

	}
}
