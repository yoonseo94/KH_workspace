package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Slaim extends Monster implements MoAttackable  {

	public Slaim() {
		this.race = "Liquid Monster";
		this.name = "슬라임";
		this.level = 1;
		this.power = 10;
		this.maxEnergy = 100;
		this.energy = 100;
		this.exp = 10;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.println("슬라임이 일반공격 [몸통박치기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.println("슬라임이 특수공격 [산성액 발사I]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.println("슬라임이 특수공격 [산성액 발사II]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 15;
		System.out.println("슬라임이 특수공격 [산성액 발사III]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 30;
		System.out.println("슬라임이 특수공격 [산성액 발사IV]를 합니다.");
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
		System.out.printf("슬라임이 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}