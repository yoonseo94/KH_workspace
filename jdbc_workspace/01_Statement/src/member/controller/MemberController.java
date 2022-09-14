package member.controller;

import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;

/**
 * controller클래스
 * - 신호등역할
 * - mvc흐름에서 가장 중심이 되는 클래스
 * - service단에 업무요청
 *
 */
public class MemberController {

	private MemberDao memberDao = new MemberDao();
	/**
	 * 사용자의 회원가입 요청을 처리하는 메소드
	 * 1. dao에 회원가입 요청후 int반환
	 * 2. menu에 int반환
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
		int result = memberDao.insertMember(member);
		return result;
	}
	public List<Member> selectAll() {
		List<Member> list = memberDao.selectAll();
		return list;
	}
	public Member selectOne(String id) {
		Member member = memberDao.selectOne(id);
		return member;
	}
	public List<Member> findMemberByName(String name) {
		List<Member> list = memberDao.findMemberByName(name);
		return list;
	}
	public int updateMember(Member member) {
		int result = memberDao.updateMember(member);
		return result;
	}
	public int deleteMember(Member member) {
		int result = memberDao.deleteMember(member);
		return result;
	}

}
