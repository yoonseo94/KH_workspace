package kh.java.mansuk_pc_cafe.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kh.java.mansuk_pc_cafe.vo.FlatBillOrder;

/**
 * 이용권 관리 클래스
 * 
 * @author 지은
 */
public class FlatBillOrderManager {
	
	private Scanner sc = new Scanner(System.in);
	static List<FlatBillOrder> orderList = new ArrayList<>();
	
	public FlatBillOrderManager() {}
	
	public FlatBillOrderManager(List<FlatBillOrder> orderList) {
		FlatBillOrderManager.orderList = orderList;
	}

	public List<FlatBillOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<FlatBillOrder> orderList) {
		FlatBillOrderManager.orderList = orderList;
	}
	
	/*
	 * 이용권 개수 선택
	 */
	public int selectCount() {

		int count = 0;
		
		while(true) {
			System.out.print("     구매하실 개수를 선택하세요 : ");
			
			try {
				count = sc.nextInt();
				
				if (count > 0)
					return count;
				
			} catch(InputMismatchException e) {
				sc.next();
			}
			printWrongInputMessage();
		}
	}

	/*
	 * 추가주문 확인
	 */
	public boolean isAddOrder() {

		while(true) {
			
			try {
				System.out.println("=======================　홈　>　이용권  등록　>　추가 주문 여부　========================");
				System.out.print("     추가 주문 하시겠습니까? (y/n) : ");
					
				char isAddOrder = sc.next().toLowerCase().charAt(0);

				switch(isAddOrder) {
				case 'y':
					return true;
				case 'n':
					return false;
				default:
					continue;
				}
					
			} catch(IndexOutOfBoundsException e) {
				sc.next();
			} catch(InputMismatchException e) {
				sc.next();
			}
			printWrongInputMessage();
		}
	}
	
	/*
	 * 잘못된 주문 확인 메세지
	 */
	public void printWrongInputMessage() {
		System.out.println("     잘못 입력하셨습니다. 다시 입력하세요.");
	}
	
	/*
	 * 주문 확인
	 */
	public boolean checkOrder() {
		System.out.println("==========================　홈　>　이용권  등록　>　주문 확인　==========================");
		Collections.sort(orderList);

		for(int i = 0; i < orderList.size(); i++) {
			FlatBillOrder orderinform = orderList.get(i);
			System.out.print("     " + orderinform);
		}
		
		System.out.println("===================================================================================");
		
		System.out.println("     합계 : " + FlatBillOrder.getTotal() + "원");
		System.out.println("     추가 예정 시간 : " + FlatBillOrder.getAdditionalTime() + "시간");
		System.out.println("===================================================================================");
		
		while(true) {
			
			try {
				System.out.print("     계속하시겠습니까? (y/n) : ");
				
				char isNext = sc.next().toLowerCase().charAt(0);
				
				switch(isNext) {
				case 'y':
					return true;
				case 'n':
					return false;
				}

			} catch(IndexOutOfBoundsException e) {
				sc.next();
			}
			printWrongInputMessage();
		}
	}

}
