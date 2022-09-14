package member.controller;

import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * controller클래스
 * - service단 업무요청
 *
 */
public class MemberController {

	private MemberService memberService = new MemberService();
	
	public int insertMember(Member member) {
		int result = memberService.insertMember(member);
		return result;
	}

	public List<Member> findMemberByName(String name) {
		List<Member> list = memberService.findMemberByName(name);
		return list;
	}
	
	public List<Member> selectAll() {
		List<Member> list = memberService.selectAll();
		return list;
	}
	
	public int deleteMember(String id) {
		int result = memberService.deleteMember(id);
		return result; 
	}

	public Member selectOne(String id) {
		Member member = memberService.selectOne(id);
		return member;
	}

	public int updateMember(String id, String colName, Object newValue) {
		return memberService.updateMember(id, colName, newValue);
	}


}
