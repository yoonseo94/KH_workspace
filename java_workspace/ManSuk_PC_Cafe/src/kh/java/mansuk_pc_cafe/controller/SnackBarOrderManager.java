package kh.java.mansuk_pc_cafe.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import kh.java.mansuk_pc_cafe.vo.Snack;

import java.util.Scanner;
import java.util.Set;

/**
 * 스낵바 매니저 클래스
 * 
 * @author 민서
 */
public class SnackBarOrderManager {
	
	private Scanner sc = new Scanner(System.in);

	Map<Snack, Integer> map = new HashMap<>();	// 새 hashmap생성
    Map<Snack, Integer> map1 = new HashMap<>(); // 새 hashmap생성
    Map<Snack, Integer> map2 = new HashMap<>(); // 새 hashmap생성
    Map<Snack, Integer> map3 = new HashMap<>(); // 새 hashmap생성
    Map<Snack, Integer> map4 = new HashMap<>(); // 새 hashmap생성
    
    public static int snackTotal;
    
    public void order() {
        if(map.size() > 0) {
        	System.out.println("============================　홈　>　음식주문　>　주문확인　============================");
        	System.out.println("     주문하신 메뉴는 다음과 같습니다.\n");
        	
        	Set<Entry<Snack, Integer>> entrySet = map.entrySet();
        	
        	Iterator<Map.Entry<Snack, Integer>> iter2 = entrySet.iterator();
    		while(iter2.hasNext()) {
    			Map.Entry<Snack, Integer> entry = iter2.next();
    			Snack key = entry.getKey();
    			Integer value = entry.getValue();
    			System.out.printf("     주문메뉴 : %s%n     주문수량 : %s%n%n", key, value);
    		}
        }
        else
        	System.out.println("     주문하신 메뉴가 없습니다.");
    }
    
    public void delete() {
    	System.out.println("============================　홈　>　음식주문　>　주문취소　============================");
    	System.out.print("     주문취소 후 메뉴를 다시 선택하셔야 합니다.\n"
    				   + "     주문을 취소하시겠습니까? (y/n) : ");
    	
    	int again3 = sc.nextInt();
    	
    	switch(again3) {
    	case 1:
    		System.out.println("     주문이 취소되었습니다.");
    		System.out.println("     다시 주문해주세요.");
    		map.clear();
    		break;
    	case 2:
    		System.out.println("     이전 단계로 돌아갑니다.");
    		return;
    	default:
    		System.out.println("     잘못 입력하셨습니다. 다시 입력해주세요.");
    		return;
    	}
    }
    
    public Map<Snack, Integer> getMap() {
        return map;
    }
    
	public void payment() {
		
		new PaymentManager().SnackbarPayment(snackTotal);
		map.clear();
		snackTotal = 0;
	}
    
}
