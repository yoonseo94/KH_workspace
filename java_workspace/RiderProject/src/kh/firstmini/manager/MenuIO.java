package kh.firstmini.manager;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kh.firstmini.vo.Menu;
import kh.firstmini.vo.Store;

public class MenuIO {

	public void printMenuOnly() {

		List<Menu> list = new ArrayList<>();
		list.add(new Menu("0000", "메가리카노", 3000));
		list.add(new Menu("0000", "카페라떼", 3500));
		list.add(new Menu("0000", "딸기라떼", 4000));
		list.add(new Menu("0000", "디카페인", 3000));
		list.add(new Menu("0000", "녹차라떼", 4200));
		list.add(new Menu("0000", "플레인요거트", 4200));

		list.add(new Menu("1111", "모카라떼", 5900));
		list.add(new Menu("1111", "헤이즈넛아메리카노", 5400));
		list.add(new Menu("1111", "바닐라라떼", 5900));
		list.add(new Menu("1111", "카페수아", 6400));
		list.add(new Menu("1111", "달고나크림라떼", 4900));
		list.add(new Menu("1111", "캐러멜마끼아또", 6400));

		list.add(new Menu("2222", "엽기떡볶이", 14000));
		list.add(new Menu("2222", "엽기닭볶음탕", 24000));
		list.add(new Menu("2222", "주먹김밥", 2000));
		list.add(new Menu("2222", "치즈추가", 3000));
		list.add(new Menu("2222", "모듬튀김세트", 15000));

		list.add(new Menu("3333", "1인용마라탕", 19500));
		list.add(new Menu("3333", "1인용부대마라탕", 18500));
		list.add(new Menu("3333", "분모자", 3000));
		list.add(new Menu("3333", "양고기", 5000));
		list.add(new Menu("3333", "고수", 3000));
		list.add(new Menu("3333", "칭따오", 5000));

		list.add(new Menu("4000", "교촌허니콤보", 20000));
		list.add(new Menu("4000", "레드콤보", 20000));
		list.add(new Menu("4000", "교촌웨지감자", 3500));

		list.add(new Menu("5555", "짜장면", 5000));
		list.add(new Menu("5555", "고추짬뽕", 7000));
		list.add(new Menu("5555", "탕수육", 13000));
		list.add(new Menu("5555", "군만두", 5500));
		list.add(new Menu("5555", "멘보샤", 7900));

		list.add(new Menu("6666", "고구마피자", 21000));
		list.add(new Menu("6666", "고르곤졸라피자", 21000));
		list.add(new Menu("6666", "베이컨체다치즈피자", 19000));
		list.add(new Menu("6666", "서울핫도그피자", 25500));

		list.add(new Menu("7777", "차돌양지쌀국수", 8900));
		list.add(new Menu("7777", "매운소고기쌀국수", 8900));
		list.add(new Menu("7777", "나시고랭볶음밥", 8500));
		list.add(new Menu("7777", "고기짜조", 6000));
		list.add(new Menu("7777", "고수", 1500));

		list.add(new Menu("8888", "닭가슴살스테이크샐러드", 8500));
		list.add(new Menu("8888", "바질파스타샐러드", 9000));
		list.add(new Menu("8888", "리코타치즈샐러드", 9000));
		list.add(new Menu("8888", "생연어과카몰리샐러드", 12000));
		list.add(new Menu("8888", "오렌지에이드", 4500));

		list.add(new Menu("9999", "먹태", 13000));
		list.add(new Menu("9999", "갈릭빠다포테이토", 6000));
		list.add(new Menu("9999", "반건조오징어", 9000));
		list.add(new Menu("9999", "생맥주1500", 10700));

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("allMenu.ser")))) {

			oos.writeObject(list);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printStoreOnly() {
		
		Map<String, Store> storeMap = new HashMap<>(); // key = storeID
		
		storeMap.put("0000", new Store("0000", "메가커피", "02-1111-3333", 2000, 3000, "삼성2동", "10:00~20:00"));
		storeMap.put("1111", new Store("1111", "커피빈", "02-1234-56783", 2500, 10000, "역삼2동", "10:00~20:00"));
		storeMap.put("2222", new Store("2222", "엽기떡볶이", "02-5555-7777", 3000, 14000, "역삼1동", "11:00~21:00"));
		storeMap.put("3333", new Store("3333", "1인용 마라탕", "02-456-8282", 2500, 3000, "압구정동", "11:00~23:59"));
		storeMap.put("4000", new Store("4000", "교촌치킨", "02-2222-3333", 3000, 16000, "역삼1호", "10:00~23:59"));
		storeMap.put("5555", new Store("5555", "홍콩반점", "02-8989-8989", 2500, 13000, "강남역", "11:30~20:00"));
		storeMap.put("6666", new Store("6666", "피자알보로", "02-5763-5763", 1000, 18000, "역삼직영", "10:00~21:00"));
		storeMap.put("7777", new Store("7777", "배달의쌀국수", "02-7358-8666", 2500, 7000, "역삼1동", "08:00~20:00"));
		storeMap.put("8888", new Store("8888", "샐러드박스", "02-8631-7865", 2900, 8000, "서초동", "09:50~20:00"));
		storeMap.put("9999", new Store("9999", "역전할머니맥주", "01-7358-8666", 3500, 16000, "신사동", "15:00~23:59"));
		
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("allStore.ser")))) {

			oos.writeObject(storeMap);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
