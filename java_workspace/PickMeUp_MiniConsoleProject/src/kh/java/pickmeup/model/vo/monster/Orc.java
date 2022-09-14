package kh.java.pickmeup.model.vo.monster;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;

public class Orc extends Monster implements MoAttackable {

	public Orc() {
		this.race = "Green Monster";
		this.name = "오크";
		this.level = 12;
		this.power = 20;
		this.maxEnergy = 250;
		this.energy = 250;
		this.exp = 30;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.println("오크가 일반공격 [검 휘두르기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.println("오크가 특수공격 [박치기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.println("오크가 특수공격 [물어 뜯기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 15;
		System.out.println("오크가 특수공격 [강타]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 40;
		System.out.println("오크가 특수공격 [야성의 일격]을 합니다.");
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
		System.out.printf("오크가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}	
}