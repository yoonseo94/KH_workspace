package kh.java.mansuk_pc_cafe.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kh.java.mansuk_pc_cafe.vo.Snack;


/**
 * 스낵바 메뉴 선택 클래스
 * 
 * @author 민서
 */
public class SnackBarManager {
	
	private SnackBarOrderManager manager3 = new SnackBarOrderManager();
	private Scanner sc = new Scanner(System.in);
	
	// 밥, 라면, 제조음료, 캔음료(pet음료), 감자, 핫도그, 햄버거, 칩(과자), 기타음식
	private List<Snack> snackList1 = new ArrayList<>(); // 밥
	{
		snackList1.add(new Snack(1 , "참치김치 볶음밥" , "" , 4500));
		snackList1.add(new Snack(2 , "새우 볶음밥" , "" , 4500));
		snackList1.add(new Snack(3 , "치킨커리 볶음밥" , "" , 4500));
		snackList1.add(new Snack(4 , "김치 볶음밥" , "" , 4500));
		snackList1.add(new Snack(5 , "낚지 덮밥" , "" , 4500));
		snackList1.add(new Snack(6 , "치킨마요 덮밥" , "" , 5000));
		snackList1.add(new Snack(7 , "매콤치킨마요 덮밥" , "" , 5000));
		snackList1.add(new Snack(8 , "오삼불고기 덮밥" , "" , 5500));
		snackList1.add(new Snack(9 , "제육 덮밥" , "" , 5500));
		snackList1.add(new Snack(10 , "닭볶음 덮밥" , "" , 5500));
	}
	
	private List<Snack> snackList2 = new ArrayList<>(); // 라면
	{
		snackList2.add(new Snack(1 , "너구리" , "" , 3000));
		snackList2.add(new Snack(2 , "오징어짬뽕" , "" , 3000));
		snackList2.add(new Snack(3 , "안성탕면" , "" , 3000));
		snackList2.add(new Snack(4 , "삼양라면" , "" , 3000));
		snackList2.add(new Snack(5 , "진라면" , "" , 3000));
		snackList2.add(new Snack(6 , "틈새라면" , "" , 3000));
		snackList2.add(new Snack(7 , "신라면" , "" , 3000));
		snackList2.add(new Snack(8 , "짜파게티" , "" , 3200));
		snackList2.add(new Snack(9 , "사천짜파게티" , "" , 3200));
		snackList2.add(new Snack(10 , "볶음 간짬뽕" , "" , 3200));
		snackList2.add(new Snack(11 , "진짬뽕" , "" , 3200));
		snackList2.add(new Snack(12 , "불닭볶음면" , "" , 3200));
		snackList2.add(new Snack(13 , "참깨라면" , "" , 3200));
		snackList2.add(new Snack(14 , "생생우동" , "" , 4000));
	}
	
	private List<Snack> snackList3 = new ArrayList<>(); // 토핑
	{
		snackList3.add(new Snack(1 , "" , "김치" , 0));
		snackList3.add(new Snack(2 , "" , "단무지" , 0));
		snackList3.add(new Snack(3 , "" , "슬라이스 치즈" , 500));
		snackList3.add(new Snack(4 , "" , "참치토핑" , 500));
		snackList3.add(new Snack(5 , "" , "계란후라이" , 700));
		snackList3.add(new Snack(6 , "" , "모짜렐라치즈" , 1000));
		snackList3.add(new Snack(7 , "" , "스팸토핑" , 1000));
	}
	
	private List<Snack> snackList4 = new ArrayList<>(); // 제조음료
	{
		snackList4.add(new Snack(1 , "코카콜라" , "" , 1000));
		snackList4.add(new Snack(2 , "코카콜라 사이즈업" , "" , 1500));
		snackList4.add(new Snack(3 , "스프라이트" , "" , 1000));
		snackList4.add(new Snack(4 , "스프라이트 사이즈업" , "" , 1500));
		snackList4.add(new Snack(5 , "환타 오렌지" , "" , 1000));
		snackList4.add(new Snack(6 , "환타 오렌지 사이즈업" , "" , 1500));
		snackList4.add(new Snack(7 , "환타 포도" , "" , 1000));
		snackList4.add(new Snack(8 , "환타 포도 사이즈업" , "" , 1500));
		snackList4.add(new Snack(9 , "환타 파인애플" , "" , 1000));
		snackList4.add(new Snack(10 , "환타 파인애플 사이즈업" , "" , 1500));
		
	}
	
