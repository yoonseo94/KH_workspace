package kh.java.object.array.friend;

import java.util.Scanner;

public class FriendManager {
	private Scanner sc = new Scanner(System.in);
	private static final int LENGTH = 3;
	private Friend[] friends = new Friend[LENGTH];
	private int index = 0;
	
	public void menu() {
		String menu = "--- 친구관리 ---\n"
					+ "1. 친구등록\n"
					+ "2. 친구목록 출력\n"
					+ "0. 종료\n"
					+ "-------------\n"
					+ "> ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			switch(choice) {
			case "1": inputFriends(); break;
			case "2": printFriends(); break;
			case "0": return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
		
	}
	
	public void insertFriend(Friend friend) {
		friends[index++] = friend;
	}
	
	public void inputFriends() {
		if(index == LENGTH)
			System.out.println("모든 친구를 입력했습니다.");
		else { 
			while(true) {
				// 친구 입력
				System.out.print("친구 이름은 입력하세요 > ");
				String name = sc.next();
				Friend f = new Friend(name);
				insertFriend(f); // 배열에 등록

				if(index < LENGTH) {
					// 입력완료 여부
					System.out.print("더 입력하시겠습니까?(y/n) > ");
					char yn = sc.next().charAt(0);
					if(yn == 'n')
						break;
				}
				else 
					break;
			}
			System.out.println("> 입력 완료!");
		}
	}
	
	public void printFriends() {
		// friends.length가 아닌 index로 지정해야 입력한 만큼만 출력가능하다.
		for(int i = 0; i < index; i++) {
			Friend f = friends[i];
			System.out.println(i + " : " + f.getFriendInfo());
		}
	}
}
