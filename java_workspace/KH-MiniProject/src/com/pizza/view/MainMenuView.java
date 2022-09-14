package com.pizza.view;

import java.util.Scanner;

public class MainMenuView {
	public void mainMenu() {
		
		Scanner sc = new Scanner(System.in);
		
		String pizza[] = {	"##########################################################",
							"#                                                        #",
							"#  #######   #####  ########  ########         #         #",
							"#  #      #    #          #         #         # #        #",
							"#  #      #    #         #         #         #   #       #",
							"#  #######     #        #         #         #     #      #",
							"#  #           #       #         #         #########     #",
							"#  #           #      #         #         #         #    #",
							"#  #           #     #         #         #           #   #",
							"#  #         #####  ########  ########  #             #  #",
							"#                                                        #",
							"##########################################################",
							"   피자 주문 프로그램 (Order : Press Enter / Program End : 0)  ",
							"==========================================================",
		};
		
		String start = ">> ";
		
		for(int i = 0; i < pizza.length; i++) {
			System.out.println(pizza[i]);
			try {
				Thread.sleep(100); //500
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
        System.out.print(start);
        String str = sc.nextLine();
        
        if(str.equals("0")) {
            System.out.println("프로그램 종료 !");
        } else if(str.equals("")) {
            System.out.println("주문 시작 !");
            new PizzaMenuView().pizzaMenu();
        } else{
            System.out.println("잘못 입력하셨습니다.\n");
            new MainMenuView().mainMenu();
        }
    }
}
