package kh.java.mansuk_pc_cafe.controller;

import java.util.Scanner;

import kh.java.mansuk_pc_cafe.vo.FlatBillOrder;
import kh.java.mansuk_pc_cafe.vo.MemberAccount;

/**
 * 결제 클래스
 * 
 * @author 지은
 */
public class PaymentManager {

	protected MemberAccount memberAccount = new MemberAccount();
	protected Scanner sc = new Scanner(System.in);
   
	/*
	* 이용권 결제 메뉴 화면
	*/
	public String paymentMenu() {
		return "============================　홈　>　이용권  등록　>　결제　============================\r\n"
			 + "     결제 예정 금액 : " + FlatBillOrder.getTotal() + "원\r\n"
			 + "===================================================================================\r\n"
			 + "     -------------------------\r\n"
			 + "     <결제 수단 선택>\r\n"
			 + "     -------------------------\r\n"
			 + "     1. 현금 결제\r\n"
			 + "     2. 카드 결제\r\n"
			 + "     -------------------------\r\n"
			 + "===================================================================================\r\n"
			 + "     결제 수단을 선택해주세요 : ";
	}
   
	/*
	 * 음식주문 결제 메뉴 화면
	 */
	public String paymentMenu(int sums) {
		return "=============================　홈　>　음식 주문　>　결제　=============================\r\n"
			 + "     결제 예정 금액 : " + sums + "원\r\n"
			 + "===================================================================================\r\n"
			 + "     -------------------------\r\n"
			 + "     <결제 수단 선택>\r\n"
			 + "     -------------------------\r\n"
			 + "     1. 현금 결제\r\n"
			 + "     2. 카드 결제\r\n"
			 + "     -------------------------\r\n"
			 + "===================================================================================\r\n"
			 + "     결제 수단을 선택해주세요 : ";
	}
   
	/*
	 * 이용권 결제 메뉴
	 */
	public void payment() {
      
		while(true) {
         
			System.out.print(paymentMenu());
			int selectNum = sc.nextInt();

			switch(selectNum) {
			case 1: 
            // 현금 결제 메소드 호출
				if(cashPayment(FlatBillOrder.getTotal())) {
					System.out.println("     결제 완료");
					MemberAccountManager.loginMember.setRemnants(MemberAccountManager.loginMember.getRemnants() + FlatBillOrder.getAdditionalTime());
				} else {
					System.out.println("     결제 실패");
				}
				break;
			case 2:
				// 카드 결제 메소드 호출
				if(cardPayment()) {
					MemberAccountManager.loginMember.setRemnants(MemberAccountManager.loginMember.getRemnants() + FlatBillOrder.getAdditionalTime());
					System.out.println("     결제 완료");
				} else {
					System.out.println("     결제 실패");
				}
				break;
			default:
				new FlatBillOrderManager().printWrongInputMessage();
				continue;
			}
			
			System.out.println("===================================================================================");
			System.out.println("     잔여 이용시간 :" + MemberAccountManager.loginMember.getRemnants());
			System.out.println("===================================================================================");
			System.out.println("     이전으로 돌아갑니다.");
			break;
		}
	}
   
	/*
	 * 음식주문 결제 메뉴
	 */
	public void SnackbarPayment(int Snacksums) {
      
		if(Snacksums != 0) {
			while(true) {
         
				System.out.print(paymentMenu(Snacksums));
				int selectNum = sc.nextInt();

				switch(selectNum) {
				case 1: 
					// 현금 결제 메소드 호출
					if(cashPayment(Snacksums)) {
						System.out.println("     결제 완료");
					} else {
						System.out.println("     결제 실패");
					}
					break;
				case 2:
					// 카드결제 메소드 호출
					if(cardPayment()) {
						System.out.println("     결제 완료");
					} else {
						System.out.println("     결제 실패");
					}
					break;
				default:
					new FlatBillOrderManager().printWrongInputMessage();
					continue;
				}
				System.out.println("===================================================================================");
				System.out.println("     이전으로 돌아갑니다.");
				break;
			}
		}
		else
			System.out.println("     결제할 금액이 없습니다.");
	}
	
