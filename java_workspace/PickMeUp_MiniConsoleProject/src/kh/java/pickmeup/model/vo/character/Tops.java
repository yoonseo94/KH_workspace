package kh.java.pickmeup.model.vo.character;

import java.io.Serializable;

import kh.java.pickmeup.model.vo.interfaces.Attackable;
import kh.java.pickmeup.model.vo.interfaces.DailyRoutinable;
import kh.java.pickmeup.model.vo.monster.Monster;

public class Tops extends Character implements Attackable, DailyRoutinable, Serializable {

	private static final long serialVersionUID = 9L;
	
	public Tops() {
		this.race = "Dinosaur";
		this.originNo = 9;
		this.name = "트리케라톱스";
		this.nickName = "톱스";
		this.level = 1;
		this.exp = 0;
		this.power = 25;
		this.maxEnergy = 350;
		this.energy = 350;
		this.countExercise = 5;
		this.specialAttack1Count = 30;
		this.specialAttack2Count = 15;
		this.specialAttack3Count = 10;
		this.specialAttack4Count = 5;
		this.battleWinCount = 0;			
		this.rockScissorPaperWinCount = 0; 
		this.coinGameWinCount = 0;	
		this.money = 1000;
		this.bossWinCount = 0;
	}
	
	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public int getOriginNo() {
		return originNo;
	}

