package kh.java.oop.account;

/**
 * 
 * 캡슐화
 * 	- 필드의 접근제한자를 private처리
 *  - 필드를 제어하는 메소드를 public로 지정
 *  - 필드와 메소드를 묶어서 클래스로 지정
 *
 */
public class Account {

	private String name;
	private long balance; // 잔액
	
	// setter
	// 변수명이 같은 경우 가까운 유효범위에 선언된 변수를 사용한다. 
	public void setName(String _name) {
		name = _name;
	}
	
	public void setBalance(long _balance) {
		balance = _balance;
	}
	
	// getter
	public String getName() {
		return name;
	}
	
	public long getBalance() {
		return balance;
	}
	
	/**
	 * 출금메소드
	 * - 출금액 유효성검사
	 * - 정확히 마이너스 연산을 통한 출금처리
	 * @param money
	 */
	public void withdraw(long money) {
		if(money <= 0) {
			System.err.println("유효한 금액을 입력하세요.");
			return;
		}
		
		if(money > balance) {
			System.err.println("출금액이 잔액보다 많습니다.");
			return;
		}
		
		balance -= money;
		System.out.println("출금처리 되었습니다.");
	}
	
	/**
	 * 입금 메소드
	 * - 유효한 금액인지 확인
	 * - 입금액 만치 더하기연산 처리
	 * @param money
	 */
	public void deposit(long money) {
		if(money > 0) {
			balance += money;
			System.out.println("입금처리 되었습니다.");
		}
		else {
			System.err.println("잘못된 금액이 입력되었습니다.");
		}
	}
	
	
}
