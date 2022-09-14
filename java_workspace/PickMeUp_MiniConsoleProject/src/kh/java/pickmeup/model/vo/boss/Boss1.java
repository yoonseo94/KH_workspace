package kh.java.pickmeup.model.vo.boss;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;
import kh.java.pickmeup.model.vo.monster.Monster;

public class Boss1 extends Monster implements MoAttackable{

	public Boss1() {
		this.race = "초심자의 숲 보스";
		this.name = "[숲의 타락한 왕]";
		this.level = 20;
		this.power = 50;
		this.maxEnergy = 700;
		this.energy = 700;
		this.exp = 100;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 30;
		System.out.println("[숲의 타락한 왕]이 일반공격 [깊은숲의 한기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 50;
		System.out.println("[숲의 타락한 왕]이 특수공격 [절명의 곡소리]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 80;
		System.out.println("[숲의 타락한 왕]이 특수공격 [스산한 안개]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 100;
		System.out.println("[숲의 타락한 왕]이 특수공격 [스피릿 오브 포레스트]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 120;
		System.out.println("[숲의 타락한 왕]이 특수공격 [영혼뺏기]를 합니다.");
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
		System.out.printf("[숲의 타락한 왕]이 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}
	
