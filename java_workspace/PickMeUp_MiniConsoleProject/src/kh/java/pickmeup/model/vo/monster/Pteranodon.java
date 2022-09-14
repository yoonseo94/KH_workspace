package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Pteranodon extends Monster implements MoAttackable {

	public Pteranodon() {
		this.race = "DINO Monster";
		this.name = "프테라노돈";
		this.level = 9;
		this.power = 25;
		this.maxEnergy = 280;
		this.energy = 280;
		this.exp = 30;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.printf("%s가 일반공격 [찌르기]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.printf("%s가 특수공격 [낚아채기]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.printf("%s가 특수공격 [부리치기]를 합니다.", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 15;
		System.out.printf("%s가 특수공격 [비상후 낙하]를 합니다.", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 40;
		System.out.printf("%s가 특수공격 [협공]을 합니다.", this.name);
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
		System.out.printf("프테라노돈이 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}	
}