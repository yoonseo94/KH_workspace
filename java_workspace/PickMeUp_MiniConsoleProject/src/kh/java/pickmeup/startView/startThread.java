package kh.java.pickmeup.startView;

public class startThread implements Runnable {
	
	@Override
	public void run() {
		System.out.print("┌");
		for(int i = 0; i < 60; i++) {
			System.out.print("─");
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.print("┐");
		System.out.println();
		
		
		String[] view = 
						{"│ ========================================================== │",
				         "│                                                            │",
				         "│ *****  *  *****  *  *     *     *  *****     *    *  ***** │",
				         "│ *   *  *  *      * *      **   **  *         *    *  *   * │",
				         "│ *****  *  *      **       * * * *  *****     *    *  ***** │",
				         "│ *      *  *      * *      *  *  *  *         *    *  *     │",
				         "│ *      *  *****  *  *     *  *  *  *****      ****   *     │",
				         "│                                                            │",
				         "│                  ┌──────────────────────┐                  │",
				         "│                  │      Game Start      │                  │",
				         "│                  │  Press Enter To Play │                  │",
				         "│                  └──────────────────────┘                  │",
				         "│ ========================================================== │"};
		
		
		for(int i = 0; i < view.length; i ++) {
			System.out.println(view[i]);
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.print("└");
		for(int i = 0; i < 60; i++) {
			System.out.print("─");
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("┘\n");
	}
 }