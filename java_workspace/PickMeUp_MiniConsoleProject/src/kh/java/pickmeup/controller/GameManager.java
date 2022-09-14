package kh.java.pickmeup.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import kh.java.pickmeup.model.vo.boss.Boss1;
import kh.java.pickmeup.model.vo.boss.Boss2;
import kh.java.pickmeup.model.vo.boss.Boss3;
import kh.java.pickmeup.model.vo.boss.Boss4;
import kh.java.pickmeup.model.vo.boss.Boss5;
import kh.java.pickmeup.model.vo.character.BEWDragon;
import kh.java.pickmeup.model.vo.character.Baekho;
import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.character.Haetae;
import kh.java.pickmeup.model.vo.character.Jujak;
import kh.java.pickmeup.model.vo.character.Lapras;
import kh.java.pickmeup.model.vo.character.Mokoko;
import kh.java.pickmeup.model.vo.character.Raptor;
import kh.java.pickmeup.model.vo.character.T_Rex;
import kh.java.pickmeup.model.vo.character.Tops;
import kh.java.pickmeup.model.vo.interfaces.Attackable;
import kh.java.pickmeup.model.vo.interfaces.DailyRoutinable;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;
import kh.java.pickmeup.model.vo.monster.Clown;
import kh.java.pickmeup.model.vo.monster.Dragon;
import kh.java.pickmeup.model.vo.monster.Goblin;
import kh.java.pickmeup.model.vo.monster.Hornet;
import kh.java.pickmeup.model.vo.monster.Indominus_rex;
import kh.java.pickmeup.model.vo.monster.Monster;
import kh.java.pickmeup.model.vo.monster.Orc;
import kh.java.pickmeup.model.vo.monster.Orger;
import kh.java.pickmeup.model.vo.monster.Pteranodon;
import kh.java.pickmeup.model.vo.monster.Silverwolf;
import kh.java.pickmeup.model.vo.monster.Slaim;
import kh.java.pickmeup.model.vo.monster.SoldierAnt;
import kh.java.pickmeup.model.vo.monster.Stegosaurus;
import kh.java.pickmeup.startView.Narration;
import kh.java.pickmeup.startView.startThread;

public class GameManager {
	Scanner sc = new Scanner(System.in);
	Random rnd = new Random();
	Thread startTh = new Thread(new startThread(), "게임시작");              // 쓰레드로 구현
	Thread narrTh = new Thread(new Narration(), "게임설명");                   
	
	public List<Character> chaLists;
	Jujak jujak = new Jujak();
	Baekho baekho = new Baekho();
	Haetae haetae = new Haetae();
	Mokoko mokoko = new Mokoko();
	BEWDragon bewdragon = new BEWDragon();
	Lapras lapras = new Lapras();
	Raptor raptor = new Raptor();            				
	T_Rex t_rex = new T_Rex();			     				
	Tops tops = new Tops();                 				
	
	public List<Monster> monsLists;
	Slaim slaim = new Slaim();
	Hornet hornet = new Hornet();
	SoldierAnt soldierAnt = new SoldierAnt();
	Goblin goblin = new Goblin();
	Silverwolf silverwolf = new Silverwolf();
	Pteranodon pteranodon = new Pteranodon();				
	Clown clown = new Clown();
	Orc orc = new Orc();
	Stegosaurus stegosaurus = new Stegosaurus();           	
	Orger orger = new Orger();
	Indominus_rex indominus_rex = new Indominus_rex();		
	Dragon dragon = new Dragon();
	
	public List<Monster> bossLists;         					
	Boss1 boss1 = new Boss1();
	Boss2 boss2 = new Boss2();
	Boss3 boss3 = new Boss3();
	Boss4 boss4 = new Boss4();
	Boss5 boss5 = new Boss5();
	
	int choiceNo = 0;
	int choiceMap = 0;
	
	public GameManager() {
		
		chaLists = new ArrayList<>();
		chaLists.add(jujak);	
		chaLists.add(baekho);	
		chaLists.add(haetae);
		chaLists.add(mokoko);	
		chaLists.add(bewdragon);	
		chaLists.add(lapras);
		chaLists.add(raptor);                               
		chaLists.add(t_rex);								
		chaLists.add(tops);	
		
		monsLists = new ArrayList<>();
		monsLists.add(slaim);
		monsLists.add(hornet);
		monsLists.add(soldierAnt);
		monsLists.add(goblin);
		monsLists.add(silverwolf);
		monsLists.add(pteranodon);                          
		monsLists.add(clown);
		monsLists.add(orc);
		monsLists.add(stegosaurus);							
		monsLists.add(orger);
		monsLists.add(indominus_rex);						
		monsLists.add(dragon);
		
		bossLists = new ArrayList<>();  					
		bossLists.add(boss1);
		bossLists.add(boss2);
		bossLists.add(boss3);
		bossLists.add(boss4);
		bossLists.add(boss5);
		
	}
	
	public void SetNickName(int choiceNo) {
		System.out.print("별명을 지어주세요: ");
		chaLists.get(choiceNo).setNickName(sc.nextLine());
	}
	
	public void TakeARest(int choiceNo) {	
		((DailyRoutinable) chaLists.get(choiceNo)).rest();	
	}
	
	public void Exercise(int choiceNo) {	
		((DailyRoutinable) chaLists.get(choiceNo)).exercise();
	}
	
	public void Intro() {
		startTh.start();														// 시작화면 출력
		String enter = sc.nextLine();                                           // 엔터로 다음메소드 호출
		narrTh.start();															// 내레이션 출력
		
		// 내레이션 출력 중 엔터로 skip
		System.out.println("                                              Enter for skip  ");
		new Scanner(System.in).nextLine();
		narrTh.interrupt();
		
		StartMenu();
	}

