package com.meshop.member.service;

import static com.meshop.common.JdbcTemplate.close;
import static com.meshop.common.JdbcTemplate.commit;
import static com.meshop.common.JdbcTemplate.getConnection;
import static com.meshop.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.meshop.member.dao.MemberDAO;
import com.meshop.member.entity.Member;


public class MemberServiceImpl implements MemberService {
	
	MemberDAO memberDAO = new MemberDAO();

	@Override
	public int insertMember(Member member) {
		//회원가입
		
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDAO.insertMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	@Override
	public Member findMember(Member member) {
		Member m = null;
		//한명의 멤버를 찾는다.
		Connection conn = getConnection();
		try {
			m = memberDAO.findMember(conn, member);
		} catch(Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return m;
	}

	@Override
	public String findId(String name) {
		String result = "";
		Connection conn = getConnection();
		try {
			result = memberDAO.findId(conn, name);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	@Override
	public String findPw(String id) {
		String result = "";
		Connection conn = getConnection();
		try {
			result = memberDAO.findPw(conn, id);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	@Override
	public boolean doubleCheck(String memberId) {
		boolean result;
		Connection conn = getConnection();
		try {
			result = memberDAO.doubleCheck(conn, memberId);
		} catch(Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	@Override
	public boolean storeDoubleCheck(String storeName) {
		boolean result;
		Connection conn = getConnection();
		try {
			result = memberDAO.storeDoubleCheck(conn, storeName);
		} catch(Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	@Override
	public List<Member> findAllMember(){
		List<Member> list = new ArrayList<>();
		Connection conn = getConnection();
		
		list = memberDAO.findAllMember(conn);
		
		close(conn);
		return list;
	}

	@Override
	public int updateMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			memberDAO.updateMember(conn, member);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return 0;
	}
	

}
