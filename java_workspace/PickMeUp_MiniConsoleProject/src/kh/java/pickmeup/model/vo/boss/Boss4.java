package kh.java.pickmeup.model.vo.boss;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;
import kh.java.pickmeup.model.vo.monster.Monster;

public class Boss4 extends Monster implements MoAttackable {

	public Boss4() {
		this.race = "사막지대";
		this.name = "[샌드 드래곤]";
		this.level = 25;
		this.power = 60;
		this.maxEnergy = 800;
		this.energy = 800;
		this.exp = 120;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 0;
		System.out.println("[샌드 드래곤]이 일반공격 [메마른 광야]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 5;
		System.out.println("[샌드 드래곤]이 특수공격 [은신]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 10;
		System.out.println("[샌드 드래곤]이 특수공격 [모래폭풍]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 40;
		System.out.println("[샌드 드래곤]이 특수공격 [모래지옥]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 60;
		System.out.println("[샌드 드래곤]이 특수공격 [사하라 브레스]를 합니다.");
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
		System.out.printf("[샌드 드래곤]이 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}