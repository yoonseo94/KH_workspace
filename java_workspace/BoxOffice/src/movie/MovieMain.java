package movie;

import java.util.Scanner;

public class MovieMain {
	
	private	Scanner sc = new Scanner(System.in);
	int Anum = 0; 
	int Tnum = 0; 
	int Pnum = 0;
	int Snum = 0;
	int Snum1 = 0;
	int Snum2 = 0;
	int Dnum = 0;
	int Tsnum = 0;
	int price = 0;	
	int customermoney = 0;
	
    int [][] seats = new int[5][5];
    String columnInput;
    char seatColumn;
    int seatRow;
	
    
	public void kiosk() {
	
		String menu = "\n\t⁂ KH BOXOFFICE ⁂\t\n"
				+ "╭──────────────────────────────╮\n\n"
				+ "\t ➊ 영화 예매하기  \n"
				+ "\t ➋ SNACK \n"
				+ "\t ⓿  종료 \n\n"
				+ "╰──────────────────────────────╯\n"
				+ "원하시는 메뉴의 번호를 입력하세요 ➪\n";
			
				System.out.print(menu);
			do {	
				String choice = sc.next();
				switch(choice) {
				case "1" : movieList1(); break;
				case "2" : snackList1(); break;
				case "0" :System.out.println(
						"ට   ​  ⠀◝◜  ⠀  ⠀⠀◝◜\r\n"
						+ "⠀◝◜  ⠀◝◜⠀◝◜  ⠀  ⠀⠀⠀⠀⠀◝◜\r\n"
						+ "_◢╲___◢╲◢╲_◢╲_◢╲_\r\n"); 
					System.out.println("KH BOXOFFICE를 이용해주셔서 감사합니다."); break;
				default: System.out.println("❌ 잘못 입력하셨습니다."); 
				}
			} while(true);
		}
	



	public void pay1() {
		
		Scanner sc = new Scanner (System.in);
				
				String pay = "💲 결제 방식을 선택하세요 \n ➀ 카드 ➁ 현금";
				System.out.println(pay);
				int choice = sc.nextInt();
		
					switch(choice) {
					case 1 : 
						price = (Anum * 13_000 + Tnum * 8_000) + (Snum * 3500 + Snum1 * 5000 + Snum2 * 4500 + Dnum * 3500);
						System.out.println("카드를 선택하셨습니다.\n결제 금액은 " + price + "원 입니다. \n");
						System.out.println("--------------------------------- \n결제 완료 되었습니다.\n즐거운 관람되세요");
						break;
						
					case 2 : 
                        price = (Anum * 13_000 + Tnum * 8_000) + (Snum * 3500 + Snum1 * 5000 + Snum2 * 4500 + Dnum * 3500);
                        System.out.println("현금를 선택하셨습니다.\n결제 금액은 " + price + "원 입니다. \n");
                        System.out.println("현금을 넣어주세요 \n");
                        
                        while(true) {                                
                            customermoney = sc.nextInt();
                            if (customermoney < price) {
                            System.out.println("현금이 부족합니다.\n 다시 결제 해 주세요");
                            continue;
                            }
                            else if(customermoney > price || customermoney == price ) {
                                customermoney -= price;
                                System.out.println("결제 완료 되었습니다. \n잔액은 "  + customermoney + "입니다."
                                        + " 즐거운 관람 되세요. 감사합니다. \n"); 
                    } break;
			}
					case 0 : kiosk(); break;
					default : System.out.println("잘못 된 번호입니다.");
		}
}	

