package kh.java.pickmeup.model.vo.boss;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;
import kh.java.pickmeup.model.vo.monster.Monster;

public class Boss3 extends Monster implements MoAttackable {

	public Boss3() {
		this.race = "깊은 숲 보스";
		this.name = "[오크 대족장]";
		this.level = 21;
		this.power = 55;
		this.maxEnergy = 650;
		this.energy = 650;
		this.exp = 95;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 25;
		System.out.println("[오크 대족장]이 일반공격 [몽둥이 휘두르기]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 50;
		System.out.println("[오크 대족장]이 특수공격 [오크부족의 함성]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 75;
		System.out.println("[오크 대족장]이 특수공격 [전장의 뿔피리]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 110;
		System.out.println("[오크 대족장]이 특수공격 [버서커 모드]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 125;
		System.out.println("[오크 대족장]이 특수공격 [분노에 휩싸인 방망이]를 합니다.");
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
		System.out.printf("[오우거 대족장]이 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}
