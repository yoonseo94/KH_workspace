package kh.java.pickmeup.model.vo.boss;

import kh.java.pickmeup.model.vo.character.Character;
import kh.java.pickmeup.model.vo.interfaces.MoAttackable;
import kh.java.pickmeup.model.vo.monster.Monster;

public class Boss2 extends Monster implements MoAttackable {

	public Boss2() {
		this.race = "초원지대 보스";
		this.name = "[초원의 지배자]";
		this.level = 22;
		this.power = 60;
		this.maxEnergy = 700;
		this.energy = 700;
		this.exp = 100;
	}
	
	public void moNormalAttack(Character character) {
		int plusDamage = 35;
		System.out.println("[초원의 지배자]가 일반공격 [지배자의 표효]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack1(Character character) {
		int plusDamage = 55;
		System.out.println("[초원의 지배자]가 특수공격 [토네이도]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack2(Character character) {
		int plusDamage = 85;
		System.out.println("[초원의 지배자]가 특수공격 [불타는 초원]을 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack3(Character character) {
		int plusDamage = 105;
		System.out.println("[초원의 지배자]가 특수공격 [다시태어난 초원의 시체들]를 합니다.");
		moAttackEnemy(character, plusDamage);
	}
	
	public void moSpecialAttack4(Character character) {
		int plusDamage = 125;
		System.out.println("[초원의 지배자]가 특수공격 [볼케이노]를 합니다.");
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
		System.out.printf("[초원의 지배자]가 %s에게 %d의 피해를 입혔습니다.%n", character.getNickName(), damage);
	}
}
