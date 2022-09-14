package com.shqkel.test;

import java.util.Scanner;

public class LoginTest {

	Scanner sc = new Scanner(System.in);
	public static Member loginMember;		// 로그인한 회원 관리객체 
	public boolean finished = false; 		// 종료여부
	
	public static void main(String[] args) {
		new LoginTest().menu();
		System.out.println("> 이용해 주셔서 감사합니다.");
	}
	
	
	/**
	 * 로그인객체(loginMember) null이면 비회원메뉴를 제공한다.
	 * 로그인객체(loginMember) null이 아니면 회원메뉴를 제공한다.
	 * 두메뉴 모두 종료(0)를 누른 경우, finished를 true로 처리해서 종료한다. 
	 */
	private void menu() {
		do {
			if(loginMember == null)
				anonymousMenu();
			else 
				loginMenu();
		} while(!finished);
	}
	
	/**
	 * 비회원메뉴(로그인전)
	 */
	public void anonymousMenu() {
		String menu = "--- 비회원메뉴 ---\n"
					+ "1. 회원가입\n"
					+ "2. 로그인\n"
					+ "0. 종료\n"
					+ "---------------\n"
					+ "선택 : ";
		while(loginMember == null) {
			System.out.print(menu);
			String choice = sc.next();
			switch(choice) {
			case "1": break;
			case "2": memberLogin(); break;
			case "0": finished = true; return;
			}
		}
	}
	
	/**
	 * 회원메뉴(로그인후)
	 */
	public void loginMenu() {
		String menu = "--- 회원메뉴 ---\n"
					+ "1. 로그아웃\n"
					+ "0. 종료\n"
					+ "---------------\n"
					+ "선택 : ";
		while(loginMember != null) {
			System.out.print(menu);
			String choice = sc.next();
			switch(choice) {
			case "1": loginMember = null; break;
			case "0": finished = true; return;
			}
		}
	}

	private void memberLogin() {
		final int MAX_LOGIN_ATTEMPT = 3;
		int cnt = 0;
		while(cnt++ < MAX_LOGIN_ATTEMPT) {
			// 아이디/비번 입력
			System.out.print("아이디 : ");
			String id = sc.next(); 
			System.out.print("비밀번호 : ");
			String password = sc.next();
			
			// 아이디가 일치하는 회원 가져오기
			Member member = findMemberById(id);
			
			// 로그인 성공
			if(member != null && password.equals(member.getPassword())) {
				loginMember = member;
				return;
			}
			// 로그인 실패 - 아이디 또는 비밀번호가 일치하지 않는 경우
			else {
				
			}
		}
		loginMember = new Member(); // 테스트코드 - 임의로 로그인 처리
		System.out.println("> 다음 기회에 다시 로그인해주세요!");
	}

	private Member findMemberById(String id) {
		// member목록에서 아이디로 회원 조회하기
		return null;
	}

}
