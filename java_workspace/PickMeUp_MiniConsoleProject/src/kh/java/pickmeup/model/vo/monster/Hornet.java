package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Hornet extends Monster implements MoAttackable  {

	public Hornet() {
		this.race = "곤충";
		this.name = "말벌";
		this.level = 3;
		this.power = 13;
		this.maxEnergy = 130;
		this.energy = 130;
		this.exp = 13;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.println("말벌이 일반공격 [날개짓]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.println("말벌이 특수공격 [찌르기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.println("말벌이 특수공격 [두번 찌르기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 15;
		System.out.println("말벌이 특수공격 [세번 찌르기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 30;
		System.out.println("말벌이 특수공격 [맹독 찌르기]를 합니다.");
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
		System.out.printf("말벌이 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}