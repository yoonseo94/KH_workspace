package com.kh.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.member.model.dto.Member;

/**
 * 
 * #8. Dao구현클래스 없이 mapper연결하기
 *
 */
@Mapper
public interface MemberDao {

	int insertMember(Member member);

	Member selectOneMember(String memberId);

	int updateMember(Member member);

	int insertAuthority(Member member);

	List<Member> selectMemberList();

	int deleteMemeberRole(String memberId);

	int insertAuthority(Map<String, Object> param);

	Member findByEmail(String email);

	Member findByMemberIdAndEmail(Member member);

	int updatePassword(Member member);
	
}
