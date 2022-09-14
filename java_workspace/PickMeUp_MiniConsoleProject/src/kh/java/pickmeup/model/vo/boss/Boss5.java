package kh.java.pickmeup.model.vo.boss;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;
import kh.java.pickmeup.model.vo.monster.Monster;

public class Boss5 extends Monster implements MoAttackable {

	public Boss5() {
		this.race = "미궁";
		this.name = "[지옥의 디아블로]";
		this.level = 30;
		this.power = 100;
		this.maxEnergy = 1000;
		this.energy = 1000;
		this.exp = 444;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 44;
		System.out.println("[지옥의 디아블로]가 일반공격 [사악한 숨결]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 66;
		System.out.println("[지옥의 디아블로]가 특수공격 [켈베로스 소환]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 120;
		System.out.println("[지옥의 디아블로]가 특수공격 [몸부림치는 절망]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 140;
		System.out.println("[지옥의 디아블로]가 특수공격 [암흑의 시선]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 160;
		System.out.println("[지옥의 디아블로]가 특수공격 [헬파이어]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}

	@Override
	public void moAttackEnemy(Character character, int plusDamage) {
		int damage = this.getPower() + plusDamage;
		int characterEnergy = (character.getEnergy() - damage);
		if(characterEnergy <= 0) {
			characterEnergy = 0;
		}
		character.setEnergy(characterEnergy);
		System.out.printf("[지옥의 디아블로]가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}
