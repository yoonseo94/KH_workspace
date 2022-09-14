package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class SoldierAnt extends Monster implements MoAttackable{

	public SoldierAnt() {
		this.race = "Monster Insect";
		this.name = "병정개미";
		this.level = 5;
		this.power = 15;
		this.maxEnergy = 150;
		this.energy = 150;
		this.exp = 15;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.println("병정개미가 일반공격 [몸통박치기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.println("병정개미가 특수공격 [물어 뜯기]을 합니다.");
		moAttackEnemy(character, plusDamage); 
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.println("병정개미가 특수공격 [힘껏 박치기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 15;
		System.out.println("병정개미가 특수공격 [산성액 발사]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 30;
		System.out.println("병정개미가 특수공격 [집단공격]을 합니다.");
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
		System.out.printf("병정개미가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}