	public void setOriginNo(int originNo) {
		this.originNo = originNo;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getCountExercise() {
		return countExercise;
	}

	public void setCountExercise(int countExercise) {
		this.countExercise = countExercise;
	}
	
	public int getSpecialAttack1Count() {
		return specialAttack1Count;
	}

	public void setSpecialAttack1Count(int specialAttack1Count) {
		this.specialAttack1Count = specialAttack1Count;
	}

	public int getSpecialAttack2Count() {
		return specialAttack2Count;
	}

	public void setSpecialAttack2Count(int specialAttack2Count) {
		this.specialAttack2Count = specialAttack2Count;
	}

	public int getSpecialAttack3Count() {
		return specialAttack3Count;
	}

	public void setSpecialAttack3Count(int specialAttack3Count) {
		this.specialAttack3Count = specialAttack3Count;
	}

	public int getSpecialAttack4Count() {
		return specialAttack4Count;
	}

	public void setSpecialAttack4Count(int specialAttack4Count) {
		this.specialAttack4Count = specialAttack4Count;
	}
	
	public int getBattleWinCount() {
		return battleWinCount;
	}

	public void setBattleWinCount(int battleWinCount) {
		this.battleWinCount = battleWinCount;
	}

	public int getRockScissorPaperWinCount() {
		return rockScissorPaperWinCount;
	}

	public void setRockScissorPaperWinCount(int rockScissorPaperWinCount) {
		this.rockScissorPaperWinCount = rockScissorPaperWinCount;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getBossWinCount() {
		return bossWinCount;
	}

	public void setBossWinCount(int bossWinCount) {
		this.bossWinCount = bossWinCount;
	}
	
	@Override
	public void levelUp() {
			this.level++;
			this.power += 8;
			this.maxEnergy += 35;
			this.energy = this.maxEnergy;
			this.exp = 0;
			this.countExercise = 5;
			this.specialAttack1Count = 30;
			this.specialAttack2Count = 15;
			this.specialAttack3Count = 10;
			this.specialAttack4Count = 5;
			System.out.printf("%s가 레벨업을 하여, %d레벨이 되었습니다.%n", this.nickName, this.level);
			if(this.level == 5) {
				System.out.println("축하합니다. 5레벨이 되어서 특수공격 [뭉개기]을 습득하였습니다.");
			}
			if(this.level == 10) {
				System.out.println("축하합니다. 10레벨이 되어서 특수공격 [육탄방어]을 습득하였습니다.");
			}
			if(this.level == 15) {
				System.out.println("축하합니다. 15레벨이 되어서 특수공격 [최후의 뿔]를 습득하였습니다.");
			}
	}
	
	@Override
	public void rest() { 	// 휴식
		this.energy += 30;
		if (this.energy >= this.maxEnergy) {
			this.energy = this.maxEnergy;
			System.out.println("에너지가 최대치입니다. 더이상 휴식할 수 없습니다.");
			return;
		}
		this.specialAttack1Count += 5;
		if(this.specialAttack1Count >= 30) {
			this.specialAttack1Count = 30;
		}
		this.specialAttack2Count += 5;
		if(this.specialAttack2Count >= 15) {
			this.specialAttack2Count = 15;
		}
		this.specialAttack3Count += 5;
		if(this.specialAttack3Count >= 10) {
			this.specialAttack3Count = 10;
		}
		this.specialAttack4Count += 5;
		if(this.specialAttack4Count >= 5) {
			this.specialAttack4Count = 5;
		}
		System.out.printf("%s이/가 휴식을 취합니다. 에너지가 30만큼 회복되었고, 각각의 특수기술 횟수가 5만큼 회복되었습니다.%n", this.nickName);
	}
	
	@Override
	public void exercise() { // 운동하기
		this.countExercise--;
		if(this.countExercise <= 0) {
			System.out.printf("%s이/가 지쳐서 더이상의 운동을 할 수 없습니다.%n", this.nickName);
			return;
		}
		this.power += 5;
		this.energy -= 20;
		System.out.printf("%s이/가 운동을 합니다. 힘이 5만큼 세졌습니다.%n", this.nickName);
		System.out.println("격렬한 운동으로 에너지가 20만큼 소모되었습니다.");
		System.out.println("남은 사용횟수: " + countExercise + "/5회(레벨당)");
	}
	
	@Override
	public void normalAttack(Monster monster) {
		int plusDamage = 0;
		System.out.printf("%s이/가 누르기 공격을 합니다.%n", this.nickName);
		attackEnemy(monster, plusDamage);
	}
	
	@Override
	public void specialAttack1(Monster monster) {
		this.specialAttack1Count--;
		if(this.specialAttack1Count <= 0) {
			System.out.println("사용할 수 있는 횟수를 모두 사용하셨습니다.");
			return;
		}
		int plusDamage = (int)(25 + (1.5 * this.level));
		System.out.printf("%s이/가 특수공격 [짓밟기]을 사용합니다.%n", this.nickName);
		System.out.println("남은 사용횟수: " + specialAttack1Count + "/30회");
		attackEnemy(monster, plusDamage);
	}
	
	@Override
	public void specialAttack2(Monster monster) {
		if(this.level >= 5) {
			this.specialAttack2Count--;
			if(this.specialAttack2Count <= 0) {
				System.out.println("사용할 수 있는 횟수를 모두 사용하셨습니다.");
				return;
			}
			int plusDamage = (50 + (2 * this.level));
			System.out.printf("%s이/가 특수공격 [뭉개기]을 사용합니다.%n", this.nickName);
			System.out.println("남은 사용횟수: " + specialAttack2Count + "/15회");
			attackEnemy(monster, plusDamage);
		}
		else {
			System.out.println("5레벨부터 사용할 수 있습니다.");
		}
	}
	
	@Override
	public void specialAttack3(Monster monster) {
		if(this.level >= 10) {
			this.specialAttack3Count--;
			if(this.specialAttack3Count <= 0) {
				System.out.println("사용할 수 있는 횟수를 모두 사용하셨습니다.");
				return;
			}
			int plusDamage = (100 + (3 * this.level));
			System.out.printf("%s이/가 특수공격 [육탄방어]을 사용합니다.%n", this.nickName);
			System.out.println("남은 사용횟수: " + specialAttack3Count + "/10회");
			attackEnemy(monster, plusDamage);
			}
		else {
			System.out.println("10레벨부터 사용할 수 있습니다.");
		}
	}
	
	@Override
	public void specialAttack4(Monster monster) {
		if(this.level >= 15) {
			this.specialAttack4Count--;
			if(this.specialAttack4Count <= 0) {
				System.out.println("사용할 수 있는 횟수를 모두 사용하셨습니다.");
				return;
			}
			int plusDamage = (200 + (5 * this.level));
			System.out.printf("%s이/가 특수공격 [최후의 뿔]을 사용합니다.%n", this.nickName);
			System.out.println("남은 사용횟수: " + specialAttack4Count + "/5회");	
			attackEnemy(monster, plusDamage);
			}
		else {
			System.out.println("15레벨부터 사용할 수 있습니다.");
		}
	}
	
	@Override
	public void attackEnemy(Monster monster, int plusDamage) {
		int damage = this.getPower() + plusDamage;
		int monsterEnergy = (monster.getEnergy() - damage);
		monster.setEnergy(monsterEnergy);
		System.out.printf("%s이/가 %s에게 %d의 피해를 입혔습니다.%n", this.nickName, monster.getName(), damage);
	}

	@Override
	public String statusInfo() {
		return 		"------------------------------------\n"
				+	"=============== 상태창 ===============\n"
				+	"종족: " + this.race + " \n"
				+	"이름: " + this.name + " \n"
				+	"별명: " + this.nickName + " \n"
				+	"레벨: " + this.level+ " \n"
				+	"경험치: " + this.exp+ " \n"
				+	"파워: " + this.power+ " \n"
				+	"최대 체력: " + this.maxEnergy+ " \n"
				+	"현재 체력: "+ this.energy+ " \n"
				+	"소지금: "+ this.money+ "원 \n"
				+	"====================================\n" 
				+ 	"------------------------------------\n";
	}
	
	@Override
	public String wallet() {
		return " 소지금액 : " + this.money + "\n";
	}
	
	@Override
	public String toString() {
		return race + " , " + originNo + " , " + name + " , " + nickName
				+ " , " + level + " , " + exp + " , " + power + " , " + maxEnergy + " , "
				+ energy + " , " + countExercise + " , " + specialAttack1Count
				+ " , " + specialAttack2Count + " , " + specialAttack3Count
				+ " , " + specialAttack4Count;
	}
}