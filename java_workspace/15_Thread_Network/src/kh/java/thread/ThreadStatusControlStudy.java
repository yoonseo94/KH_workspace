package kh.java.thread;

import java.util.Scanner;

/**
 * 
 * Thread 상태제어
 * - 실행/실행대기 RUNNABLE 
 * - 일시정지	WAITING TIMED-WAITING BLOCKED
 * 
 * sleep
 * join
 * interrupt
 * wait
 * notify/notifyAll
 * yield
 *
 */
public class ThreadStatusControlStudy {
 
	public static void main(String[] args) {
		ThreadStatusControlStudy study = new ThreadStatusControlStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
		study.test5();
		
		System.out.println("[" + Thread.currentThread().getName() + " 종료!]");
	}

	/**
	 * interupt
	 * 특정스레드를 종료시킬수 있다.
	 */
	private void test5() {
		Thread th = new Thread(new CountDownThread(), "카운트다운");
		th.start();
		
		System.out.println("엔터 입력시 카운트다운 종료!");
		new Scanner(System.in).nextLine();
		th.interrupt(); // 특정쓰레드 종료(InterruptedException유발)
	}

	/**
	 * join
	 * - 특정스레드 종료시/지정한 시간동안 기다린다.
	 */
	private void test4() {
		Thread th = new Thread(new CountDownThread(), "카운트다운");
		th.start();
		
		// 메인쓰레드가 카운트다운쓰레드에 조인
		try {
			// 현재쓰레드(main)가 th쓰레드에 join 
//			th.join();
			th.join(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 대몬쓰레드 daemon thread
	 * - 백그라운드 쓰레드
	 * - 일반쓰레드가 모두 종료하면 따라서 종료
	 */
	private void test3() {
		Thread th = new Thread(new CountDownThread(), "카운트다운");
		th.setDaemon(true);
		th.start();
		
		System.out.println("엔터입력시 카운트다운 종료!!");
		new Scanner(System.in).nextLine();
	}

	/**
	 * 구구단 쓰레드
	 * - 사용자입력값 (2~9)
	 * - 구구단을 출력(쓰레드) : 각 수는 0.7초간격으로 출력
	 */
	private void test2() {
		// 사용자 입력값 처리
		Scanner sc = new Scanner(System.in);
		System.out.print("구구단 단수 입력(2 ~ 9) : ");
		int dan = sc.nextInt();
		System.out.println("몇 초간격으로 출력할까요? : ");
		double sec = sc.nextDouble(); 
		// 0.1 => 100
		// 3 => 3000
		// 10 => 10000
		
		// 쓰레드 생성 및 실행
		// run메소드안 코드는 무엇?
		new Thread(new Gugudan(dan, sec)).start();
	}

	/**
	 * sleep
	 * (TIMED-WAITNG - RUNNABLE)
	 */
	private void test1() {
		// new Thread(Runnable)
		Thread th = new Thread(new SleepThread());
		th.start();
	}
}