   /*
    * 이용권 현금결제
    */
	public boolean cashPayment() {
		int money = 0;
		int tmp1 = 0;

		payment:
		while(true) {
		System.out.println("======================　홈　>　이용권  등록　>　결제　>　현금　결제　=====================");
         System.out.print("     현금을 투입하세요 : ");
            
         try {
               
        	 tmp1 = sc.nextInt();
        	 if(tmp1 <= 0) {
        		 throw new RuntimeException();
        	 }
            
        	 money += tmp1;
        	 System.out.println("===================================================================================");
        	 System.out.println("     [누적 투입액 : "+ money + "원]");
        	 System.out.print("     [1. 금액 추가 투입 / 2. 투입 완료] : ");
            
        	 int tmp2 = sc.nextInt();
        	 if(tmp2 != 1 && tmp2 != 2) 
        		 throw new RuntimeException();
            
        	 switch(tmp2) {
        	 case 1:
        		 continue;
        	 case 2:
        		 if(money >= FlatBillOrder.getTotal()) {
                  
        			 money -= FlatBillOrder.getTotal();
                  
        			 System.out.println("     잔돈을 가져가십시오 : " + money + "원");
        			 return true;
        		 }
        		 else {
        			 System.out.println("     투입액이 부족합니다.");
        		 }
        	 default:
        		 throw new RuntimeException();
        	 }
            
         } catch(RuntimeException e) {
        	 System.err.println("error!");
               
        	 while(true) {
        		 
        		 System.out.print("     [1. 금액 투입창으로 / 2. 이전으로(잔돈 반환)] : ");
               
               String tmp3 = sc.next();
               
               switch(tmp3) {
               case "1":
                  continue payment;
               case "2":
            	   System.out.println("     잔돈을 가져가십시오 : " + money + "원");
                  money = 0;
                  return false; // 잔돈 반환 만들기
               }
            }
         }
      }
   }
   
   /*
    * 이용권 카드결제
    */
   public boolean cardPayment() {
	   while(true) {
		   System.out.println("======================　홈　>　이용권  등록　>　결제　>　카드　결제　=====================");
		   System.out.print("     카드를 투입해주세요. [1. 네 / 2. 이전으로] : ");
		   String tmp = sc.next();
		   
		   switch(tmp) {
		   case "1":
			   System.out.println("===================================================================================");
			   System.out.println("     >>>>>>>>>>>>>>>>>>> 결제 진행중 >>>>>>>>>>>>>>>>>>> ");
			   System.out.println();
			   System.out.println("     카드 결제가 완료되었습니다.");
			   System.out.println("     카드를 가져가세요");
			   System.out.println();
			   return true;
		   case "2":
			   return false;
		   default:
			   continue;
		   }
	   }
	}
   
	/*
	 * 음식주문 현금결제
	 */
	public boolean cashPayment(int sums) {
		int money = 0;
		int tmp1 = 0;
		
		payment:
		while(true) {
			System.out.print("     현금을 투입하세요 : ");
            
			try {
               
	        	 tmp1 = sc.nextInt();
	        	 if(tmp1 <= 0) {
	        		 throw new RuntimeException();
	        	 }
	            
	        	 money += tmp1;
	        	 System.out.println("===================================================================================");
	        	 System.out.println("     [누적 투입액 : "+ money + "원]");
	        	 System.out.print("     [1. 금액 추가 투입 / 2. 투입 완료] : ");
					
	        	 int tmp2 = sc.nextInt();
	        	 if(tmp2 != 1 && tmp2 != 2) 
	        		 throw new RuntimeException();
	        	 
	        	 switch(tmp2) {
	        	 case 1:
	        		 continue;
	        	 case 2:
	        		 if(money >= sums) {
	                  
	        			 money -= sums;
	
	        			 System.out.println("     잔돈을 가져가십시오 : " + money + "원");
	        			 return true;
	        		 }
	        		 else {
	        			 System.out.println("투입액이 부족합니다.");
	        		 }
	        	 default:
	        		 throw new RuntimeException();
	        	 }
	            
	         } catch(RuntimeException e) {
	        	 System.err.println("error!");
	               
	        	 while(true) {
	               
	        		 System.out.print("     [1. 금액 투입창으로 / 2. 이전으로(잔돈 반환)] : ");
						
	        		 String tmp3 = sc.next();
	               
	        		 switch(tmp3) {
	        		 case "1":
	        			 continue payment;
	        		 case "2": 
	        			 System.out.println("     잔돈을 가져가십시오 : " + money + "원");
	        			 money = 0;
	        			 return false; //잔돈 반환 만들기
	        		 }
	        	 }
	         }
		}
   }
   
}