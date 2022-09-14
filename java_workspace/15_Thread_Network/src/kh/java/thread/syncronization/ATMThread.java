package kh.java.thread.syncronization;

public class ATMThread implements Runnable {

	private Account acc;
	
	public ATMThread (Account acc) {
		this.acc = acc;
	}
	
	@Override
	public void run() {
		// 잔액이 없을때 까지 출금시도
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random() * 3 + 1) * 100; // 100 200 300
			acc.withdraw(money); // 출금
		}
		System.out.println("[" + Thread.currentThread().getName() + "종료]");
	}

}
