package kh.java.mansuk_pc_cafe.run;

import kh.java.mansuk_pc_cafe.controller.MemberAccountManager;

/**
 * 실행용 클래스
 * 
 * @author 수진
 */
public class PcCafeMain {

	public static void main(String[] args) {
		MemberAccountManager mManager = new MemberAccountManager();
		mManager.mainMenu();
	}

}
