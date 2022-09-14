package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Orger extends Monster implements MoAttackable{

	public Orger() {
		this.race = "Green Monster";
		this.name = "오우거";
		this.level = 15;
		this.power = 30;
		this.maxEnergy = 500;
		this.energy = 500;
		this.exp = 50;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.println("오우거가 일반공격 [몽둥이 휘두르기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.println("오우거가 특수공격 [발구르기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.println("오우거가 특수공격 [돌진]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 40;
		System.out.println("오우거가 특수공격 [나무 던지기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 60;
		System.out.println("오우거가 특수공격 [힘껏 몽둥이를 휘두르기]를 합니다.");
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
		System.out.printf("오우거가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}