	private List<Snack> snackList5 = new ArrayList<>(); // 캔음료 + PET는 추후 추가
	{
		snackList5.add(new Snack(1 , "코카콜라캔" , "" , 1500));
		snackList5.add(new Snack(2 , "핫식스캔" , "" , 1500));
		snackList5.add(new Snack(3 , "닥터 페퍼캔" , "" , 1500));
		snackList5.add(new Snack(4 , "마운틴 듀캔" , "" , 1500));
		snackList5.add(new Snack(5 , "웰치스 포도캔" , "" , 1500));
		snackList5.add(new Snack(6 , "웰치스 딸기캔" , "" , 1500));
		snackList5.add(new Snack(7 , "웰치스 청포도캔" , "" , 1500));
		snackList5.add(new Snack(8 , "코코팜 포도캔" , "" , 1500));
		snackList5.add(new Snack(9 , "코코팜 화이트캔" , "" , 1500));
		snackList5.add(new Snack(10 , "코코팜 복숭아캔" , "" , 1500));
		snackList5.add(new Snack(11 , "밀키스캔" , "" , 1500));
		snackList5.add(new Snack(12 , "자몽 에이드캔" , "" , 1500));
		snackList5.add(new Snack(13 , "블루 하와이 캔" , "" , 1500));
		snackList5.add(new Snack(14 , "밀키스캔" , "" , 1500));
		snackList5.add(new Snack(15 , "맥콜캔" , "" , 1500));
		snackList5.add(new Snack(16 , "칠성사이다캔" , "" , 1500));
		snackList5.add(new Snack(17 , "포카리스웨트캔" , "" , 1500));
	}
	
