package kh.java.mansuk_pc_cafe.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kh.java.mansuk_pc_cafe.vo.FlatBillOrder;
import kh.java.mansuk_pc_cafe.vo.MemberAccount;

/**
 * 이용권 메뉴 클래스
 * 
 * @author 지은
 */
public class FlatBillOrderController {
	
	private Scanner sc = new Scanner(System.in);
	
	private List<FlatBillOrder> orderList = new ArrayList<>();
	private FlatBillOrderManager fbom = new FlatBillOrderManager(orderList);

	/*
	 * 주문 시작
	 */
	public void flatBillOrder() {
		String flatBillMenu = "===============================　홈　>　이용권  등록　================================\r\n"
							+ "     -------------------------\r\n"
							+ "     이용시간\t이용금액\r\n"
							+ "     -------------------------\r\n"
			        		+ "     1. 01시간\t1000원\r\n"
			                + "     2. 04시간\t3800원\r\n"
			                + "     3. 10시간\t9000원\r\n"
			                + "     4. 50시간\t43000원\r\n"
			                + "     5. 100시간\t80000원\r\n"
			                + "     -------------------------\r\n"
			                + "     0. 이전으로\r\n"
			                + "===================================================================================\r\n"
			                + "     >> 메뉴선택 : ";
		
		while(true) {

			System.out.print(flatBillMenu);	//메뉴 출력
			int selectNum = 0;				//선택 번호

			try{
				
				selectNum = sc.nextInt();
				
				if (selectNum < 0 || selectNum > 5) {
					throw new Exception();
				}
				
				if (selectNum == 0) {
					orderList.clear();
					FlatBillOrder.setTotal(0);
					FlatBillOrder.setAdditionalTime(0);
					System.out.println("     주문을 종료하고 이전으로 돌아갑니다.");
					break;
				}

			} catch(InputMismatchException e) {
				fbom.printWrongInputMessage();
				sc.next();
				continue;
			}
			catch(Exception e) {
				fbom.printWrongInputMessage();
				continue;
			}

			int count = fbom.selectCount();
			
			if (orderList.size() != 0) {
				for(int i = 0; i < orderList.size(); i++) {

					if(orderList.get(i).getProductNum() == selectNum) {
						int tmp = orderList.get(i).getCount() + count;
						
						FlatBillOrder.setTotal( FlatBillOrder.getTotal() - orderList.get(i).getCount() * orderList.get(i).getPrice());
						FlatBillOrder.setAdditionalTime(FlatBillOrder.getAdditionalTime() - orderList.get(i).getCount() * orderList.get(i).getProductName());
						
						orderList.remove(i);
						orderList.add(new FlatBillOrder(selectNum, tmp));
						break;
					}
					else if(i + 1 == orderList.size() ) {
						orderList.add(new FlatBillOrder(selectNum, count));// 삭제X
						break;
					}
				}
			}
			else {
				orderList.add(new FlatBillOrder(selectNum, count));
			}

			// 추가주문 여부 확인
			if(fbom.isAddOrder() == false) {
				if(fbom.checkOrder()) {
					// 결제 클래스 불러오기
					new PaymentManager().payment();
					break;
				}
				else {
					orderList.clear();
					FlatBillOrder.setTotal(0);
				}
			}
		}
	}

}
