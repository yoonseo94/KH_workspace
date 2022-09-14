package kh.java.thread;

public class MyRunnable implements Runnable {

	char ch;
	
	public MyRunnable(char ch) {
		this.ch = ch;
	}
	
	/**
	 * 쓰레드 작업내용
	 */
	@Override
	public void run() {
		
		for(int i = 0; i < 100; i++)
			System.out.print(ch);
		
		System.out.print("[" + Thread.currentThread().getName() + "종료]");
	}

}
