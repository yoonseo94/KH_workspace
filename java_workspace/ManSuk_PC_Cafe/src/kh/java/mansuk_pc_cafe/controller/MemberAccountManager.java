package kh.java.mansuk_pc_cafe.controller;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import kh.java.mansuk_pc_cafe.vo.MemberAccount;

/**
 * 멤버 계정 관리 클래스
 * 	- 회원가입
 * 	- 로그인
 * 	- 회원정보 변경
 * 
 * @author 수진
 */
@SuppressWarnings("unchecked")
public class MemberAccountManager {
	
	private Scanner sc = new Scanner(System.in);
	
	private ArrayList<MemberAccount> memberList = new ArrayList<>();
	private ArrayList<MemberAccount> searchList = new ArrayList<>();
	
	private SnackBarMenu sbMenu = new SnackBarMenu();
	
	static MemberAccount loginMember = null;
	
	private static final int SEAT_NUM = 10;
	private Object[][] seats = new Object[SEAT_NUM][SEAT_NUM]; // 좌석 표시 배열
	
	private String choice;
	
	{
		try {
			ObjectInputStream ois = null;
			ois = DataManager.loadMember("memberList.ser");			
			memberList = (ArrayList<MemberAccount>) ois.readObject();
			ois.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MemberAccount getLoginMember() {
		return loginMember;
	}
	
	public void mainView() {
		System.out.print("===================================================================================\r\n"
					   + "                                                                                   \r\n"
					   + "     ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓     \r\n"
					   + "     ┃                                                                       ┃     \r\n"
					   + "     ┃     ┏━━━━━━━━━┓  ┃    ┃            ┃    ┏━━━━━━━━━┓    ┏━━━━━━━━━     ┃     \r\n"
					   + "     ┃     ┃         ┃  ┃    ┃      ━━━━━━┫    ┃         ┃    ┃              ┃     \r\n"
					   + "     ┃     ┃         ┃  ┣━━  ┣━━━━━━      ┃    ┃         ┃    ┃              ┃     \r\n"
					   + "     ┃     ┃         ┃  ┃    ┃            ┃    ┣━━━━━━━━━┛    ┃              ┃     \r\n"
					   + "     ┃     ┗━━━━━━━━━┛  ┃        ━━━━━━━━━┓    ┃              ┃              ┃     \r\n"
					   + "     ┃        ┃                           ┃    ┃              ┃              ┃     \r\n"
					   + "     ┃        ┗━━━━━━━━━━                 ┃    ┃              ┗━━━━━━━━━     ┃     \r\n"
					   + "     ┃                                                                       ┃     \r\n"
					   + "     ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛     \r\n"
					   + "																					 \r\n"
					   + "                         💻 만석 PC방에 오신 것을 환영합니다! 💻                         \r\n"
					   + "																					 \r\n"
					   + "===================================================================================\r\n"
					   + "     1. 로그인\r\n"
					   + "     2. 회원 가입\r\n"
					   + "     3. 아이디/비밀번호 찾기\r\n"
					   + "     4. 비회원 로그인\r\n"
					   + "     0. PC 종료\r\n"
					   + "===================================================================================\r\n"
					   + "     >> 메뉴선택 : ");
	}
	
	public void mainMenu() {
		
		while(true) {
			
			mainView();
			choice = sc.next();
			
			switch(choice) {
			case "1":
				memberLogin();
				break;
			case "2":
				memberJoin();
				break;
			case "3":
				findIdPwdMenu();
				break;
			case "4":
				nonMember();
				break;
			case "0":
				System.out.println("     PC를 종료합니다.");
				System.out.println("     이용해주셔서 감사합니다.");
				return;
			default:
				System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
	/*
	 * 회원가입
	 */
	public void memberJoin() {
		while(true) {
			MemberAccount tmpMember = InputMember();
			System.out.println(tmpMember);
			
			System.out.println("===================================================================================");
			System.out.println("     입력한 정보가 맞습니까? (y/n)");
			System.out.println("       ※ n을 누르시면 메인 화면으로 돌아갑니다.");
			System.out.println("===================================================================================");
			System.out.print("     >> 입력 : ");
			char yn = sc.next().toLowerCase().charAt(0);
			
			if(yn == 'y') {
				System.out.println("\n     " + tmpMember.getId() + "님 가입을 축하드립니다.\n");
				memberList.add(tmpMember);
				DataManager.saveMember("memberList.ser", memberList);
				mainMenu();
			}
			else if(yn == 'n') {
				System.out.println("\n     메인 메뉴로 돌아갑니다.\n");
				return;
			}
			else
				System.out.println("\n     잘못 입력하셨습니다.\n");
		}
	}
	
	/*
	 * 회원 가입 정보 입력
	 */
	public MemberAccount InputMember() {
		
		System.out.println("=================================　홈　>　회원 가입　=================================");
		
		String name = inputName();
		int age = inputAge();
		char gender = inputGender();
		String id = inputId();
		String pwd = inputPwd();
		String phone = inputPhone();
		String email = inputEmail();
			
		System.out.println("===================================================================================");
			
		
		MemberAccount member = new MemberAccount(name, age, gender, id, pwd, phone, email);
		return member;
	}
	
	/*
	 * 이름 입력
	 */
	public String inputName() {
		while(true) {
			System.out.print("     이름 입력 : ");
			String name = sc.next();
			
			if(checkName(name))
				return name;
		}
	}
	
	/*
	 * 이름 유효성 검사
	 */
	public boolean checkName(String name) {
		if(!Pattern.matches("^[ㄱ-ㅎ가-힣]*$", name)) {
			System.out.println("       ※ 이름은 한글로 입력해주세요.");
			return false;
		}
		return true;
	}
	
	/*
	 * 나이 입력
	 */
	public int inputAge() {
		while(true) {
			System.out.print("     나이 입력 : ");
			int age = sc.nextInt();
			
			if(checkAge(age))
				return age;
		}
	}
	
	/*
	 * 나이 유효성 검사
	 */
	public boolean checkAge(int age) {
		if(age < 14) {
			System.out.println("       ※ 14세 미만은 가입이 불가능합니다.");
			return false;
		}
		return true;
	}
	
	/*
	 * 성별 입력
	 */
	public char inputGender() {
		while(true) {
			System.out.print("     성별 입력 : ");
			char gender = sc.next().toLowerCase().charAt(0);
			
			if(checkGender(gender))
				return gender;
		}
	}
	
	/*
	 * 성별 유효성 검사
	 */
	public boolean checkGender(char gender) {
		if(!(gender == '남' || gender == '여')) {
			System.out.println("       ※ '남' 또는 '여'로 성별을 입력해주세요.");
			return false;
		}
		return true;
	}
	
	/*
	 * 아이디 입력
	 */
	public String inputId() {
		while(true) {
			System.out.print("     아이디 입력 : ");
			String id = sc.next();
			
			if(checkId(id))
				return id;
		}
	}
	
	/*
	 * 아이디 유효성 검사
	 */
	public boolean checkId(String id) {
		if(id.length() < 5 || id.length() > 15) {
			System.out.println("       ※ 5 ~ 15자 이내의 아이디만 가능합니다.");
			return false;
		}
		
		int engCnt = 0;
		int numCnt = 0;
		
		for(int i = 0; i < id.length(); i++) {
			char ch = id.charAt(i);
			
			if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
				engCnt++;
			else if(ch >= '0' && ch <= '9')
				numCnt++;
		}

		if(engCnt == 0 || numCnt == 0) {
			System.out.println("       ※ 아이디는 영문자와 숫자를 혼용해서 만들어주세요.");	 
			return false;
		}
			
		MemberAccount tmp = findId(id);
		if(tmp != null) {
			System.out.println("       ※ 이미 사용중이거나 탈퇴한 아이디입니다.");
			return false;
		}
		else {
			return true;
		}
	}
	
	/*
	 * 비밀번호 입력
	 */
	public String inputPwd() {
		while(true) {
			System.out.print("     비밀번호 입력 : ");
			String pwd = sc.next();
			System.out.print("     비밀번호 확인 : ");
			String pwd2 = sc.next();
			
			if(checkPwd(pwd, pwd2))
				return pwd;
		}
	}
	
	/*
	 * 비밀번호 유효성 검사
	 */
	public boolean checkPwd(String pwd, String pwd2) {
		if(!pwd.equals(pwd2)) {
			System.out.println("       ※ 비밀번호가 일치하지 않습니다.");
			return false;
		}
		
		boolean check = Pattern.matches("^[A-Za-z[0-9]]{5,12}$", pwd);
		if(!check) {
			System.out.println("       ※ 비밀번호는 영문, 숫자가 포함된 5~12자의 문자열이며, 공백 없이 입력해주세요.");
			return false;
		}
		return true;
	}

	/*
	 * 비밀번호 암호화
	 */
	public String encrypt(String pwd) {
		String enPwd = "";
		
		for(int i = 0; i < pwd.length(); i++) {
			enPwd += (char)(pwd.charAt(i) * 3);
		}
		
		return enPwd;
	}
	
	/*
	 * 비밀번호 복호화
	 */
	public String decrypt(String enPwd) {
		String dePwd = "";
		
		for(int i = 0; i < enPwd.length(); i++) {
			dePwd += (char)(enPwd.charAt(i) / 3);
		}
		
		return dePwd;
	}
	
	/*
	 * 전화번호 입력
	 */
	public String inputPhone() {
		while(true) {
			System.out.print("     전화번호 입력 : ");
			String phone = sc.next();
			
			if(checkPhone(phone))
				return phone;
		}
	}
	
	/*
	 * 전화번호 유효성 검사
	 */
	public boolean checkPhone(String phone) {
		boolean check = Pattern.matches(
				"(010|011|016|017|018?019)(\\d{3,4})(\\d{4})", phone);

		if(!check) {
			System.out.println("       ※ 전화번호는 공백 없이 10 ~ 11자리 입력해주세요.");
			return false;
		}
		return true;
	}
	
	/*
	 * 이메일 입력
	 */
	public String inputEmail() {
		while(true) {
			System.out.print("     이메일 입력 : ");
			String email = sc.next();
			
			if(checkEmail(email))
				return email;
		}
	}
	
	/*
	 * 이메일 유효성 검사
	 */
	public boolean checkEmail(String email) {
		Boolean check = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);
		
		if(!check) {
			System.out.println("       ※ 올바른 이메일 형식을 입력하세요.");
			return false;
		}
		return true;
	}
	
	/*
	 * 이용자 메인 메뉴 화면
	 */
	public void userMainView() {
		System.out.println();
		System.out.print("====================================　이용자　홈　====================================\r\n" 
						+ "     1. 회원정보 변경\r\n"
						+ "     2. 좌석 선택\r\n"
						+ "     3. 이용권 등록\r\n"
						+ "     4. 음식주문\r\n"
						+ "     0. 로그아웃(메인화면 이동)\r\n"
						+ "===================================================================================\r\n"
						+ "     >> 메뉴선택 : ");
	}
	
	/*
	 * 이용자 회원정보 변경 메뉴 화면
	 */
	public void userAccountView() {
		System.out.println();
		System.out.print("================================　홈　>　회원정보　변경　===============================\r\n" 
						+ "     1. 비밀번호 변경\r\n"
						+ "     2. 전화번호 변경\r\n"
						+ "     3. 이메일 변경\r\n"
						+ "     0. 돌아가기\r\n"
						+ "===================================================================================\r\n"
						+ "     >> 메뉴선택 : ");
	}
	
	/*
	 * 이용자 메인 메뉴 선택
	 */
	public void userMainMenu() {
		
		while(true) {
			
			userMainView();
			choice = sc.next();
			
			switch(choice) {
			case "1":
				changeInfo();
				break;
			case "2":
				seatSelect();
				break;
			case "3":
				new FlatBillOrderController().flatBillOrder();
				break;
			case "4":
				sbMenu.snackMenu();
				break;
			case "0":
				DataManager.saveMember("memberList.ser", memberList);
				System.out.println("     로그아웃합니다.");
				mainMenu();
				break;
			default:
				System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
	/*
	 * 로그인
	 */
	public void memberLogin() {
		
		while(true) {
			System.out.println("==================================　홈　>　로그인　==================================");
			System.out.print("     아이디 입력 : ");
			String id = sc.next();
			System.out.print("     비밀번호 입력 : ");
			String pwd = sc.next();
			System.out.println("===================================================================================");
			
			if(findIdPwd(id, pwd) != null) {
				loginMember = findIdPwd(id, pwd);
				if("admin1".equals(id) && "admin1234".equals(pwd)) {
					System.out.println("     관리자 모드입니다.");
					adminMainMenu();
					break;
				} else {
					if(loginMember.getRemnants() > 0) {
						if(!loginMember.isState())
							loginMember.setState(true);
					}
					
					System.out.printf("     로그인 성공! ID[%s]님. 접속을 환영합니다.%n", loginMember.getId());
					System.out.println("\n     " + loginMember);
					userMainMenu();
				}
			}
			else if(loginMember == null) {
				System.out.println("     등록된 계정이 없습니다. 회원가입을 먼저 진행해주세요.");
				mainMenu();
				break;
			}
		}
	}
	
	/*
	 * 이용자 아이디/비밀번호 찾기 메뉴 화면 
	 */
	public void findIdPwdView() {
		System.out.println();
		System.out.print("============================　홈　>　아이디/비밀번호　찾기　============================\r\n"
					   + "     1. 아이디 찾기\r\n"
					   + "     2. 비밀번호 찾기\r\n"
					   + "     0. 돌아가기\r\n"
					   + "===================================================================================\r\n"
					   + "     >> 메뉴선택 : ");
	}
	
	/*
	 * 이용자 아이디/비밀번호 찾기 메뉴
	 */
	public void findIdPwdMenu() {
		while(true) {
			
			findIdPwdView();
			choice = sc.next();
			
			switch(choice) {
			case "1":
				findId();
				break;
			case "2":
				findPwd();
				break;
			case "0":
				mainMenu();
				break;
			default: System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
	/*
	 * 이용자 아이디 찾기
	 */
	public void findId() {
		
		while(true) {
			System.out.println("=====================　홈　>　아이디/비밀번호　찾기　>　아이디　찾기　=====================");
			System.out.print("     전화번호 입력 : ");
			String phone = sc.next();
			System.out.println("===================================================================================");
			
			for(int i = 0; i < memberList.size(); i++) {
				if(phone.equals(memberList.get(i).getPhone())) {
					System.out.println("     당신의 아이디는 [" + memberList.get(i).getId() + "]입니다.");
					return;
				}
				else {
					System.out.println("     찾으시는 아이디가 없습니다.");
					return;
				}
			}
		}
	}
	
	/*
	 * 아이디 찾기2
	 */
	public MemberAccount findId(String id) {
		for(int i = 0; i < memberList.size(); i++) {
			if(id.equals(memberList.get(i).getId()))
				return memberList.get(i);
		}
		return null;
	}
	
	/*
	 * 아이디/비밀번호 찾기
	 */
	public MemberAccount findIdPwd(String id, String pwd) {
		if(findId(id) != null) {
			for(int i = 0; i < memberList.size(); i++) {
				if(id.equals(memberList.get(i).getId()) &&
				   pwd.equals(memberList.get(i).getPwd()))
					return memberList.get(i);
			}
		}
		return null;
	}
	
	/*
	 * 이용자 비밀번호 찾기
	 */
	public void findPwd() {
		
		while(true) {
			System.out.println("====================　홈　>　아이디/비밀번호　찾기　>　비밀번호　찾기　====================");
			System.out.print("     아이디 입력 : ");
			String id = sc.next();
			System.out.print("     이메일 입력 : ");
			String email = sc.next();
			System.out.println("===================================================================================");
			
			for(int i = 0; i < memberList.size(); i++) {
				if(id.equals(memberList.get(i).getId()) &&
				   email.equals(memberList.get(i).getEmail())) {
					System.out.println("     당신의 비밀번호는 [" + memberList.get(i).getPwd() + "]입니다.");
					return;
				}
				else {
					System.out.println("     찾으시는 계정이 없습니다.");
					return;
				}
			}
		}
	}
	
	/*
	 * 비회원 로그인
	 */
	public void nonMember() {
		String phone = inputPhone();
		loginMember = new MemberAccount(phone);
		String tmpNo = loginMember.getMemberNo() + loginMember.getPhone();
		System.out.println("===============================　홈　>　비회원　로그인　===============================");
		System.out.println("     임시번호 : " + tmpNo);
		System.out.println("===================================================================================");
		
		while(true) {
			System.out.print("     임시번호 입력 : ");
			String inputTmpNo = sc.next();
			if(inputTmpNo.equals(tmpNo)) {
				userMainMenu();
				break;
			}
			else
				System.out.println("     잘못 입력하셨습니다.");
		}
	}
	
	/*
	 * 관리자 메인 메뉴 화면
	 */
	public void adminMainView() {
		System.out.println();
		System.out.print("===================================　관리자　홈　====================================\r\n"
					   + "     1. 회원정보관리\t0. 종료\r\n"
					   + "===================================================================================\r\n"
					   + "     >> 메뉴선택 : ");
	}
	
	/*
	 * 관리자 회원정보관리 메뉴 화면
	 */
	public void adminAccountView() {
		System.out.println();
		System.out.print("================================　홈　>　회원정보관리　================================\r\n"
					   + "     1. 회원목록 출력\r\n"
					   + "     2. 회원정보 검색\r\n"
					   + "     3. 회원정보 수정\r\n"
					   + "     4. 회원정보 삭제\r\n"
					   + "     5. 돌아가기\r\n"
					   + "     0. 종료\r\n"
					   + "===================================================================================\r\n"
					   + "     >> 메뉴선택 : ");
	}
	
	/*
	 * 관리자 회원정보관리 메뉴
	 */
	public void AdminAccountMenu() {
		
		while(true) {
			
			adminAccountView();
			choice = sc.next();
			
			switch(choice) {
			case "1":
				printMember();
				break;
			case "2":
				searchMember();
				break;
			case "3":
				changeData();
				break;
			case "4":
				deleteMember();
				break;
			case "5":
				mainMenu();
				break;
			case "0":
				System.out.println("     시스템을 종료합니다.");
				break;
			default:
				System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
	/*
	 * 관리자 메인 메뉴
	 */
	public void adminMainMenu() {
		while(true) {
			
			adminMainView();
			choice = sc.next();
			
			switch(choice) {
			case "1":
				AdminAccountMenu();
				break;
			case "0":
				System.out.println("     시스템을 종료합니다.");
				return;
			default:
				System.out.println("     잘못 입력하셨습니다.");			
			}
		}
	}
	
	/*
	 * 관리자용 회원목록 출력
	 */
	public void printMember() {
		
		while(true) {
			System.out.println();
			System.out.print("========================　홈　>　회원정보관리　>　회원정보　출력　========================\r\n"
						   + "     1. 출력\t0. 돌아가기\r\n"
						   + "===================================================================================\r\n"
						   + "     >> 메뉴선택 : ");
			
			choice = sc.next();
			
			switch(choice) {
			case "1":
				Iterator iter = memberList.iterator();
				while(iter.hasNext()){
					System.out.println(iter.next());
				}
				break;
			case "0":
				return;
			default:
				System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
	/*
	 * 관리자용 회원정보 삭제
	 */
	public void deleteMember() {
		while(true) {
			System.out.print("========================　홈　>　회원정보관리　>　회원정보　삭제　========================\r\n"
						   + "     1. 삭제\t0. 돌아가기\r\n"
						   + "===================================================================================\r\n"
						   + "     >> 메뉴선택 : ");
		
			choice = sc.next();
			
			switch(choice) {
			case "1":
				System.out.print("\n     삭제할 회원번호 입력 : ");
				int index = sc.nextInt();
				memberList.remove(index);
				DataManager.saveMember("memberList.ser", memberList);
				System.out.println(memberList);
				System.out.println("     삭제가 완료되었습니다.");
				return;
			case "0":
				return;
			default: System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
	/*
	 * 회원정보 인덱스 찾기
	 */
	public int searchMember(String name, String id) {
		for(int i = 0; i < memberList.size(); i++) {
			if(name.equals(memberList.get(i).getName()) &&
			   id.equals(memberList.get(i).getId()))
				return i;
		}
		return -1;
	}
	
	/*
	 * 관리자용 회원정보 수정
	 */
	public void changeData() {
		while(true) {
			System.out.print("========================　홈　>　회원정보관리　>　회원정보　수정　========================\r\n"
						   + "     1. 비밀번호 수정\r\n"
						   + "     2. 전화번호 수정\r\n"
						   + "     3. 이메일 수정\r\n"
						   + "     0. 돌아가기\r\n"
						   + "===================================================================================\r\n"
						   + "     >> 메뉴선택 : ");
			
			choice = sc.next();
			int inputNo = 0;
			
			switch(choice) {
			case "1":
				System.out.print("================　홈　>　회원정보관리　>　회원정보　수정　>　비밀번호　수정　================");
				System.out.print("\n     수정할 회원번호 입력 : ");
				inputNo = sc.nextInt();
				System.out.println("===================================================================================");
				if(inputNo != 1) {
					int index = findNo(inputNo);
					
					if(index == -1) {
						System.out.println("     회원정보를 찾을 수 없습니다.");
					}
					else {
						System.out.println("\n     회원 " + memberList.get(index).getId() + "님의 현재 비밀번호 : " 
											+ memberList.get(index).getPwd());
						System.out.println("===================================================================================");
						System.out.print("     수정할 비밀번호 입력 : ");
						String pwd = sc.next();
						System.out.print("     수정할 비밀번호 확인 : ");
						String pwd2 = sc.next();
						
						checkPwd(pwd, pwd2);
						memberList.get(index).setPwd(pwd);
						System.out.println("     수정이 완료되었습니다.");
						break;
					}
				}
				
			case "2":
				System.out.print("================　홈　>　회원정보관리　>　회원정보　수정　>　전화번호　수정　================");
				System.out.print("\n     수정할 회원번호 입력 : ");
				inputNo = sc.nextInt();
				System.out.println("===================================================================================");
				if(inputNo != 1) {
					int index = findNo(inputNo);
					
					if(index == -1) {
						System.out.println("     회원정보를 찾을 수 없습니다.");
					}
					else {
						System.out.println("\n     회원 " + memberList.get(index).getId() + "님의 현재 전화번호 : " 
											+ memberList.get(index).getPhone());
						System.out.println("===================================================================================");
						System.out.print("     수정할 전화번호 입력 : ");
						String phone = sc.next();
						
						checkPhone(phone);
						memberList.get(index).setPhone(phone);
						System.out.println("     수정이 완료되었습니다.");
						break;
					}
				}
			case "3":
				System.out.print("==================　홈　>　회원정보관리　>　회원정보　수정　>　이메일　수정　==================");
				System.out.print("\n     수정할 회원번호 입력 : ");
				inputNo = sc.nextInt();
				System.out.println("===================================================================================");
				if(inputNo != 1) {
					int index = findNo(inputNo);
					
					if(index == -1) {
						System.out.println("     회원정보를 찾을 수 없습니다.");
					}
					else {
						System.out.println("     회원 " + memberList.get(index).getId() + "님의 현재 이메일 : " 
								+ memberList.get(index).getEmail());
						System.out.println("===================================================================================");
						System.out.print("     수정할 이메일 입력 : ");
						String email = sc.next();
						
						checkEmail(email);
						memberList.get(index).setEmail(email);
						System.out.println("     수정이 완료되었습니다.");
						break;
					}
				}
			case "0":
				return;
			default:
				System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
	public int findNo(int no) {
		for(int i = 0; i < memberList.size(); i++) {
			if(no == memberList.get(i).getMemberNo()) {
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * 관리자용 회원정보 검색
	 */
	public void searchMember() {
		searchList.clear();
		
		System.out.println("========================　홈　>　회원정보관리　>　회원정보　검색　========================");
		System.out.print("     아이디 입력 : ");
		String id = sc.next();
		System.out.println("===================================================================================");
		
		for(int i = 0; i < memberList.size(); i++) {
			MemberAccount member = memberList.get(i);
			
			if(member.getId().indexOf(id) != -1)
				searchList.add(member);
		}
		
		if(searchList.size() > 0) {
			System.out.println(searchList);
		}
		else {
			System.out.println("     검색결과가 없습니다.");
		}
	}
	
	/*
	 * 회원용 개인정보 수정
	 */
	public void changeInfo() {
		
		if(!loginMember.isMemberYN()) {
			System.out.println("     비회원은 정보 수정이 불가합니다.");
			return;
		}
		
		while(true) {
			userAccountView();
			choice = sc.next();
			
			switch(choice) {
			case "1":
				System.out.println("     회원 " + loginMember.getId() + "님의 현재 비밀번호 : " + loginMember.getPwd());
				System.out.println("===================================================================================");
				System.out.print("     변경할 비밀번호 입력 : ");
				String pwd = sc.next();
				System.out.print("     변경할 비밀번호 확인 : ");
				String pwd2 = sc.next();
				
				checkPwd(pwd, pwd2);
				loginMember.setPwd(pwd);
				memberList.get(loginMember.getMemberNo()).setPwd(pwd);
				System.out.println("     수정이 완료되었습니다.");
				break;
			case "2":
				System.out.println("     회원 " + loginMember.getId() + "님의 현재 전화번호 : " + loginMember.getPhone());
				System.out.println("===================================================================================");
				System.out.print("     변경할 전화번호 입력 : ");
				String phone = sc.next();
				
				checkPhone(phone);
				loginMember.setPhone(phone);
				memberList.get(loginMember.getMemberNo()).setPhone(phone);
				System.out.println("     수정이 완료되었습니다.");
				break;
			case "3":
				System.out.println("     회원 " + loginMember.getId() + "님의 현재 이메일 : " + loginMember.getEmail());
				System.out.println("===================================================================================");
				System.out.print("     변경할 이메일 입력 : ");
				String email = sc.next();
				
				checkEmail(email);
				loginMember.setEmail(email);
				memberList.get(loginMember.getMemberNo()).setEmail(email);
				System.out.println("     수정이 완료되었습니다.");
				break;
			case "0":
				return;
			default:
				System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
	/*
	 * 좌석조회
	 */
	public void seatView() {
		int[][] viewSeats = new int[SEAT_NUM][SEAT_NUM];
		System.out.println("================================　홈　>　좌석　선택　=================================");
		System.out.println("     좌석 조회");
		int num = 1;

		for(int i = 0; i < viewSeats.length; i++) {
			for(int j = 0; j < viewSeats[i].length; j++) {
				viewSeats[i][j] = num++;
			}
		}
		
		for(int i = 0; i < viewSeats.length; i++) {
			for(int j = 0; j < viewSeats[i].length; j++) {
				System.out.printf("[%2s]", viewSeats[i][j]);
			}
			System.out.println();
		}
		System.out.println("===================================================================================");
	}
	
	/*
	 * 좌석 선택
	 */
	public void seatSelect() {
		while(true) {
			seatView();
			
			int num = 1;
			int i = 0;
			int j = 0;

			for (i = 0; i < seats.length; i++) {
				for (j = 0; j < seats[i].length; j++) {
					
					if(seats[i][j] == loginMember) {
						System.out.printf("     ID %s님. [%2s]번 사용 중입니다.%n", loginMember.getId(), (i * 10) + j + 1);
						System.out.println("===================================================================================");
						System.out.print("     좌석 이동하시겠습니까? (y/n) : ");
						
						char yn = sc.next().toLowerCase().charAt(0);
						if(yn == 'y') {
							seats[i][j] = null;
							loginMember.setSeatNum(-1);
							System.out.println("     자리 이동이 진행됩니다.\n");
						}
						else if(yn == 'n') {
							return;
						}
						break;
					}
				}
			}
			
			System.out.print("     좌석번호 입력 : ");
			int mySeat = sc.nextInt();
			int myRow = (mySeat % 10 == 0) ? mySeat / 10 - 1 : mySeat / 10;
			int myCol = (mySeat % 10 == 0) ? 9 : mySeat % 10 - 1;

			if(mySeat > 0 && mySeat < 101) {
				if(seats[myRow][myCol] == null) {
					seats[myRow][myCol] = loginMember;
					loginMember.setSeatNum(mySeat);
				    
					System.out.println("===================================================================================\n");
					for(i = 0; i < seats.length; i++) {
						for(j = 0; j < seats[i].length; j++) {
							System.out.printf("[%2s]", (seats[i][j] == null) ? (i * 10 + j + 1) : " ");
						}
							System.out.println();
					}
					System.out.println("\n===================================================================================");
						
					System.out.printf("     [%2s]번이 선택 완료되었습니다.", mySeat);
					break;
				}
				else {
					System.out.println("     이용중인 좌석입니다.");
				}
			}
			else {
				System.out.println("     잘못 입력하셨습니다.");
			}
		}
	}
	
}
