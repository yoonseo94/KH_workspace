package kh.java.mansuk_pc_cafe.controller;

import java.util.Scanner;

/**
 * 스낵바 메뉴 클래스
 * 
 * @author 민서
 */
public class SnackBarMenu {

	private SnackBarManager manager1 = new SnackBarManager();
    private SnackBarOrderManager manager3 = new SnackBarOrderManager();
    
    private Scanner sc = new Scanner(System.in);
	
	public void snackMenu() {
		
		String smenu = "==================================　홈　>　음식주문　=================================\r\n"
					 + "     1. 메뉴선택\r\n"
					 + "     2. 주문취소\r\n"
					 + "     3. 주문확인\r\n"
					 + "     4. 주문결제\r\n"
					 + "     0. 돌아가기\r\n"
					 + "===================================================================================\r\n"
					 + "     >> 메뉴선택 : ";
		
		while(true) {
			
			System.out.print(smenu);
			int selected = sc.nextInt();
			
			switch(selected) {
			case 1: 
				manager3 =  manager1.selectCategory();
				break;
			case 2: 
				manager3.delete();
				break;
			case 3:
				manager3.order();
				break;
			case 4:
				manager3.payment();
				break;	
			case 0:
				return; 
			default:
				System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}

}