package kh.java.pickmeup.startView;

public class Narration extends Thread implements Runnable {
	
	@Override
	public void run() {
		System.out.print(" ");
		for(int i = 0; i < 60; i++) {
			System.out.print("─");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println();
		String[] narr = {"  =============================================================== ",
		                 "                           『게임설명』                         ",
		                 "   Pick Me Up 게임은 유저가 원하는 캐릭터를 선택 후 다양한 방법으로 육성하 ",
		                 "  는 콘솔형 게임입니다. 각 레벨에 맞는 사냥터를 유저가 선택하여 입장 후 랜덤  ",
		                 "  하게 등장하는 필드 몬스터들을 사냥 후 경험치를 얻고 보스레이드에 입장할 수  ",
		                 "  있는 권한을 획득하여 보스사냥에 도전하는 시스템입니다.                  ",
		                 "  그 외에도 미니게임으로 가위바위보, 동전던지기 게임을 즐길 수 있습니다.     ",
		                 "                                                             ",
		                 "   자신의 스타일에 맞는 캐릭터를 선택하여 명예의 전당에 올라보세요!!!!      ",
					     "  =============================================================== ",};

		for(int i = 0; i < narr.length ; i ++) {
			System.out.println(narr[i]);
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
//				e.printStackTrace();
				return;
			}
	
		}
		
		System.out.print(" ");
		for(int i = 0; i < 60; i++) {
			System.out.print("─");
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return;
	}
}