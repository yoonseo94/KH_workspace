package movie;

import java.util.Scanner;

public class MemberManager {

	private MemberVo[] memberArr;
	private int idx;
	public static MemberVo loginMember;
	
	public MemberManager(int num) {
		memberArr = new MemberVo[num];
		idx = 0;
	}

	// 회원가입
	public void memberJoin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("✐ 사용 ID 입력 : ");
		String id = sc.nextLine();
		if (!checkId(id)) {
			System.out.println("❌중복 된 ID는 사용할 수 없습니다.❌\n");
			return;
		}
		System.out.println("✐ 비밀번호 설정 :");
		String pw = sc.nextLine();
		System.out.println("✐ 이름 : ");
		String name = sc.nextLine();
		System.out.println("✐ 나이 : ");
		int age = sc.nextInt();

		memberArr[idx] = new MemberVo(id, pw, name, age);
		idx++;

		System.out.println("\t▒▒▒▒▒▒▒▒▒▒▒▒\n"
						  +"\t▒▒▒▒▓▒▒▓▒▒▒▒\n"
						  +"\t▒▒▒▒▓▒▒▓▒▒▒▒\n"
						  +"\t▒▒▒▒▒▒▒▒▒▒▒▒\n"
						  +"\t▒▓▒▒▒▒▒▒▒▒▓▒\n"
						  +"\t▒▒▓▓▓▓▓▓▓▓▒▒\n"
						  +"\t▒▒▒▒▒▒▒▒▒▒▒▒\n\t"
						  + name + "님 반갑습니다!\n가입이 정상적으로 완료되었습니다.\n");
	}

	// 아이디 중복여부
	private boolean checkId(String id) {
//		if (idx == 0)
//			return true;

		for (int i = 0; i < idx; i++) {
			if (memberArr[i].getId().equals(id)) {
				return false;
			}
		}
		return true;
	}

	// 로그인
	public void memberLogin() {
		final int MAX_LOGIN_ATTEMPT = 3; // 로그인 시도 횟수 제한
		int cnt = 0; 
		Scanner sc = new Scanner(System.in);

		while (cnt++ < MAX_LOGIN_ATTEMPT) {
			System.out.println("⌨ ID : ");
			String id = sc.nextLine();
			System.out.println("⌨ PASSWORD : ");
			String pw = sc.nextLine();

			// 아이디 일치하는 회원 가져오기
			MemberVo member = findMemberById(id);

			// 로그인 성공
			if (member != null && pw.equals(member.getPw())) {
				System.out.println("\t║║╔║║╔╗ ║\r\n"
					           	+  "\t╠╣╠║║║║ ║\r\n"
						        +  "\t║║╚╚╚╚╝ O\r\n");
				System.out.println("✧ ˖˚˳⊹ " + id +"님 환영합니다! ⊹˳˚˖ ✧");
				System.out.println("\t정상적으로 로그인되었습니다.\n");
				
				// 로그인 처리 
				loginMember = member;
				return;
			} else {
				System.out.println("❌ 아이디 또는 비밀번호가 일치하지 않습니다.(남은 횟수 " + (MAX_LOGIN_ATTEMPT - cnt) + "번)\n");
			} 
				
			}
		}
	

	private MemberVo findMemberById(String id) {
		for (int i = 0; i < idx; i++) {
			if (memberArr[i].getId().equals(id)) {
				return memberArr[i]; 
			}
		}
		return null;

	}
}
