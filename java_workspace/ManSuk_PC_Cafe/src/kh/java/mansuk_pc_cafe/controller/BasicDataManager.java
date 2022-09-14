package kh.java.mansuk_pc_cafe.controller;

import java.util.ArrayList;

import kh.java.mansuk_pc_cafe.vo.MemberAccount;

/**
 * 관리자 기본 계정 추가
 * 
 * @author 수진
 */
public class BasicDataManager {
	
	public static void main(String[] args) {
		
		ArrayList<MemberAccount> memberList = new ArrayList<>();
		memberList.add(new MemberAccount("관리자", "admin1", "admin1234"));
		DataManager.saveMember("memberList.ser", memberList);
	}

}
