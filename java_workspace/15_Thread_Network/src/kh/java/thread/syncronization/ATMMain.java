package kh.java.thread.syncronization;

public class ATMMain {

	public static void main(String[] args) {
		Account acc = new Account(); // 공유자원
		
		Thread th1 = new Thread(new ATMThread(acc), "ATM1");
		Thread th2 = new Thread(new ATMThread(acc), "ATM2");
		th1.start();
		th2.start();

	}

}