	public void snackList1() {
		Scanner sc = new Scanner (System.in);
		String snack = 
					"╭────────── SNACK MENU ───────────╮\n"
				  + "\t 10. 오리지널 팝콘 - 3500 \n"
				  + "\t 11. 캬라멜 팝콘 - 3500 \n"
				  + "\t 12. 어니언 팝콘 - 3500 \n"
				  + "\t 13. 버터오징어 - 5000 \n"
				  + "\t 14. 나초  - 4500 \n"
				  + "─────────────── DRINK ───────────── \n"
				  + "\t 20. 코카콜라 m - 3500 	\n"
				  + "\t 21. 스프라이트 m - 3500 \n"
				  + "\t 22. 오렌지주스 m - 3500 \n"
				  + "\t 0. 종료 \n"
				  + "╰─────────────────────────────────╯ \n"
				  + "원하시는 메뉴의 번호를 입력하세요 ➪ \n";
		
		while(true) {
			System.out.println(snack);
			int choice = sc.nextInt();
			
			switch(choice) {
			case 10 : 
				System.out.println("오리지널 팝콘을 선택하셨습니다.");
				System.out.println("구매 수량을 입력해 주세요.");
				Snum = sc.nextInt();
	
				Tsnum = Snum + Dnum + Snum1 + Snum2;
				System.out.printf("오리지널 팝콘 %d 개를 선택 하셨습니다. \n\n", Snum); 
				break;
			case 11 : 
				System.out.println("캬라멜 팝콘을 선택하셨습니다.");
				System.out.println("구매 수량을 입력해 주세요.");
				Snum = sc.nextInt();

				Tsnum = Snum + Dnum + Snum1 + Snum2;
				System.out.printf("캬라멜 팝콘 %d 개를 선택 하셨습니다. \n\n", Snum); 
				break;
			case 12 : 
				System.out.println("어니언 팝콘을 선택하셨습니다.");
				System.out.println("구매 수량을 입력해 주세요.");
				Snum = sc.nextInt();

				Tsnum = Snum + Dnum + Snum1 + Snum2;
				System.out.printf("어니언 팝콘 %d 개를 선택 하셨습니다. \n\n", Snum); 
				break;
			case 13 : 
				System.out.println("버터 오징어를 선택하셨습니다.");
				System.out.println("구매 수량을 입력해 주세요.");
				Snum1 = sc.nextInt();

				Tsnum = Snum + Dnum + Snum1 + Snum2;
				System.out.printf("버터 오징어 %d 개를 선택 하셨습니다. \n\n", Snum1); 
				break;
			case 14 : 
				System.out.println("나초를 선택하셨습니다.");
				System.out.println("구매 수량을 입력해 주세요.");
				Snum2 = sc.nextInt();

				Tsnum = Snum + Dnum + Snum1 + Snum2;
				System.out.printf("나초 %d 개를 선택 하셨습니다. \n\n", Snum2); 
				break;
			case 20 :
				System.out.println("코카콜라를 선택하셨습니다.");
				System.out.println("구매 수량을 입력해 주세요.");
				Dnum = sc.nextInt();

				Tsnum = Snum + Dnum + Snum1 + Snum2;
				System.out.printf("코카콜라 %d 개를 선택 하셨습니다. \n\n", Dnum); 
				break;
			case 21 : 
				System.out.println("스프라이트를 선택하셨습니다.");
				System.out.println("구매 수량을 입력해 주세요.");
				Dnum = sc.nextInt();

				Tsnum = Snum + Dnum + Snum1 + Snum2;
				System.out.printf("스프라이트 %d 개를 선택 하셨습니다. \n\n", Dnum); 
				break;
			case 22 : 
				System.out.println("오렌지 주스를 선택하셨습니다.");
				System.out.println("구매 수량을 입력해 주세요.");
				Dnum = sc.nextInt();

				System.out.printf("오렌지 주스 %d 개를 선택 하셨습니다. \n\n", Dnum); 
				break;
			case 0 : pay1(); break;
			default : System.out.println("잘못 된 번호입니다.");
				
			}
		}
		
		
	}
	public void movieList1() {
		Scanner sc = new Scanner (System.in);	
		String moviemenu = 
					"╭────────── 상영작 안내 ───────────╮\n"
				  + "\t ➊ 더 배트맨 \n"
				  + "\t ➋ 이상한 나라의 수학자 \n"
				  + "\t ➌ 언차티드 \n"
				  + "\t ➍ 블랙라이트 \n"
				  + "\t ➎ 스파이더맨: 노 웨이 홈 \n"
				  + " ─────────────────────────────── \n"
				  + "\t ⓿ 종료\n"
				  + "╰───────────────────────────────╯ \n"
				  + "원하시는 메뉴의 번호를 입력하세요 ➪ \n";
		
		while(true) {
			System.out.println(moviemenu);
			int choice = sc.nextInt();
			switch(choice) {
				case 1 : 
					System.out.println("더 배트맨 영화를 선택하셨습니다.");
					System.out.println("성인 인원 수를 입력해 주세요.");
					Anum = sc.nextInt();
					System.out.println("청소년 인원 수를 입력해 주세요");
					Tnum = sc.nextInt();
					
					Pnum = Anum + Tnum;
					System.out.printf("더 배트맨 %d 명 선택 하셨습니다. \n\n", Pnum); 
					break;
					
				case 2 : 
					System.out.println("이상한 나라의 수학자 영화를 선택하셨습니다.");
					System.out.println("성인 인원 수를 입력해 주세요.");
					Anum = sc.nextInt();
					System.out.println("청소년 인원 수를 입력해 주세요");
					Tnum = sc.nextInt();
					
					Pnum = Anum + Tnum;
					System.out.printf("이상한 나라의 수학자 %d 명 선택 하셨습니다. \n\n", Pnum); 
					break;
				case 3 : 
					System.out.println("언차티드 영화를 선택하셨습니다.");
					System.out.println("성인 인원 수를 입력해 주세요.");
					Anum = sc.nextInt();
					System.out.println("청소년 인원 수를 입력해 주세요");
					Tnum = sc.nextInt();
					
					Pnum = Anum + Tnum;
					System.out.printf("언차티드 %d 명 선택 하셨습니다. \n\n", Pnum); 
					break;
				case 4 : 
					System.out.println("블랙라이트 영화를 선택하셨습니다.");
					System.out.println("성인 인원 수를 입력해 주세요.");
					Anum = sc.nextInt();
					System.out.println("청소년 인원 수를 입력해 주세요");
					Tnum = sc.nextInt();
					
					Pnum = Anum + Tnum;
					System.out.printf("블랙 라이트 %d 명 선택 하셨습니다. \n\n", Pnum); 
					break;
				case 5 : 
					System.out.println("스파이더맨 : 노 웨이 홈 영화를 선택하셨습니다.");
					System.out.println("성인 인원 수를 입력해 주세요.");
					Anum = sc.nextInt();
					System.out.println("청소년 인원 수를 입력해 주세요");
					Tnum = sc.nextInt();
					
					Pnum = Anum + Tnum;
					System.out.printf("스파이더맨 : 노 웨이 홈  %d 명 선택 하셨습니다. \n\n", Pnum); 
					break;
				case 0 : return;
				default : 
					System.out.println("잘못 된 번호입니다.");
				} movieTime();
		}
	}
	
