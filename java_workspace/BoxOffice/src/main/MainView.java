package main;

import java.util.Scanner;

import movie.MemberManager;
import movie.MovieMain;

public class MainView {
	
	public static void main(String[] args) {
		MemberManager manager = new MemberManager(100);
		MovieMain movie = new MovieMain();
		
		Scanner sc = new Scanner(System.in);
		String loginMenu = "╒◖═══════════════════════════════════◗╕\n"
						+ "\n\t⁂ KH BOXOFFICE ⁂\t\n\n"
			            + "\t ➊ 회원가입\n"
			            + "\t ➋ 로그인\n"
			            + "\t ⓿ 종료\n\n"
			            + "╘◖═══════════════════════════════════◗╛\n"
			            + " 원하시는 메뉴의 번호를 입력하세요 ➪\n";
	
		while(true) {
			
			System.out.println(loginMenu);
			int selected = sc.nextInt();
			
			switch(selected) {
			case 1 : manager.memberJoin(); break;
			case 2 : manager.memberLogin(); movie.kiosk(); break;
			case 0 : System.out.printf("정상적으로 종료되었습니다. \nKH BOXOFFICE를 이용해주셔서 감사합니다."); return;
			default : System.out.println("잘못 된 입력입니다. 다시 입력해주세요."); continue;
			}
		}
	}
}