	public void StartMenu() {

			
		String startMenu = "============= 게임 시작 ============\r\n"
						 + "----------------------------------\r\n"
						 + "1. 새 게임\r\n"
						 + "2. 불러오기\r\n"
						 + "3. 게임종료\r\n"
						 + "----------------------------------\r\n"
						 + "선택 > ";
		
		
		
		while(true) {
			System.out.print(startMenu);
			String stChoice = sc.nextLine();
			
			switch(stChoice) {
			case "1": SelectMenu();
				break;
			case "2": 
				LoadFile();
				break;
			case "3": 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void SelectMenu() {
		
		String selectMenu = "============ 캐릭터 선택 ============\r\n"
				 		  + "----------------------------------\r\n"
				 		  + "1. 주작\r\n"
				 		  + "2. 백호\r\n"
				 		  + "3. 해태\r\n"
				 		  + "4. 모코코\r\n"
				 		  + "5. 푸른 눈의 백룡\r\n"
				 		  + "6. 라프라스 \r\n"
				 		  + "7. 랩터\r\n"
				 		  + "8. T-렉스\r\n"
				 		  + "9. 톱스\r\n"
				 		  + "0. 돌아가기\r\n"
				 		  + "----------------------------------\r\n"
				 		  + "선택 > ";
		
		while(true) {
			System.out.print(selectMenu);
			String seChoice = sc.nextLine();
			
			switch(seChoice) {
			case "1":
				choiceNo = 0;
				selectCheckMenu(choiceNo);
				break;
			case "2": 
				choiceNo = 1;
				selectCheckMenu(choiceNo);
				break;
			case "3": 
				choiceNo = 2;
				selectCheckMenu(choiceNo);
				break;
			case "4":
				choiceNo = 3;
				selectCheckMenu(choiceNo);
				break;
			case "5":
				choiceNo = 4;
				selectCheckMenu(choiceNo);
				break;
			case "6":
				choiceNo = 5;
				selectCheckMenu(choiceNo);
				break;
			case "7":
				choiceNo = 6;
				selectCheckMenu(choiceNo);
				break;
			case "8":
				choiceNo = 7;
				selectCheckMenu(choiceNo);
				break;
			case "9":
				choiceNo = 8;
				selectCheckMenu(choiceNo);
				break;
			case "0": 
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void selectCheckMenu(int choiceNo) {
		
		String selectCheckMenuJ  = "============== 1 주작 =============\r\n"
								 + "----------------------------------\r\n"
								 + "캐릭터 정보\r\n"
								 + "종족: 환수\r\n"
								 + "이름: 주작\r\n"
								 + "레벨: 1\r\n"
								 + "파워:	50\r\n"
								 + "에너지: 200\r\n"
								 + "----------------------------------\r\n"
								 + "1. 선택완료\r\n"
								 + "2. 재선택\r\n"	
								 + "-----------------------------------\r\n"
								 + "선택 > ";
		
		String selectCheckMenuB = "============== 2 백호 ==============\r\n"
								+ "-----------------------------------\r\n"
								+ "캐릭터 정보\r\n"
								+ "종족: 환수\r\n"
								+ "이름: 백호\r\n"
								+ "레벨: 1\r\n"
								+ "파워:	25\r\n"
								+ "에너지: 350\r\n"
								+ "-----------------------------------\r\n"
								+ "1. 선택완료\r\n"
								+ "2. 재선택\r\n"	
								+ "-----------------------------------\r\n"
								+ "선택 > ";

		String selectCheckMenuH  = "============== 3 해태 ==============\r\n"
								 + "-----------------------------------\r\n"
								 + "캐릭터 정보\r\n"
								 + "종족: 환수\r\n"
								 + "이름: 해태\r\n"
								 + "레벨: 1\r\n"
								 + "파워:	 35\r\n"
								 + "에너지: 250\r\n"
								 + "-----------------------------------\r\n"
								 + "1. 선택완료\r\n"
								 + "2. 재선택\r\n"	
								 + "-----------------------------------\r\n"
								 + "선택 > ";
		
		String selectCheckMenuMOK = "============== 4 모코코 =============\r\n"
								  + "-----------------------------------\r\n"
								  + "캐릭터 정보\r\n"
								  + "종족: 풀\r\n"
								  + "이름: 모코코\r\n"
								  + "레벨: 1\r\n"
								  + "파워: 15\r\n"
								  + "에너지: 150\r\n"
								  + "-----------------------------------\r\n"
								  + "1. 선택완료\r\n"
								  + "2. 재선택\r\n"	
								  + "-----------------------------------\r\n"
								  + "선택 > ";

		String selectCheckMenuBEWD = "========= 5 푸른 눈의 백룡 ===========\r\n"
								   + "-----------------------------------\r\n"
								   + "캐릭터 정보\r\n"
								   + "종족: 용\r\n"
								   + "이름: 푸른눈의 백룡\r\n"
								   + "레벨: 1\r\n"
								   + "파워: 30\r\n"
								   + "에너지: 300\r\n"
								   + "-----------------------------------\r\n"
								   + "1. 선택완료\r\n"
								   + "2. 재선택\r\n"	
								   + "-----------------------------------\r\n"
								   + "선택 > ";

		String selectCheckMenuLAP = "============ 6 라프라스 =============\r\n"
								  + "-----------------------------------\r\n"
								  + "캐릭터 정보\r\n"
								  + "종족: 해룡\r\n"
								  + "이름: 라프라스\r\n"
								  + "레벨: 1\r\n"
								  + "파워: 20\r\n"
								  + "에너지: 250\r\n"
								  + "-----------------------------------\r\n"
								  + "1. 선택완료\r\n"
								  + "2. 재선택\r\n"	
								  + "-----------------------------------\r\n"
								  + "선택 > ";
		
		String selectCheckMenuR  = "============== 7 랩터 ==============\r\n"   
								 + "-----------------------------------\r\n"
								 + "캐릭터 정보\r\n"
								 + "종족: Dinosaur\r\n"
								 + "이름: 랩터\r\n"
								 + "레벨: 1\r\n"
								 + "파워:	 35\r\n"
								 + "에너지: 250\r\n"
								 + "-----------------------------------\r\n"
								 + "1. 선택완료\r\n"
								 + "2. 재선택\r\n"	
								 + "-----------------------------------\r\n"
								 + "선택 > ";

		String selectCheckMenuT  = "============== 8 티렉스 =============\r\n"    
				 				 + "-----------------------------------\r\n"
				 				 + "캐릭터 정보\r\n"
				 				 + "종족: Dinosaur\r\n"
				 				 + "이름: 티렉스\r\n"
				 				 + "레벨: 1\r\n"
				 				 + "파워:	 50\r\n"
				 				 + "에너지: 220\r\n"
				 				 + "-----------------------------------\r\n"
				 				 + "1. 선택완료\r\n"
				 				 + "2. 재선택\r\n"	
				 				 + "-----------------------------------\r\n"
				 				 + "선택 > ";

		String selectCheckMenuT2 = "============== 9 톱스 ==============\r\n"    
								 + "-----------------------------------\r\n"
								 + "캐릭터 정보\r\n"
								 + "종족: Dinosaur\r\n"
								 + "이름: 톱스\r\n"
								 + "레벨: 1\r\n"
								 + "파워:	 25\r\n"
								 + "에너지: 350\r\n"
								 + "-----------------------------------\r\n"
								 + "1. 선택완료\r\n"
								 + "2. 재선택\r\n"	
								 + "-----------------------------------\r\n"
								 + "선택 > ";
		
		while(true) {
			if(choiceNo == 0) {
				System.out.print(selectCheckMenuJ);
			}
			else if(choiceNo == 1) {
				System.out.print(selectCheckMenuB);
			}
			else if(choiceNo == 2) {
				System.out.print(selectCheckMenuH);
			}
			else if(choiceNo == 3) {
				System.out.print(selectCheckMenuMOK);
			}
			else if(choiceNo == 4) {
				System.out.print(selectCheckMenuBEWD);
			}
			else if(choiceNo == 5) {
				System.out.print(selectCheckMenuLAP);
			}
			else if(choiceNo == 6) {                           		
				System.out.print(selectCheckMenuR);
			}
			else if(choiceNo == 7) {								
				System.out.print(selectCheckMenuT);
			}
			else if(choiceNo == 8) {								
				System.out.print(selectCheckMenuT2);
			}
			
			String choice = sc.nextLine();
			switch(choice) {
			case "1": 
				System.out.print("선택하신 캐릭터의 별명을 입력해 주세요: ");
				chaLists.get(choiceNo).setNickName(sc.nextLine());
				MainMenu();
				break;
			case "2": 
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void MainMenu() {

		String mainMenu = "============= 메인메뉴 ==============\r\n"
						+ "-----------------------------------\r\n"
						+ "1. 휴식(에너지 회복)\r\n"
			   			+ "2. 운동(파워 상승)\r\n"
			   			+ "3. 전투\r\n"
			   			+ "4. 미니게임\r\n"
			   			+ "5. 상점\r\n"
			   			+ "6. 상태 보기\r\n"
			   			+ "7. 명예의 전당\r\n"
			   			+ "8. 저장하기\r\n"
			   			+ "9. 돌아가기\r\n"
			   			+ "-----------------------------------\r\n"
			   			+ "선택 > ";
		
		while(true) {
		
			System.out.print(mainMenu);
			String mChoice = sc.nextLine();
			
			switch(mChoice) {
			case "1": 
				TakeARest(choiceNo);
				break;
			case "2": 
				Exercise(choiceNo);
				break;
			case "3": 
				BattleMapMenu(choiceNo);
				break;
			case "4": 
				MiniGameMenu(choiceNo);
				break;
			case "5": 
				GotoShop(choiceNo);
				break;
			case "6": 
				System.out.println(chaLists.get(choiceNo).statusInfo());
				break;
			case "7": 
				HallOfFame(choiceNo);
				break;
			case "8": 
				SaveFile();
				break;
			case "9": 
				System.out.println("이전 메뉴로 돌아갑니다.");
				StartMenu();
				break;
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void BattleMapMenu(int choiceNo) {
		
		String battleMenu = "============= 전투지역 ==============\r\n"  
					  	  + "-----------------------------------\r\n"
					  	  + "1. 초심자의 숲\r\n"
					  	  + "2. 초원지대\r\n"
					  	  + "3. 깊은 숲\r\n"
					  	  + "4. 사막지대\r\n"
					  	  + "5. 미궁\r\n"
					  	  + "6. 돌아가기\r\n"					  	  
					  	  + "-----------------------------------\r\n"
					  	  + "선택 > ";

		while(true) {
			System.out.print(battleMenu);
			String beChoice = sc.nextLine();
	
			switch(beChoice) {
			case "1": 
				choiceMap = 0;
				Map1_Beginners_Forest(choiceNo, choiceMap);
				break;
			case "2": 
				choiceMap = 1;
				Map2_Meadow(choiceNo, choiceMap);
				break;
			case "3": 
				choiceMap = 2;
				Map3_The_Deepst_Forest(choiceNo, choiceMap);
				break;
			case "4": 
				choiceMap = 3;
				Map4_The_Desert(choiceNo, choiceMap);
				break;
			case "5": 
				choiceMap = 4;
				Map5_Labyrinth(choiceNo, choiceMap);
				break;
			case "6": 
				choiceMap = 5;
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void Map1_Beginners_Forest(int choiceNo, int choiceMap) {	// 초심자의 숲
		AdventureMenu(choiceNo, choiceMap);
	}
	
	public void Map2_Meadow(int choiceNo, int choiceMap) {				// 초원지대
		AdventureMenu(choiceNo, choiceMap);
	}
	
	public void Map3_The_Deepst_Forest(int choiceNo, int choiceMap) {	// 깊은 숲
		AdventureMenu(choiceNo, choiceMap);
	}
	
	public void Map4_The_Desert(int choiceNo, int choiceMap) {			// 사막지대
		AdventureMenu(choiceNo, choiceMap);
	}
	
	public void Map5_Labyrinth(int choiceNo, int choiceMap) {			// 미궁
		AdventureMenu(choiceNo, choiceMap);
	}
	
	public void Battle(int choiceNo, int choiceMap) {
		
		System.out.println("주사위를 던집니다.");
		int playerDice = rnd.nextInt(6) + 1;
		int monsterDice = rnd.nextInt(6) + 1;
		System.out.println("플레이어: " + playerDice + " --- " + "몬스터: " + monsterDice);
		
		int monsRandom1 = rnd.nextInt(3) + 0;	// 등장 몬스터 랜덤(맵1)
		int monsRandom2 = rnd.nextInt(3) + 2;	// 등장 몬스터 랜덤(맵2)
		int monsRandom3 = rnd.nextInt(3) + 4;	// 등장 몬스터 랜덤(맵3)
		int monsRandom4 = rnd.nextInt(3) + 6;	// 등장 몬스터 랜덤(맵4)
		int monsRandom5 = rnd.nextInt(3) + 9;	// 등장 몬스터 랜덤(맵5)
		
		int monsRandom = 0;
		
		if(choiceMap == 0) {										//Map1_Beginners_Forest
			monsRandom = monsRandom1;
			BattleProcess(playerDice, monsterDice, choiceNo, choiceMap, monsRandom);
		}
		else if(choiceMap == 1) {									// Map2_Meadow
			monsRandom = monsRandom2;
			BattleProcess(playerDice, monsterDice, choiceNo, choiceMap, monsRandom);
		}
		else if(choiceMap == 2) {									// Map3_The_Deepst_Forest
			monsRandom = monsRandom3;
			BattleProcess(playerDice, monsterDice, choiceNo, choiceMap, monsRandom);
		}
		else if(choiceMap == 3) {									// Map4_The_Desert
			monsRandom = monsRandom4;
			BattleProcess(playerDice, monsterDice, choiceNo, choiceMap, monsRandom);
		}
		else if(choiceMap == 4) {									// Map5_Labyrinth
			monsRandom = monsRandom5;
			BattleProcess(playerDice, monsterDice, choiceNo, choiceMap, monsRandom);
		}
	}
	
	public void bossRaid(int choiceNo, int choiceMap) {             // 보스몬스터 
		
			System.out.println("주사위를 던집니다.");
			int playerDice = rnd.nextInt(6) + 1;
			int bossDice = rnd.nextInt(6) + 1;
			System.out.println("플레이어: " + playerDice + " --- " + "보스: " + bossDice);
		
			int boss1 = 0;	// 등장 보스
			int boss2 = 1;	// 등장 보스
			int boss3 = 2;	// 등장 보스
			int boss4 = 3;	// 등장 보스
			int boss5 = 4;	// 등장 보스
		
		
			if(choiceMap == 0) {										//Map1_Beginners_Forest 보스
				BossBattle(playerDice, bossDice, choiceNo, choiceMap, boss1);
			}
			else if(choiceMap == 1) {									// Map2_Meadow 보스
				BossBattle(playerDice, bossDice, choiceNo, choiceMap, boss2);
			}
			else if(choiceMap == 2) {									// Map3_The_Deepst_Forest 보스
				BossBattle(playerDice, bossDice, choiceNo, choiceMap, boss3);
			}
			else if(choiceMap == 3) {									// Map4_The_Desert 보스
				BossBattle(playerDice, bossDice, choiceNo, choiceMap, boss4);
			}
			else if(choiceMap == 4) {									// Map5_Labyrinth 보스
				BossBattle(playerDice, bossDice, choiceNo, choiceMap, boss5);
			}
		}
	
	public void BattleProcess(int playerDice, int monsterDice, int choiceNo, int choiceMap, int monsRandom) {
		int plusExp = 0; 
		if(playerDice >= monsterDice) { 						//플레이어 선공
			System.out.println("플레이어가 선공합니다.");
			System.out.printf("야생의 %s이/가 나타났다. %n", monsLists.get(monsRandom).getName());			
			while(true) {
					if(monsLists.get(monsRandom).getEnergy() > 0 &&
							chaLists.get(choiceNo).getEnergy() > 0) {
						System.out.println("============ Your Turn ============");
						AttackMenu(choiceNo, monsRandom);
						System.out.println("========== Monster Turn ===========");
						MonstersAtttack(choiceNo, monsRandom);
						System.out.println("============ Turn End =============");
						System.out.println("===================================");
					}
					else if(monsLists.get(monsRandom).getEnergy() <= 0) {
						System.out.printf("당신은 %s에게 승리하셨습니다. %n", monsLists.get(monsRandom).getName());
						plusExp = chaLists.get(choiceNo).getExp() + monsLists.get(monsRandom).getExp();
						chaLists.get(choiceNo).setExp(plusExp);
						System.out.printf("%s가 승리하여 %d의 경험치를 얻었습니다.%n",chaLists.get(choiceNo).getNickName() , plusExp);
						chaLists.get(choiceNo).setBattleWinCount(chaLists.get(choiceNo).getBattleWinCount() + 1);
						monsLists.get(monsRandom).setEnergy(monsLists.get(monsRandom).getMaxEnergy());
						if(chaLists.get(choiceNo).getExp() > 100 * chaLists.get(choiceNo).getLevel()) {
							chaLists.get(choiceNo).levelUp();
						}
						AdventureMenu(choiceNo, choiceMap);
					}
					else if(chaLists.get(choiceNo).getEnergy() <= 0) {
						System.out.printf("당신은 %s에게 패배하셨습니다. %n", monsLists.get(monsRandom).getName());
						monsLists.get(monsRandom).setEnergy(monsLists.get(monsRandom).getMaxEnergy());
						return;
				}
			}
		}
		else {													// 몬스터 선공
			System.out.println("몬스터가 선공합니다.");
			System.out.printf("야생의 %s이/가 나타났다. %n", monsLists.get(monsRandom).getName());
			while(true) {
				if(monsLists.get(monsRandom).getEnergy() > 0 &&
						chaLists.get(choiceNo).getEnergy() > 0) {
					
					System.out.println("========== Monster Turn ===========");
					MonstersAtttack(choiceNo, monsRandom);
					System.out.println("============ Your Turn ============");
					AttackMenu(choiceNo, monsRandom);
					System.out.println("============ Turn End =============");
					System.out.println("===================================");
				}
				if(monsLists.get(monsRandom).getEnergy() <= 0) {
					System.out.printf("당신은 %s에게 승리하셨습니다. %n", monsLists.get(monsRandom).getName());
					plusExp = chaLists.get(choiceNo).getExp() + monsLists.get(monsRandom).getExp();
					chaLists.get(choiceNo).setExp(plusExp);
					System.out.printf("%s가 승리하여 %d의 경험치를 얻었습니다.%n",chaLists.get(choiceNo).getNickName() , plusExp);
					chaLists.get(choiceNo).setBattleWinCount(chaLists.get(choiceNo).getBattleWinCount() + 1);
					monsLists.get(monsRandom).setEnergy(monsLists.get(monsRandom).getMaxEnergy());
					AdventureMenu(choiceNo, choiceMap);
				}
				else if(chaLists.get(choiceNo).getEnergy() <= 0) {
					System.out.printf("당신은 %s에게 패배하셨습니다. %n", monsLists.get(monsRandom).getName());
					monsLists.get(monsRandom).setEnergy(monsLists.get(monsRandom).getMaxEnergy());
					return;
				}
			}
		}
	}
	
	public void BossBattle(int playerDice, int bossDice, int choiceNo, int choiceMap, int boss) {
		int plusExp = 0; 
		if(playerDice >= bossDice) { 						//플레이어 선공
			System.out.println("플레이어가 선공합니다.");
			System.out.printf("이 필드의 보스 [%s] 등장. %n", bossLists.get(boss).getName());			
			while(true) {
					if(bossLists.get(boss).getEnergy() > 0 &&
							chaLists.get(choiceNo).getEnergy() > 0) {
						System.out.println("============ Your Turn ============");
						BossAttackMenu(choiceNo, boss);
						System.out.println("============ Boss Turn ============");
						BossAtttack(choiceNo, boss);
						System.out.println("============ Turn End =============");
						System.out.println("===================================");
					}
					else if(bossLists.get(boss).getEnergy() <= 0) {
						System.out.printf("당신은 %s에게 승리하셨습니다. %n", bossLists.get(boss).getName());
						plusExp = chaLists.get(choiceNo).getExp() + bossLists.get(boss).getExp();
						chaLists.get(choiceNo).setExp(plusExp);
						System.out.printf("%s가 승리하여 %d의 경험치를 얻었습니다.%n",chaLists.get(choiceNo).getNickName() , plusExp);
						chaLists.get(choiceNo).setBattleWinCount(chaLists.get(choiceNo).getBattleWinCount() + 1);
						bossLists.get(boss).setEnergy(bossLists.get(boss).getMaxEnergy());
						if(chaLists.get(choiceNo).getExp() > 100 * chaLists.get(choiceNo).getLevel()) {
							chaLists.get(choiceNo).levelUp();
						}
						AdventureMenu(choiceNo, choiceMap);
					}
					else if(chaLists.get(choiceNo).getEnergy() <= 0) {
						System.out.printf("당신은 %s에게 패배하셨습니다. %n", bossLists.get(boss).getName());
						bossLists.get(boss).setEnergy(bossLists.get(boss).getMaxEnergy());
						return;
				}
			}
		}
		else {													// 몬스터 선공
			System.out.println("보스가 선공합니다.");
			System.out.printf("이 필드의 보스 [%s] 등장. %n", bossLists.get(boss).getName());
			while(true) {
				if(bossLists.get(boss).getEnergy() > 0 &&
						chaLists.get(choiceNo).getEnergy() > 0) {
					
					System.out.println("============ Boss Turn ============");
					BossAtttack(choiceNo, boss);
					System.out.println("============ Your Turn ============");
					BossAttackMenu(choiceNo, boss);
					System.out.println("============ Turn End =============");
					System.out.println("===================================");
				}              
				if(bossLists.get(boss).getEnergy() <= 0) {
					System.out.printf("당신은 %s에게 승리하셨습니다. %n", bossLists.get(boss).getName());
					plusExp = chaLists.get(choiceNo).getExp() + bossLists.get(boss).getExp();
					chaLists.get(choiceNo).setExp(plusExp);
					System.out.printf("%s가 승리하여 %d의 경험치를 얻었습니다.%n",chaLists.get(choiceNo).getNickName() , plusExp);
					chaLists.get(choiceNo).setBattleWinCount(chaLists.get(choiceNo).getBattleWinCount() + 1);
					chaLists.get(choiceNo).setBossWinCount(chaLists.get(choiceNo).getBossWinCount() + 1);
					bossLists.get(boss).setEnergy(bossLists.get(boss).getMaxEnergy());
					AdventureMenu(choiceNo, choiceMap);
				}
				else if(chaLists.get(choiceNo).getEnergy() <= 0) {
					System.out.printf("당신은 %s에게 패배하셨습니다. %n", bossLists.get(boss).getName());
					bossLists.get(boss).setEnergy(bossLists.get(boss).getMaxEnergy());
					return;  
				}
			}
		}
	}

	public void AdventureMenu(int choiceNo, int choiceMap) {
		
		String adventureMenu = "============= 탐험 메뉴 =============\r\n"
				 			 + "-----------------------------------\r\n"
				 			 + "1. 이 지역을 탐험한다.(몬스터 출현확률 70%)\r\n"
				 			 + "2. 보스레이드(레벨 15이상 출입가능)\r\n"
				 			 + "3. 집으로 돌아간다.\r\n"
				 			 + "-----------------------------------\r\n"
				 			 + "선택 > ";
	
		while(true) {
			System.out.print(adventureMenu);
			String adChoice = sc.next();			  // 메뉴 선택 변수
			int appearMonster = rnd.nextInt(100) + 1; // 몬스터 출현 확률용 최솟값/최댓값(1~100) 
			sc.nextLine();
			switch(adChoice) {
			case "1": 
				if(appearMonster <= 70) {			  // 1 ~ 70 사이의 숫자가 나오면 몬스터 출현
					Battle(choiceNo, choiceMap);
					break;
				}
				else if(appearMonster >= 71) {		  // 71 이상의 숫자가 나오면 몬스터 출현하지 않음
					break;
				}
			case "2":									  // 레벨 15이상 출입가능
				if(chaLists.get(choiceNo).getLevel() >= 15) {
					System.out.println("보스레이드!");
					bossRaid(choiceNo, choiceMap);
					break;
				}
				System.out.println("입장가능 레벨이 아닙니다.");
			case "3":
				System.out.println("이전 메뉴로 돌아갑니다.");
				MainMenu();
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void AttackMenu(int choiceNo, int monsRandom) {
		
		String attackMenu = "============= 전투메뉴 ==============\r\n"
					  	  + "-----------------------------------\r\n"
					  	  + "1. 일반공격\r\n"
					  	  + "2. 특수공격\r\n"
					  	  + "3. 도망가기\r\n"
					  	  + "-----------------------------------\r\n"
					  	  + "선택 > ";
		
		String specialAttackMenu = "============= 특수공격 ==============\r\n"
				 				 + "-----------------------------------\r\n"
				 				 + "1. 특수공격1(레벨제한 없음)\r\n"
				 				 + "2. 특수공격2(5레벨 이상 사용 가능)\r\n"
				 				 + "3. 특수공격3(10레벨 이상 사용 가능)\r\n"
				 				 + "4. 특수공격4(15레벨 이상 사용 가능)\r\n"
				 				 + "5. 돌아가기\r\n"
				 				 + "-----------------------------------\r\n"
				 				 + "선택 > ";

			System.out.print(attackMenu);
			String attChoice = sc.nextLine();
	
			switch(attChoice) {
			case "1": 
				((Attackable) chaLists.get(choiceNo)).normalAttack(monsLists.get(monsRandom));
				break;
			case "2": 
				System.out.print(specialAttackMenu);
				String attSpChoice = sc.nextLine();
				
					switch(attSpChoice) {
						case "1": 
							((Attackable) chaLists.get(choiceNo)).specialAttack1(monsLists.get(monsRandom));
							break;
						case "2": 
							((Attackable) chaLists.get(choiceNo)).specialAttack2(monsLists.get(monsRandom));
							break;
						case "3": 
							((Attackable) chaLists.get(choiceNo)).specialAttack3(monsLists.get(monsRandom));
							break;
						case "4": 
							((Attackable) chaLists.get(choiceNo)).specialAttack4(monsLists.get(monsRandom));
							break;
						case "5": 
							System.out.println("이전메뉴로 돌아갑니다.");
							return;
						default : 
							System.out.println("잘못 입력하셨습니다.");
					}
				break;
			case "3": 
				int escapeRnd = rnd.nextInt(100) + 1;
				if(escapeRnd >= 51) {
					System.out.println("도망치는 데에 성공했습니다.");
					AdventureMenu(choiceNo, choiceMap);
				}
				else {
					System.out.println("도망치지 못하였습니다.");
					break;
				}
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	
	
	
	public void BossAttackMenu(int choiceNo, int boss) {
		
			String attackMenu = "============= 전투메뉴 ==============\r\n"
							  + "-----------------------------------\r\n"
							  + "1. 일반공격\r\n"
							  + "2. 특수공격\r\n"
							  + "3. 도망가기\r\n"
							  + "-----------------------------------\r\n"
							  + "선택 > ";
		
			String specialAttackMenu = "============= 특수공격 ==============\r\n"
									 + "-----------------------------------\r\n"
									 + "1. 특수공격1(레벨제한 없음)\r\n"
									 + "2. 특수공격2(5레벨 이상 사용 가능)\r\n"
									 + "3. 특수공격3(10레벨 이상 사용 가능)\r\n"
									 + "4. 특수공격4(15레벨 이상 사용 가능)\r\n"
									 + "5. 돌아가기\r\n"
									 + "-----------------------------------\r\n"
									 + "선택 > ";

			System.out.print(attackMenu);
			String attChoice = sc.nextLine();
	
			switch(attChoice) {
			case "1": 
				((Attackable) chaLists.get(choiceNo)).normalAttack(bossLists.get(boss));
				break;
			case "2": 
				System.out.print(specialAttackMenu);
				String attSpChoice = sc.nextLine();
				
					switch(attSpChoice) {
						case "1": 
							((Attackable) chaLists.get(choiceNo)).specialAttack1(bossLists.get(boss));
							break;
						case "2": 
							((Attackable) chaLists.get(choiceNo)).specialAttack2(bossLists.get(boss));
							break;
						case "3": 
							((Attackable) chaLists.get(choiceNo)).specialAttack3(bossLists.get(boss));
							break;
						case "4": 
							((Attackable) chaLists.get(choiceNo)).specialAttack4(bossLists.get(boss));
							break;
						case "5": 
							System.out.println("이전메뉴로 돌아갑니다.");
							return;
						default : 
							System.out.println("잘못 입력하셨습니다.");
					}
				break;
			case "3": 
				int escapeRnd = rnd.nextInt(100) + 1;
				if(escapeRnd >= 51) {
					System.out.println("도망치는 데에 성공했습니다.");
					AdventureMenu(choiceNo, choiceMap);
				}
				else {
					System.out.println("도망치지 못하였습니다.");
				}
				break;
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	
	public void MonstersAtttack(int choiceNo, int monsRandom) {
		int monsAttackType = rnd.nextInt(100) + 1; 

		if(monsAttackType >= 89) {
			((MoAttackable) monsLists.get(monsRandom)).moSpecialAttack1(chaLists.get(choiceNo));
		}
		else if(monsAttackType >= 80) {
			((MoAttackable) monsLists.get(monsRandom)).moSpecialAttack2(chaLists.get(choiceNo));
		}
		else if(monsAttackType >= 74) {
			((MoAttackable) monsLists.get(monsRandom)).moSpecialAttack3(chaLists.get(choiceNo));
		}
		else if(monsAttackType >= 71) {
			((MoAttackable) monsLists.get(monsRandom)).moSpecialAttack4(chaLists.get(choiceNo));
		}
		else if(monsAttackType <= 70) {
			((MoAttackable) monsLists.get(monsRandom)).moNormalAttack(chaLists.get(choiceNo));
		}
	}
	
	public void BossAtttack(int choiceNo, int boss) {
		int bossAttackType = rnd.nextInt(100) + 1; 

		if(bossAttackType >= 85) {
			((MoAttackable) bossLists.get(boss)).moSpecialAttack1(chaLists.get(choiceNo));
		}
		else if(bossAttackType >= 75) {
			((MoAttackable) bossLists.get(boss)).moSpecialAttack2(chaLists.get(choiceNo));
		}
		else if(bossAttackType >= 63) {
			((MoAttackable) bossLists.get(boss)).moSpecialAttack3(chaLists.get(choiceNo));
		}
		else if(bossAttackType >= 50) {
			((MoAttackable) bossLists.get(boss)).moSpecialAttack4(chaLists.get(choiceNo));
		}
		else if(bossAttackType <= 49) {
			((MoAttackable) bossLists.get(boss)).moNormalAttack(chaLists.get(choiceNo));
		}
	}
	
	public void MiniGameMenu(int choiceNo) {
		
		String miniGameMenu = "============== 미니게임 =============\r\n"
							+ "-----------------------------------\r\n"
							+ "1. 가위 바위 보\r\n"
							+ "2. 동전 던지기\r\n"
							+ "3. 돌아가기\r\n"
							+ "-----------------------------------\r\n"
							+ "선택 > ";
	
		while(true) {
			System.out.print(miniGameMenu);
			String mgChoice = sc.nextLine();
		
			switch(mgChoice) {
			case "1": 
				Rock_Scissor_PaperGame(choiceNo);
				break;
			case "2":
				CoinGame(choiceNo);
				break;
			case "3": 
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default : 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void CoinGame(int choiceNo) {
		System.out.println("===================================");
		System.out.println("동전 앞뒤 맞히기 게임");
		System.out.print("1. 앞 2. 뒤 : ");
		
		int userCoin = sc.nextInt();
		int comCoin = rnd.nextInt(2) + 1;
		sc.nextLine();
		System.out.println("-----------------------------------\n");
		// 결과
		String result = 
				userCoin == comCoin ?
						"당신이 이겼습니다.\n상금 50원을 획득하셨습니다!\n":
							"컴퓨터가 이겼습니다.";
		if(userCoin == comCoin) {
			chaLists.get(choiceNo).setCoinGameWinCount(chaLists.get(choiceNo).getCoinGameWinCount() + 1);
			int reward = chaLists.get(choiceNo).getMoney() + 50;
			chaLists.get(choiceNo).setMoney(reward);
		}
		System.out.println(result);
		System.out.println("===================================");
	}
	
	public void Rock_Scissor_PaperGame(int choiceNo) {
		
		
		System.out.println("===================================");
		System.out.println("가위-바위-보 게임입니다.");
		System.out.println("-----------------------------------\n");
		System.out.print("숫자를 선택하세요(1. 가위   2. 바위   3. 보  ) : \n");
		int playerNo = sc.nextInt();
		int computerNo = rnd.nextInt(3) + 1;
		sc.nextLine();
		if(playerNo != 1 && playerNo != 2 && playerNo != 3 ) {
			System.out.println("당신은 잘못 입력하셨습니다.");
			return;
		}
		
		String player = playerNo == 1 ? "가위" : (playerNo == 2 ? "바위" : "보"); 
		String computer = computerNo == 1 ? "가위" : (computerNo == 2 ? "바위" : "보"); 
		
		System.out.println("=============== 결과 ===============");
		System.out.println("당신은 " + player + "를 냈습니다");
		System.out.println("컴퓨터는 " + computer + "를 냈습니다");
		
		System.out.println("-----------------------------------\n");
		
		//실행결과
		if (computerNo == playerNo) {
			System.out.print("비겼습니다.\n"); 
			System.out.println("===================================");
		}
		else if ((computerNo == 1 && playerNo == 2)
					||(computerNo == 2 && playerNo == 3)
						||(computerNo == 3 && playerNo == 1)) {
			chaLists.get(choiceNo).setRockScissorPaperWinCount(chaLists.get(choiceNo).getRockScissorPaperWinCount() + 1);
			int reward = chaLists.get(choiceNo).getMoney() + 100;
			chaLists.get(choiceNo).setMoney(reward);
			System.out.print("당신이 이겼습니다.\n상금 100원을 획득하셨습니다! \n"); 
			System.out.println("===================================");			
		}
		else if ((computerNo == 1 && playerNo == 3)
					||(computerNo == 2 && playerNo == 1)
						||(computerNo == 3 && playerNo == 2)) {
			System.out.print("당신이 졌습니다.\n"); 
			System.out.println("=========================================");			
		}
	}
	
	public void HallOfFame(int choiceNo) {
		String hallOfFamePrint = "-----------------------------------\n"
							   + "============ 명예의 전당 =============\n"
							   + 	"종족: " + chaLists.get(choiceNo).getRace() + " \n"
							   + 	"이름: " + chaLists.get(choiceNo).getName() + " \n"
							   + 	"별명: " + chaLists.get(choiceNo).getNickName() + " \n"
							   +	"레벨: " + chaLists.get(choiceNo).getLevel() + " \n"
							   +	"파워: " + chaLists.get(choiceNo).getPower() + " \n"
							   +	"최대 체력: " + chaLists.get(choiceNo).getMaxEnergy() + " \n"
							   +	"전투 승리: " + chaLists.get(choiceNo).getBattleWinCount() + "회 \n"
							   +	"보스 레이드 승리: " + chaLists.get(choiceNo).getBossWinCount() + "회 \n"
							   +	"가위바위보 승리: " + chaLists.get(choiceNo).getRockScissorPaperWinCount() + "회 \n"
							   +	"동전 던지기 승리: " + chaLists.get(choiceNo).getCoinGameWinCount() + "회 \n" 
							   + "===================================\n" 
							   + "-----------------------------------\n";
		
		System.out.println(hallOfFamePrint);
	}
	
	public void GotoShop(int choiceNo) {
        int powerPotion = 500;
        int energyPotion = 700;
        int experiencePotion = 1000;
	    
        String PotionMenu = "============ 포션 상점 ===========\r\n"
                + "------------- 메 뉴 판 -----------------\r\n"
                + "1. 강화물약 (힘 +5) ---------------- 500원\r\n"
                + "2. 최대체력 증가 물약 (최대체력 +10)---- 700원\r\n"
                + "3. 경험치 물약 (경험치 +100)--------- 1000원\r\n"
                + "4. 나가기 \r\n"
                + "---------------------------------------\r\n"
                + "선택 > ";
        
        while(true) {
            int powerUp = 0;
            int maxEnergyUp = 0;
            int expUp = 0;
            int payment = 0;
 
            System.out.println(chaLists.get(choiceNo).wallet());
            System.out.print(PotionMenu);
            String potionChoice = sc.nextLine();
            
            switch (potionChoice) {
            case "1": 
            	if(powerPotion <= chaLists.get(choiceNo).getMoney()) {
            		powerUp = chaLists.get(choiceNo).getPower() + 5;
            		chaLists.get(choiceNo).setPower(powerUp);
            		payment = chaLists.get(choiceNo).getMoney() - 500;
            		chaLists.get(choiceNo).setMoney(payment);
            		System.out.println(chaLists.get(choiceNo).statusInfo());
            }
            else {
            	System.out.println("잔액이 부족합니다.");
            }
                break;
            
            case "2": 
            	if(energyPotion <= chaLists.get(choiceNo).getMoney()) {
            		maxEnergyUp = chaLists.get(choiceNo).getMaxEnergy() + 10;
            		chaLists.get(choiceNo).setMaxEnergy(maxEnergyUp);
            		payment = chaLists.get(choiceNo).getMoney() - 700;
            		chaLists.get(choiceNo).setMoney(payment);
            		System.out.println(chaLists.get(choiceNo).statusInfo());
            }
                else {
                	System.out.println("잔액이 부족합니다.");
                }
                break;
            
            case "3": 
            	if(experiencePotion <= chaLists.get(choiceNo).getMoney()) {        
            		expUp = chaLists.get(choiceNo).getExp() + 100;
            		chaLists.get(choiceNo).setExp(expUp);
            		payment = chaLists.get(choiceNo).getMoney() - 1000;
            		chaLists.get(choiceNo).setMoney(payment);
            		if(chaLists.get(choiceNo).getExp() > 100 * chaLists.get(choiceNo).getLevel()) {
						chaLists.get(choiceNo).levelUp();
					}
            		System.out.println(chaLists.get(choiceNo).statusInfo());
            }
                else {
                	System.out.println("잔액이 부족합니다.");
                }
                break;
                
            case "4":
                System.out.println("메인화면으로 돌아갑니다.");
                return;
                
            default :
                System.out.println("잘못된 입력 입니다.");
                break;
            }
        }
    }
	
	public void SaveFile() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("save/saveFile.txt"));
			Character saveInfo = chaLists.get(choiceNo);
			oos.writeObject(saveInfo);			
			System.out.println("저장 완료!");
			
		} catch(IOException  e) {
			e.printStackTrace();
		} finally{
            try{
            	oos.flush();
    			oos.close();
            } catch(Exception e){
            	e.printStackTrace();
            }
		}
	}
	
	public void LoadFile() {
		ObjectInputStream ois = null;
		try{
			ois = new ObjectInputStream(new FileInputStream("save/saveFile.txt"));
			Character loadCharacter = (Character)ois.readObject();
				choiceNo = loadCharacter.getOriginNo() - 1;
				chaLists.get(choiceNo).setRace(loadCharacter.getRace());
				chaLists.get(choiceNo).setOriginNo(loadCharacter.getOriginNo());
				chaLists.get(choiceNo).setName(loadCharacter.getName());
				chaLists.get(choiceNo).setNickName(loadCharacter.getNickName());
				chaLists.get(choiceNo).setLevel(loadCharacter.getLevel());
				chaLists.get(choiceNo).setExp(loadCharacter.getExp());
				chaLists.get(choiceNo).setPower(loadCharacter.getPower());
				chaLists.get(choiceNo).setMaxEnergy(loadCharacter.getMaxEnergy());
				chaLists.get(choiceNo).setEnergy(loadCharacter.getEnergy());
				chaLists.get(choiceNo).setCountExercise(loadCharacter.getCountExercise());
				chaLists.get(choiceNo).setSpecialAttack1Count(loadCharacter.getSpecialAttack1Count());
				chaLists.get(choiceNo).setSpecialAttack2Count(loadCharacter.getSpecialAttack2Count());
				chaLists.get(choiceNo).setSpecialAttack3Count(loadCharacter.getSpecialAttack3Count());
				chaLists.get(choiceNo).setSpecialAttack4Count(loadCharacter.getSpecialAttack4Count());
				chaLists.get(choiceNo).setBattleWinCount(loadCharacter.getBattleWinCount());
				chaLists.get(choiceNo).setRockScissorPaperWinCount(loadCharacter.getRockScissorPaperWinCount());
				chaLists.get(choiceNo).setCoinGameWinCount(loadCharacter.getCoinGameWinCount());
				chaLists.get(choiceNo).setMoney(loadCharacter.getMoney());
				
				System.out.println("불러오기 완료!");
				MainMenu();
		}catch(IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
            try{
    			ois.close();
            } catch(Exception e){
            	e.printStackTrace();
            }
		}
	}
}