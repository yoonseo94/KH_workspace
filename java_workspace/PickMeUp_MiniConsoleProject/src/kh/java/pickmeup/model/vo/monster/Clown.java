package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Clown extends Monster implements MoAttackable {

	public Clown() {
		this.race = "Demon";
		this.name = "어릿광대";
		this.level = 10;
		this.power = 27;
		this.maxEnergy = 300;
		this.energy = 300;
		this.exp = 33;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.printf("%s가 일반공격 [혼란]을 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.printf("%s가 특수공격 [깜짝베기]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.printf("%s가 특수공격 [야습]을 합니다.", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 15;
		System.out.printf("%s가 특수공격 [정신조작]을 합니다.", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 40;
		System.out.printf("%s가 특수공격 [트라우마 심기]를 합니다.", this.name);
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
		System.out.printf("어릿광대가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}	
}