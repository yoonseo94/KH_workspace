package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Indominus_rex extends Monster implements MoAttackable {
	
	public Indominus_rex() {
		this.race = "Hybrid Dinosaur";
		this.name = "인도미누스렉스";
		this.level = 14;
		this.power = 60;
		this.maxEnergy = 600;
		this.energy = 600;
		this.exp = 60;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 60;
		System.out.printf("%s가 일반공격 [사악한숨소리]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 65;
		System.out.printf("%s가 특수공격 [은신]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 70;
		System.out.printf("%s가 특수공격 [암살]를 합니다.", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 75;
		System.out.printf("%s가 특수공격 [난도질]를 합니다.", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 80;
		System.out.printf("%s가 특수공격 [도살]을 합니다.", this.name);
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
		System.out.printf("인도미누스렉스가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}	
}