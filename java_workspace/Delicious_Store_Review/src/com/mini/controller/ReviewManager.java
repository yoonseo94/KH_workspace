package com.mini.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.mini.vo.Review;
import com.mini.vo.ReviewComparator;
import com.mini.vo.SignUp;

public class ReviewManager {
	private Scanner sc = new Scanner(System.in);
	private List<SignUp> memberList = new ArrayList<>();
	private List<Review> reviewList = new ArrayList<>();
	
	private String[] areaArr = new String[] {"강남구", "성동구", "종로구", "용산구"};
	private String id;
	private String password;
	
	public void reviewMenu() {
		String menu = "=== 요기어때(맛집 리뷰) ===\n"
					+ "1. 회원가입\n"
					+ "2. 로그인\n"
					+ "0. 요기어때 종료\n";
		
		while(true) {
			System.out.print(menu);
			System.err.println("로그인 후 이용이 가능합니다.");
			System.out.print("----------------------\n"
							 + "> ");
			
			String choice = sc.next();
			
			// 메뉴 선택
			switch(choice) {
			case "1" : signUp(); break;
			case "2" : login(); break;
			case "0" : return;
			default : System.err.println("다시 입력해주세요.");
			}			
		}
	}
	
	//회원가입
	private void signUp() {
		
		// 기존 아이디 중복검사를 위해 저장된 SignUp List 불러오기
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("signUpArr.ser")))) {
			memberList = (List<SignUp>) ois.readObject();
		} catch(IOException | ClassNotFoundException e ) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("======= 회원가입 =======");
		
		// 아이디 
		outer :
		while(true) {
			id = getStrInput("아이디 : ");
			
			// 값 있을 시 중복 검사.
			for(int i = 0; i < memberList.size(); i++) {
				if(id.equals(memberList.get(i).getID())) {
					System.err.println("이미 사용중인 아이디입니다.");
					continue outer;
				}
			}
			break;
		}
		
		// 비밀번호
		while(true) {
			password = getStrInput("비밀번호(특수문자 1개이상 포함 8~12자리) : ");
			String password2 = getStrInput("비밀번호 확인 : ");
			
			// 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
			// 비민번호 조건 만족 못할 시
			if(password.length() < 8 || password.length() > 12 || password.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*") == true)
				System.err.println("비밀번호는 특수문자 포함하여 8자 이상 12자 사이어야 합니다.");
			// 비밀번호 동일하지 않을 시
			else if(!password.equals(password2)) {
				System.err.println("비밀번호를 다시 확인해주세요.");
			}
			else {
				String name = getStrInput("이름 : ");
				String phoneNum = getStrInput("전화번호 : ");
				memberList.add(new SignUp(id, password, name, phoneNum));
				
				try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("signUpArr.ser")))) {
					oos.writeObject(memberList);
				} catch(IOException e) {
					System.err.println(e.getMessage());
				}
				System.out.println("[" + name + "]님 가입을 축하드립니다!");
				System.out.println("----------------------");
				break;
			}
			
		}
		
	}
	
	// 사용자 입력값 받는 메소드
	private String getStrInput(String msg) {
		System.out.print(msg);
		return sc.next();        // nextLine() -> next() 수정
    }
	 
	//로그인
	private void login() {
		 
		// 로그인 시 아이디 있는지 확인하기위해 SignUp List 불러오기
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("signUpArr.ser")))) {
			memberList = (List<SignUp>) ois.readObject();
		} catch(IOException | ClassNotFoundException e ) {
			System.err.println(e.getMessage());
		}
		
		outer : 
		while(true) {
			System.out.println("======== 로그인 ========");
			id = getStrInput("ID : ");
			
			// 저장된 ID 없을 시
			for(int i = 0; i < memberList.size(); i++) {
				if(id.equals(memberList.get(i).getID()))
					break outer;					
			}
			System.err.println("등록되지 않은 ID입니다. 다시 입력해주세요.");
		}
		
		outer :
		while(true) {
			password = getStrInput("PassWord : ");
			for(int i = 0; i < memberList.size(); i++) {
				if(id.equals(memberList.get(i).getID()) && password.equals(memberList.get(i).getPassWord())) {
					// 내가 쓴 리뷰 확인을 위해 Review 객체에 ID 값 저장
					System.out.println("[" + memberList.get(i).getName() + "]님께서 로그인 하셨습니다.");
					System.out.println("-------------------");
					break outer;
				}
			}
			System.err.println("비밀번호가 틀렸습니다. 다시 입력해주세요.");
		}
		
		// 로그인 성공 시 subMenu() 실행
		subMenu();
	}
	
	 
	// subMenu
	private void subMenu() {
		String subMenu = "====== 요기어때 ======\n"
					   + "1. 리뷰 쓰러가기\n"
					   + "2. 리뷰 둘러보기\n"
					   + "3. 내가 쓴 리뷰\n"
					   + "0. 뒤로\n"
					   + "-------------------\n"
					   + "> ";
		while(true) {
			System.out.print(subMenu);
			String choice = sc.next();
			
			
			switch(choice) {
			case "1" : writeReview(); break;
			case "2" : readReview(); break;
			case "3" : checkReview(); break;
			case "0" : return;
			default : System.err.println("다시 입력해주세요.");
			}
			
		}
		
	}
	
	// 리뷰 쓰기
	private void writeReview() {
		readObjectReview();
		
			
		String msgAreaList = "===== 리뷰 쓰기 =====\n";
		for (int i = 0; i < areaArr.length; i++) {
			msgAreaList += (i+1) + ". " + areaArr[i] + '\n';
		}
		System.out.print(msgAreaList);
		System.out.println("------------------");
		
		// 지역선택
		int areaNumber = 0;
		while(true) {
			try {
				System.out.print("> 지역 선택 : ");
				areaNumber = sc.nextInt();
				sc.nextLine();
				break;
			} catch(Exception e) {
				System.err.println("다시 입력해주세요.");
				sc.nextLine();
			}
		}
		
		System.out.println("[" + areaArr[areaNumber-1] + "를 선택 하셨습니다.]");
		
		// 가게명 입력
		String storeName = getStrInput("> 가게명 : ");
		
		// 한줄평 입력
		sc.nextLine();
		System.out.print("> 한줄평 : ");
		String contents = sc.nextLine();
		
		// 별점 입력
		System.out.print("> 별점(5점만점) : ");
		int starScore = sc.nextInt();
		
		System.out.println("> 저장 완료!");
		
		Review review = new Review(areaArr[areaNumber-1], contents, storeName, starScore, id);
		reviewList.add(review);
		
		// 리뷰리스트 저장
		writeObjectReview();
	}
	
	
	// 리뷰 둘러보기
	private void readReview() {
		String menu = "===== 지역 선택 =====\n"
					+ "1. 강남구\n"
					+ "2. 성동구\n"
					+ "3. 종로구\n"
					+ "4. 용산구\n"
					+ "0. 뒤로\n"
					+ "------------------\n"
					+ "> ";
		
		// 저장된 Review 객체정보 list로 불러오기 
		readObjectReview();
		
		// switch문에서 메소드 반환전까지 무한반복
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			// 사용자 입력값에 따라 지역별 출력
			// printByArea() 실행
			switch(choice) {
			case "1" : printByArea(areaArr[0]); break;
			case "2" : printByArea(areaArr[1]); break;
			case "3" : printByArea(areaArr[2]); break;
			case "4" : printByArea(areaArr[3]); break;
			case "0" : return;
			default : System.err.println("다시 입력해주세요.");
			}
		}
	}
	
	// 지역별 리뷰 출력
	private void printByArea(String str) {
		String subMenu = "===== 정렬 선택 =====\n"
				   	   + "1. 별점순\n"
				   	   + "2. 추천순\n"
				   	   + "3. 별?점 이상만\n"
				   	   + "-------------------\n"
				   	   + "> ";
		
		// reviewList 정렬 선택
		while(true) {
			System.out.print(subMenu);
			String subChoice = sc.next();
			
			switch(subChoice) {
			case "1" : Collections.sort(reviewList); break;
			case "2" : Collections.sort(reviewList, new ReviewComparator()); break;
			case "3" : Starview(str); return;
			default : System.err.println("다시 입력해주세요."); continue;
			}
			break;
		}
			
		while(true) {
			System.out.println("[" + str + " 맛집 리스트]");
			
			for(int i = 0; i < reviewList.size(); i++) {
				if(str.equals(reviewList.get(i).getArea())) {
					System.out.println(reviewList.get(i));
				}
			}
			System.out.println("------------------");
			
			String menu = "1. 추천\n"
						+ "0. 뒤로\n"
						+ "------------------\n"
						+ "> ";
			System.out.print(menu);
			String choice = sc.next();
			switch(choice) {
			case "1" : recomand(); break;
			case "0" : return;
			default : System.err.println("다시 입력해주세요.");
			}
			
		}
		
	}
	
	// 추천하기
	private void recomand() {
		// list 불러오기
//		readObjectReview();
		
		System.out.println("----------------------");
		System.out.print("> 추천할 가게명 : ");
		String storeName = sc.next();
		
		boolean flag = true;
		for(int i = 0; i < reviewList.size(); i++) {
			if(storeName.equals(reviewList.get(i).getStoreName())) {
				reviewList.get(i).setRecomandCnt(reviewList.get(i).getRecomandCnt() + 1);
				flag = false;
			}
		}
		if(flag) {
			System.err.println("해당 가게명이 존재하지 않습니다.");
			return;
		}
		System.out.println("추천 완료!");
		Collections.sort(reviewList, new ReviewComparator());
		
		// 다시 list 저장
		writeObjectReview();
		
	}
	
	// 특정 별점 이상 리스트 
	private void Starview(String str) {
		System.out.print("> 별점 ?점 이상 : ");
		
		// fall through
		String starNum = sc.next();
		System.out.println("=== "+starNum +"점 이상 리뷰 ===");
		switch(starNum) {
		case "1" : getStarStore(1, str);
		case "2" : getStarStore(2, str);
		case "3" : getStarStore(3, str);
		case "4" : getStarStore(4, str);
		case "5" : getStarStore(5, str); break;
		default : System.err.println("다시 입력해주세요");
		return;
		}
	}
	
	private void getStarStore(int num, String str) {
		for(int i = 0; i < reviewList.size(); i++) {
			if(str.equals(reviewList.get(i).getArea()) && reviewList.get(i).getStarScore() == num) {
				System.out.println(reviewList.get(i));
			}
		}
	}
	
	// 내가 쓴 리뷰
	private void checkReview() {
		readObjectReview();
		
		while(true) {
			System.out.println("===== 내가 쓴 맛집 리뷰 =====");
			for(int j = 0; j < areaArr.length; j++) {
				printMyReview(areaArr[j]);
			}
			System.out.println("------------------------");
			System.out.print("1. 추가\n"
						   + "2. 삭제\n"
						   + "0. 뒤로\n"
						   + "------------------------\n"
						   + "> ");
			String choice = sc.next();
			switch(choice) {
			case "1" : writeReview(); break;
			case "2" : deleteReview(); break;
			case "0" : return;
			default : System.err.println("다시 입력해주세요.");
			}
		}	
		
	}
	
	private void printMyReview(String str) {
		System.out.println("<" + str + ">----------------");
		for(int i = 0; i < reviewList.size(); i++) {
			if(str.equals(reviewList.get(i).getArea()) && reviewList.get(i).getId().equals(id))
				System.out.println(reviewList.get(i));
		}
	}
	
	// 리뷰 삭제
	private void deleteReview() {
		System.out.println("--------------------------");
		System.out.print("> 삭제할 가게명 : ");
		String storeName = sc.next();
		
		boolean flag = true;
		for(int i = 0; i < reviewList.size(); i++) {
			if(storeName.equals(reviewList.get(i).getStoreName())) {
				reviewList.remove(i);
				flag = false;
			}
		}
		if(flag) {
			System.err.println("해당 가게명이 존재하지 않습니다.");
			return;
		}
		System.out.println("삭제 완료!");
		
		// 다시 list 저장
		writeObjectReview();
		
	}
	
	// 저장된 리뷰객체 불러오기
	private void readObjectReview() {
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("reviewArr.ser")))) {
			reviewList = (List<Review>) ois.readObject();
		} catch(IOException | ClassNotFoundException e ) {
			System.err.println(e.getMessage());
		}
	}
	// 리뷰객체 저장하기
	private void writeObjectReview() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("reviewArr.ser")))) {
			oos.writeObject(reviewList);
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
