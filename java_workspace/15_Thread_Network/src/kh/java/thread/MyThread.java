package kh.java.thread;

public class MyThread extends Thread {

	char ch;
	
	public MyThread(char ch) {
		this.ch = ch;
	}
	
	/**
	 * 쓰레드의 작업내용
	 */
	@Override
	public void run() {
		
		for(int i = 0; i < 100; i++)
			System.out.print(ch);
		
	}
}
