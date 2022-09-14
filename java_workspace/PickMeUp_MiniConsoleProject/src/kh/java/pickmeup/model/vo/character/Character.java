package kh.java.pickmeup.model.vo.character;

import java.io.Serializable;

public abstract class Character implements Serializable {
	
	private static final long serialVersionUID = 100L;
	
	protected String race;  				// 종족
	protected int originNo;  				// 고유번호
	protected String name;					// 이름
	protected String nickName; 				// 별명
	protected int level;					// 레벨
	protected int exp;						// 경험치	
	protected int power;					// 파워
	protected int maxEnergy;				// 최대 체력
	protected int energy;					// 체력
	protected int countExercise;			// 레벨당 운동 횟수 제한 변수
	protected int specialAttack1Count;		// 스킬 횟수 제한 변수
	protected int specialAttack2Count;		// 스킬 횟수 제한 변수
	protected int specialAttack3Count;		// 스킬 횟수 제한 변수
	protected int specialAttack4Count;		// 스킬 횟수 제한 변수
	protected int battleWinCount;			// 전투 승리 횟수
	protected int rockScissorPaperWinCount; // 가위바위보 승리 횟수
	protected int coinGameWinCount;			// 동전 던지기 승리 횟수
	protected int money;					// 돈
	protected int bossWinCount;				// 보스전 승리 횟수
	
	public void levelUp() {
		if(exp > 100 * level) {
			this.level++;
			this.power += 10;
			this.maxEnergy += 25;
			this.energy = this.maxEnergy;
			this.exp = 0;
			this.countExercise = 5;
			this.specialAttack1Count = 30;
			this.specialAttack2Count = 15;
			this.specialAttack3Count = 10;
			this.specialAttack4Count = 5;
			System.out.printf("%s가 레벨업을 하여, %d레벨이 되었습니다.%n", name, level);
			if(this.level == 5) {
				System.out.println("축하합니다. 5레벨이 되어서 특수공격 []을 습득하였습니다.");
			}
			if(this.level == 10) {
				System.out.println("축하합니다. 10레벨이 되어서 특수공격 []를 습득하였습니다.");
			}
			if(this.level == 15) {
				System.out.println("축하합니다. 15레벨이 되어서 특수공격 []을 습득하였습니다.");
			}
		}
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

	public int getCoinGameWinCount() {
		return coinGameWinCount;
	}

	public void setCoinGameWinCount(int coinGameWinCount) {
		this.coinGameWinCount = coinGameWinCount;
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

	public abstract String statusInfo();

	public abstract String wallet();
	
	@Override
	public abstract String toString();
}