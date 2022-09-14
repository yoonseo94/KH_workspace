package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Goblin extends Monster implements MoAttackable  {

	public Goblin() {
		this.race = "Green Monster";
		this.name = "고블린";
		this.level = 6;
		this.power = 16;
		this.maxEnergy = 160;
		this.energy = 160;
		this.exp = 20;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.println("고블린이 일반공격 [휘두르기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.println("고블린이 특수공격 [돌 던지기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.println("고블린이 특수공격 [오물 투척]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 15;
		System.out.println("고블린이 특수공격 [기습]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 30;
		System.out.println("고블린이 특수공격 [집단공격]을 합니다.");
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
		System.out.printf("고블린이 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}