package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Dragon extends Monster implements MoAttackable {

	public Dragon() {
		this.race = "Dragon";
		this.name = "드래곤";
		this.level = 15;
		this.power = 70;
		this.maxEnergy = 700;
		this.energy = 700;
		this.exp = 70;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 50;
		System.out.printf("%s가 일반공격 [고공낙하]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 65;
		System.out.printf("%s가 특수공격 [드래곤피어]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 70;
		System.out.printf("%s가 특수공격 [눈부신 오라]를 합니다.", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 80;
		System.out.printf("%s가 특수공격 [마지막 숨결]를 합니다.", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 100;
		System.out.printf("%s가 특수공격 [드래곤브레스]를 합니다.", this.name);
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
		System.out.printf("드래곤이 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}	
}