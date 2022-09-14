package member.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import member.model.dao.MemberDao;
import member.model.dto.Address;
import member.model.dto.Member;

public class MemberService {
	
	public static final int NUM_PER_PAGE = 10; // 한페이지에 표시할 컨텐츠수
	private MemberDao memberDao = new MemberDao();
	
	public List<Member> findAllMembers(Map<String, Object> pageBarPoint) {
		Connection conn = getConnection();
		List<Member> memberList = memberDao.findAllMembers(conn, pageBarPoint);
		close(conn);
		return memberList;
	}

	public int getTotalContents() {
		Connection conn = getConnection();
		int totalContents = memberDao.getTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public int updateMemberRole(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMemberRole(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Member findByMemberId(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.findByMemberId(conn, memberId);
		close(conn);
		return member;
	}

	public Member findByMemberEmail(String memberEmail) {
		Connection conn = getConnection();
		Member member = memberDao.findByMemberEmail(conn, memberEmail);
		close(conn);
		return member;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		int resultMember = 0;
		try {
			resultMember = memberDao.insertMember(conn, member);

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return resultMember;
	}

	public int insertMemberAddr(Address addressInfo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.insertMemberAddr(conn, addressInfo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Member findByMemberNickname(String memberNickname) {
		Connection conn = getConnection();
		Member member = memberDao.findByMemberNickname(conn, memberNickname);
		close(conn);
		return member;
	}

	public Member findMemberIdByEmail(String memberEmail) {
		Connection conn = getConnection();
		Member member = memberDao.findMemberIdByEmail(conn, memberEmail);
		close(conn);
		return member;
	}

	public int resetPasswordOfMember(Member updateMember) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.resetPasswordOfMember(conn, updateMember);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	public int updateMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try{
			result = memberDao.updateMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updatePassword(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updatePassword(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteMember(String member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.deleteMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Member> findBySomething(Map<String, String> searchParam) {
		Connection conn = getConnection();
		List<Member> searchList = memberDao.findBySomething(conn, searchParam);
		close(conn);
		return searchList;
	}

	public int getFindContents(Map<String, String> searchParam) {
		Connection conn = getConnection();
		int getFindContents = memberDao.getFindContents(conn, searchParam);
		close(conn);
		return getFindContents;
	}
	
}
