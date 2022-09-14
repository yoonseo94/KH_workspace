
package com.meshop.member.service;

import java.util.List;
import java.util.Map;

import com.meshop.member.entity.Member;

public interface MemberService {

	int insertMember(Member member);	
	
	Member findMember(Member member);
	
	String findId(String name);
	
	String findPw(String id);
	
	boolean doubleCheck(String id);
	boolean storeDoubleCheck(String storeName);
	
	List<Member> findAllMember();
	
	int updateMember(Member member);
}