	public SnackBarOrderManager selectCategory() {
		String cateMenu = "===========================　홈　>　음식주문　>　메뉴 선택　============================\r\n"
					    + "     1. 밥 메뉴\r\n"
					    + "     2. 라면 메뉴\r\n"
					    + "     3. 토핑 메뉴\r\n"
					    + "     4. 제조음료 메뉴\r\n"
					 	+ "     5. 캔음료 메뉴\r\n"
					 	+ "     0. 전단계로 가기\r\n"
					 	+ "===================================================================================\r\n"
					 	+ "     >> 메뉴선택 : ";
		
		while(true) {
			
			System.out.print(cateMenu);
			int selected1 = sc.nextInt();
			
			switch(selected1) {
			case 1:
				 selectList1(); 
				break;
			case 2: 
				selectList2();
				break;
			case 3: 
				selectList3(); 
				break;
			case 4: 
				selectList4(); 
				break;
			case 5: 
				selectList5(); 
				break;
			case 0:
				return null;
			default:
				System.out.println("     잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
			
			System.out.println("===================================================================================");
			System.out.print("     추가주문하시겠습니까? (y/n) : ");
			char again2 = sc.next().toLowerCase().charAt(0);
			switch(again2) {
			case 'y':
				manager3 = selectCategory();
				return manager3;
			case 'n':
				System.out.println("     메뉴 선택이 완료되었습니다.");
				return manager3;
			default:
				System.out.println("     잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
		}
	}
	
	public void selectList1() {
        
        System.out.println("====================　홈　>　음식주문　>　메뉴 선택　>　밥　메뉴　선택　===================");
        System.out.println();
       
        Iterator iter = snackList1.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println();
		System.out.println("===================================================================================");
        System.out.print("     >> 메뉴선택 : ");
        
        int rice = sc.nextInt();
        System.out.println("     밥 메뉴의 " + rice + "번 "+ snackList1.get(rice - 1).getFood() +"을 선택하셨습니다.");    

//      OrderManager#map에 저장
        Snack snack = snackList1.get(rice - 1); // 선택한 음식 정보
        Map<Snack, Integer> map = manager3.getMap();

        if((manager3.map).containsKey(snack)) {
            // 이미 선택한 메뉴를 추가 주문하는 경우
            int count = map.get(snack);
            map.put(snack, count + 1);
        }
        else {
            map.put(snack, 1);
        }
        SnackBarOrderManager.snackTotal += snackList1.get(rice - 1).getPrice();
    }
		
	public void selectList2() {
        System.out.println("===================　홈　>　음식주문　>　메뉴 선택　>　라면 메뉴　선택　===================");
        System.out.println();
       
        Iterator iter = snackList2.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println();
        System.out.println("===================================================================================");
        System.out.print("     >> 메뉴선택 : ");
		
		int noodle = sc.nextInt();
		System.out.println("     라면 메뉴의 " + noodle + "번 " + snackList2.get(noodle - 1).getFood() + "을 선택하셨습니다.");

//      OrderManager#map에 저장
		Snack snack = snackList2.get(noodle - 1); // 선택한 음식 정보
		Map<Snack, Integer> map1 = manager3.getMap();

		if((manager3.map1).containsKey(snack)) {
			// 이미 선택한 메뉴를 추가 주문하는 경우
			int count = map1.get(snack);
			map1.put(snack, count + 1);
		}
		else {
			map1.put(snack, 1);
		}
		SnackBarOrderManager.snackTotal += snackList2.get(noodle- 1).getPrice();
	}
	
	public void selectList3() {
        System.out.println("===================　홈　>　음식주문　>　메뉴 선택　>　토핑　메뉴　선택　==================");
        System.out.println();
       
        Iterator iter = snackList3.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println();
        System.out.println("===================================================================================");
        System.out.print("     >> 메뉴선택 : ");
		
		int topping = sc.nextInt();
		System.out.println("     토핑 메뉴의 " + topping + "번 " + snackList3.get(topping - 1).getFood() + "을 선택하셨습니다.");

//      OrderManager#map에 저장
		Snack snack = snackList3.get(topping - 1); // 선택한 음식 정보
		Map<Snack, Integer> map2 = manager3.getMap();

		if((manager3.map2).containsKey(snack)) {
			// 이미 선택한 메뉴를 추가 주문하는 경우
			int count = map2.get(snack);
			map2.put(snack, count + 1);
		}
		else {
			map2.put(snack, 1);
		}
		SnackBarOrderManager.snackTotal += snackList3.get(topping- 1).getPrice();
	}
	
	public void selectList4() {
		System.out.println("=================　홈　>　음식주문　>　메뉴 선택　>　제조음료　메뉴　선택　=================");
        System.out.println();
       
        Iterator iter = snackList4.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println();
        System.out.println("===================================================================================");
        System.out.print("     >> 메뉴선택 : ");
		
		int makeDrink = sc.nextInt();
		System.out.println("     제조음료 메뉴의 " + makeDrink + "번 " + snackList4.get(makeDrink - 1).getFood() + "을 선택하셨습니다.");

//      OrderManager#map에 저장
		Snack snack = snackList4.get(makeDrink - 1); // 선택한 음식 정보
		Map<Snack, Integer> map3 = manager3.getMap();

		if((manager3.map3).containsKey(snack)) {
			// 이미 선택한 메뉴를 추가 주문하는 경우
			int count = map3.get(snack);
			map3.put(snack, count + 1);
		}
		else {
			map3.put(snack, 1);
		}
		SnackBarOrderManager.snackTotal += snackList4.get(makeDrink- 1).getPrice();
	}
	
	public void selectList5() {
		System.out.println("==================　홈　>　음식주문　>　메뉴 선택　>　캔음료　메뉴　선택　==================");
        System.out.println();
       
        Iterator iter = snackList5.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println();
		System.out.println("===================================================================================");
        System.out.print("     >> 메뉴선택 : ");
		
		int canDrink = sc.nextInt();
		System.out.println("     캔음료 메뉴의 " + canDrink + "번 " + snackList5.get(canDrink - 1).getFood() + "을 선택하셨습니다.");

//      OrderManager#map에 저장
		Snack snack = snackList5.get(canDrink - 1); // 선택한 음식 정보
		Map<Snack, Integer> map4 = manager3.getMap();

		if((manager3.map4).containsKey(snack)) {
			// 이미 선택한 메뉴를 추가 주문하는 경우
			int count = map4.get(snack);
			map4.put(snack, count + 1);
		}
		else {
			map4.put(snack, 1);
		}
		SnackBarOrderManager.snackTotal += snackList5.get(canDrink- 1).getPrice();
	}
	
}
