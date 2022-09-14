package kh.java.pickmeup.model.vo.interfaces;

import kh.java.pickmeup.model.vo.monster.Monster;

public interface Attackable {

	// 캐릭터
	
	void normalAttack(Monster monster);
	
	void specialAttack1(Monster monster);
	
	void specialAttack2(Monster monster);
	
	void specialAttack3(Monster monster);
	
	void specialAttack4(Monster monster);
	
	void attackEnemy(Monster monster, int plusDamage);
	
}