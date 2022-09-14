package member.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.exception.MemberException;
import member.model.vo.Member;
import member.model.vo.MemberDel;

public class MemberDao {
	
	Properties prop = new Properties();
	
	public MemberDao() {
		// member-query.properties의 내용 불러오기
		try {
			prop.load(new FileReader("resources/member-query.properties"));
			System.out.println("> member-query.properties 로드 완료!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Connection conn, Member member) {
		String sql = prop.getProperty("insertMember");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			// 1. PreparedStatement 생성 (미완성 sql & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			
			// 2. 실행 
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// 체크드예외를 언체크드예외로 전환해서 던지기
			throw new MemberException("insertMember 오류", e); // 원래 발생한 예외를 감싸서(전환) 다시 던지기 -> service 트랜잭션처리용
		} finally {
			// 3. 자원반납(pstmt) - conn 반환하지마세요(트랜잭션 처리전입니다)
			close(pstmt);
		}
		
		return result;
	}

	public List<Member> findMemberByName(Connection conn, String name) {
		String sql = prop.getProperty("findMemberByName");
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// 1. PreparedStatement 객체 생성(미완성sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%"); // where name like '%길동%'
			// 2. 실행 
			rset = pstmt.executeQuery();
			// 3. ResultSet처리 -> Member객체 변환
			while(rset.next()) {
				Member member = memberResultSetHandler(rset);
				list.add(member);
			}
		} catch (SQLException e) {
			throw new MemberException("findMemberByName 오류", e);
		} finally {
			// 4. 자원반납(pstmt, rset)
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<Member> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAll");
		List<Member> list = new ArrayList<>();
		
		try {
			// 1. PreparedStatment객체 생성 및 쿼리 완성
			pstmt = conn.prepareStatement(sql);
			
			// 2. 실행 및 ResultSet처리
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member member = memberResultSetHandler(rset);
				list.add(member);
			}
			
		} catch (SQLException e) {
			throw new MemberException("selectAll 조회오류", e);
		} finally {
			// 3. 자원반납(rset, pstmt)
			close(rset);
			close(pstmt);
		}
		return list;
	}

	
	
	public int deleteMember(Connection conn, String id) {
		String sql = prop.getProperty("deleteMember"); 
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("deleteMember 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 컬럼명을 동적으로 변경하는 기능은 PreparedStatement에서 지원하지 않는다.
	 * 
	 * @param conn
	 * @param id
	 * @param colName
	 * @param newValue
	 * @return
	 */
	public int updateMember(Connection conn, String id, String colName, Object newValue) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		sql = sql.replace("#", colName); // 컬럼명 설정 update member set email = ? where id = ?
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			// name, email, address : pstmt.setString(1, newValue)
			// birthday : pstmt.setDate(1, newValue)
			pstmt.setObject(1, newValue); // 상응하는 db타입값으로 자동 대입
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("updateMember 예외", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectOne(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String sql = prop.getProperty("selectOne");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = memberResultSetHandler(rset);
			}
		} catch (SQLException e) {
			throw new MemberException("selectOne 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return member;
	}

	private Member memberResultSetHandler(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setId(rset.getString("ID"));
		member.setName(rset.getString("NAME"));
		member.setGender(rset.getString("GENDER"));
		member.setBirthday(rset.getDate("BIRTHDAY"));
		member.setEmail(rset.getString("EMAIL"));
		member.setAddress(rset.getString("ADDRESS"));				
		member.setRegDate(rset.getTimestamp("REG_DATE"));
		return member;
	}

	public List<MemberDel> selectAllMemberDel(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllMemberDel");
		List<MemberDel> list = new ArrayList<>();
		
		try {
			// 1. PreparedStatment객체 생성 및 쿼리 완성
			pstmt = conn.prepareStatement(sql);
			
			// 2. 실행 및 ResultSet처리
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MemberDel member = new MemberDel();
				// 컬럼명 대소문자 구분하지 않는다.
				member.setId(rset.getString("ID"));
				member.setName(rset.getString("NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setBirthday(rset.getDate("BIRTHDAY"));
				member.setEmail(rset.getString("EMAIL"));
				member.setAddress(rset.getString("ADDRESS"));				
				member.setRegDate(rset.getTimestamp("REG_DATE"));
				member.setDelDate(rset.getTimestamp("DEL_DATE"));
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납(rset, pstmt)
			close(rset);
			close(pstmt);
		}
		return list;

	}

}
