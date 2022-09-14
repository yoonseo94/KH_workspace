package kh.java.thread;

public class ThreadBasicStudy {

	public static void main(String[] args) {
		ThreadBasicStudy study = new ThreadBasicStudy();
//		study.test1();
//		study.test2();
		study.test3();
		
		System.out.print("[" + Thread.currentThread().getName() + "종료]");
	}


	/**
	 * Runnable인터페이스 구현 쓰레드 
	 * 
	 * 쓰레드 우선순위 지정 
	 * - 1 우선순위 가장 낮음
	 * - 5 기본값
	 * - 10 우선순위 가장 높음
	 * (스레드 스케쥴링은 OS소관이므로 완전하게 지정할수는 없다.)
	 */
	private void test3() {
		Runnable run1 = new MyRunnable('-');
		Runnable run2 = new MyRunnable('|');
		
		Thread thread1 = new Thread(run1);
		Thread thread2 = new Thread(run2);
		
		thread1.setName("홍길동 쓰레드");
		thread2.setName("신사임당 쓰레드");
		
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread2.setPriority(Thread.MIN_PRIORITY);
		
		// Override한 run이 아닌 start를 호출해야 새로운 callstack에서 작업이 시작된다.
		// 추가한 Thread의 시작메소드는 run이된다.
		thread1.start();
		thread2.start();
	}


	/**
	 * 하나의 프로세스 - 멀티 쓰레드
	 * 
	 * 쓰레드 객체를 만드는 방법
	 * 1. Thread 상속 클래스
	 * 2. Runnable 구현 클래스
	 * 
	 * - 자바는 단일상속, 다중구현을 지원하므로, Thread상속보다는 Runnable구현을 선호하는 편이다. 
	 * 
	 */
	private void test2() {
		Thread thread1 = new MyThread('-');
		Thread thread2 = new MyThread('|');
		
		thread1.start();
		thread2.start();
	}


	/**
	 * 하나의 프로세스 - 하나의 쓰레드(main)
	 */
	private void test1() {
		taskA('-');
		taskB('|');
	}
	
	public void taskA(char ch) {
		for(int i = 0; i < 100; i++) {
			System.out.print(ch);
		}
	}
	
	public void taskB(char ch) {
		for(int i = 0; i < 100; i++) {
			System.out.print(ch);
		}
	}

}
