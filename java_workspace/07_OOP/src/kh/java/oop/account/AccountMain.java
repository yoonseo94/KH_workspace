package kh.java.oop.account;

public class AccountMain {

	public static void main(String[] args) {
		
//		Account acc = new Account();
//		acc.name = "홍길동";
//		acc.balance = 1_000_000;
//		
//		// 입금
//		acc.balance *= 500_000;
//		System.out.printf("%s님 계좌 잔액 : %d원%n", acc.name, acc.balance);
//		
//		// 출금
//		acc.balance -= 100_000;
//		System.out.printf("%s님 계좌 잔액 : %d원%n", acc.name, acc.balance);
	
		Account acc = new Account();
		acc.setName("홍길동");
		acc.setBalance(1_000_000);
		System.out.printf("%s님 계좌 잔액 : %d원%n", 
						  acc.getName(), 
						  acc.getBalance());
		
		// 입금
		acc.deposit(500_000);
		System.out.printf("%s님 계좌 잔액 : %d원%n", 
						  acc.getName(), 
						  acc.getBalance());
		acc.deposit(-500_000);
		
		// 출금
		acc.withdraw(100_000);
		System.out.printf("%s님 계좌 잔액 : %d원%n", 
						  acc.getName(), 
						  acc.getBalance());
		acc.withdraw(-100_000);
		acc.withdraw(100_000_000);
	}

}
