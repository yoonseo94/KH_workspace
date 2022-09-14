package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Stegosaurus extends Monster implements MoAttackable {

	public Stegosaurus() {
		this.race = "DINO Monster";
		this.name = "스테고사우르스";
		this.level = 13;
		this.power = 40;
		this.maxEnergy = 400;
		this.energy = 400;
		this.exp = 40;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.printf("%s가 일반공격 [돌격]를 합니다.%n" , this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 10;
		System.out.printf("%s가 특수공격 [기싸움]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 20;
		System.out.printf("%s가 특수공격 [낙엽후리기]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 25;
		System.out.printf("%s가 특수공격 [앞발치기]를 합니다.%n", this.name);
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 50;
		System.out.printf("%s가 특수공격 [진격의 꼬리철퇴]을 합니다.%n", this.name);
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
		System.out.printf("스테고사우르스가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}	
}