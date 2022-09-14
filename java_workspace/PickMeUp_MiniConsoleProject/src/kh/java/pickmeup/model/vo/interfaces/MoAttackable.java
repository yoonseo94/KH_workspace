package kh.java.pickmeup.model.vo.interfaces;

import kh.java.pickmeup.model.vo.character.Character;

public interface MoAttackable {

	// 몬스터
	
	void moNormalAttack(Character character);
	
	void moSpecialAttack1(Character character);
	
	void moSpecialAttack2(Character character);
	
	void moSpecialAttack3(Character character);
	
	void moSpecialAttack4(Character character);
	
	void moAttackEnemy(Character character, int plusDamage);
	
}
