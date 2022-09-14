package member.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;

/**
 * service 클래스
 * 
 * ------------------------------------------ Service 시작
 * 1. 드라이버클래스 등록
 * 2. Connection객체 생성(setAutoCommit(false))
 * ------------------------------------------------ Dao 시작
 * 		3. PreparedStatement객체 생성(미완성 sql 값대입)
 * 		4. 실행 (및 ResultSet처리)
 * 		5. 자원반납(pstmt, rset)
 * ------------------------------------------------ Dao 끝
 * 6. 트랜잭션처리
 * 7. 자원반납 (conn)
 * ------------------------------------------ Service 끝
 *  
 *
 */
public class MemberService {
	
	private MemberDao memberDao = new MemberDao();

	public int insertMember(Member member) {
		int result = 0;
		// 1. Connection 생성
		Connection conn = getConnection();		
		try {
			// 2. dao 요청
			result = memberDao.insertMember(conn, member);
			// 3. 트랜잭션처리
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			// 4. 자원반납
			close(conn);			
		}
		return result;
	}

	public List<Member> findMemberByName(String name) {
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. dao요청
		List<Member> list = memberDao.findMemberByName(conn, name);
		// 3. 자원반납
		close(conn);
		return list;
	}
	public List<Member> selectAll() {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectAll(conn);
		close(conn);
		return list;
	}

	public int deleteMember(String id) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.deleteMember(conn, id);
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);			
		}
		return result;
	}

	public Member selectOne(String id) {
		Connection conn = getConnection();
		Member member = memberDao.selectOne(conn, id);
		close(conn);
		return member;
	}

	public int updateMember(String id, String colName, Object newValue) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMember(conn, id, colName, newValue);
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);			
		}
		return result;
	}

	
//	public int insertMember(Member member) {
//		Connection conn = null;
//		int result = 0;
//		try {
//			// 1. driver class 등록
//			Class.forName(driverClass);
//
//			// 2. Connection 생성(setAutoCommit(false))
//			conn = DriverManager.getConnection(url, user, password);
//			conn.setAutoCommit(false);
//			
//			// 3. dao 요청 및 리턴
//			result = memberDao.insertMember(conn, member);
//			
//			// 4. 트랜잭션처리
//			conn.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		} finally {
//			// 5. 자원반납
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}
	
	
	
}
