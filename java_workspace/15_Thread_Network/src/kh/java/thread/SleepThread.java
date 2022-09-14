package kh.java.thread;

public class SleepThread implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.print('-');
			try {
				Thread.sleep(500); // 밀리초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