	public void movieSeat() {
	
	    do {
	
	        System.out.println();
	        System.out.println("─────────────[ SCREEN ]────────────");
	        System.out.println();
	        System.out.print(" [ / ] ");
	        for (int i = 0; i < seats.length; i++) {
	            System.out.print("[ "+ (i + 1) +" ]");
	        }
	        System.out.println();
	
	        for (int i = 0; i < seats.length; i++) {
	        
	        	System.out.print(" [ "+ (char)(i + 65) +" ] ");
	            for (int j = 0; j < seats[i].length; j++) {
	
	                if(seats[i][j] == 0) {
	                    System.out.print("[ □ ]");
	                }else {
	                    System.out.print("[ ■ ]");
	                }
	            }
	            System.out.println();
	        }
	        System.out.println();
	        
	        System.out.print("───────────────────────────────────");
	        System.out.print("\n예매를 원하는 좌석의 열을 입력해주세요.\n(예매를 종료하시려면 exit을 입력)");
	        columnInput = sc.next();
	
	        if(columnInput.equals("exit")) {
	            System.out.println("예매가 종료되었습니다.");
	            break;
	        }
	
	        seatColumn = columnInput.trim().charAt(0);
	        System.out.println("선택하신 열은 " + seatColumn + "입니다.");
	
	        if(seatColumn < 65 || seatColumn > 70) {
	            System.out.println("선택할 수 없는 좌석입니다");
	            continue;
	        } 
	
	        int column = seatColumn - 65;
	
	        System.out.print("예매하실 행 번호를 입력해주세요 > ");
	        seatRow = sc.nextInt();
	
	        if(seatRow < 1 || seatRow > 5) {
	            System.out.println("선택할 수 없는 행입니다.");
	            continue;
	        }
	
	        System.out.println("선택하신 좌석 번호는 " + seatColumn + seatRow + " 입니다");
	        System.out.println("선택을 완료하시겠습니까? (Y / N) : ");
	
	        String s = sc.next();
	        if(s.equals("y") || s.equals("Y")) {
	            seats[column][seatRow - 1] = 1;
	            System.out.println("좌석 선택이 완료되었습니다!");
	            
	        }else {
	            System.out.println("예매를 중단했습니다.");
	            break;
	        }
	    } while(true);
	    snackList1();
	
	}
	
	public void movieTime()	{
		
			String menu = "╭───────── 상영회차 안내 ──────────╮\n"
						 + "\t ➊ 09 : 00\n"
						 + "\t ➋ 12 : 00 \n"
						 + "\t ➌ 15 : 00 \n"
						 + "\t ➍ 18 : 00 \n"
						 + "\t ➎ 21 : 00 \n"
						 + " ───────────────────────────────\n "
						 + "  ⓿ 종료"
						 + "╰───────────────────────────────╯\n";
			System.out.println(menu);
			int timeSelect = sc.nextInt();
			String time = " 09 : 00";
			String time2 = " 12 : 00";
			String time3 = " 15 : 00";
			String time4 = " 18 : 00";
			String time5 = " 21 : 00";
			
			switch(timeSelect){
				case 1 : System.out.println("선택하신 회차는" + time + "시 영화입니다."); break;
				case 2 : System.out.println("선택하신 회차는" + time2 + "시 영화입니다."); break;
				case 3 : System.out.println("선택하신 회차는" + time3 + "시 영화입니다.");break;
				case 4 : System.out.println("선택하신 회차는" + time4 + "시 영화입니다.");break;
				case 5 : System.out.println("선택하신 회차는" + time5 + "시 영화입니다.");break;
				case 0 : return;
			}
			movieSeat();
	}

	
	}


		