package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Silverwolf extends Monster implements MoAttackable  {

	public Silverwolf() {
		this.race = "Wild Animal";
		this.name = "회색늑대";
		this.level = 7;
		this.power = 17;
		this.maxEnergy = 170;
		this.energy = 170;
		this.exp = 24;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.println("회색늑대가 일반공격 [할퀴기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.println("회색늑대가 특수공격 [물기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.println("회색늑대가 특수공격 [물어 뜯기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 15;
		System.out.println("회색늑대가 특수공격 [매복]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 30;
		System.out.println("회색늑대가 특수공격 [집단공격]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}

	@Override
	public void moAttackEnemy(Character character, int plusDamage) {
		int damage = this.getPower() + plusDamage;
		int characterEnergy = character.getEnergy() - damage;
		if(characterEnergy <= 0) {
			characterEnergy = 0;
		}
		character.setEnergy(characterEnergy);
		System.out.printf("회색늑대가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}