package member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

/**
 * dao클래스
 * - Database Access Object 
 * - db에 접근해서 요청/응답 처리
 * 
 * 1. driver class 등록
 * 2. Connection 생성
 * 3. PreparedStatement 생성
 * 4. 쿼리 실행 
 * 5. ResultSet 처리 / 트랜잭션처리
 * 6. 자원반납
 * 
 * 
 *
 */
public class MemberDao {
	
	String driverClass = "oracle.jdbc.OracleDriver"; // 드라이버클래스명
	String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 접속할 db서버주소 (db접속프로토콜@ip:port:sid)
	String user = "student";
	String password = "student";

	/**
	 * db에 접속, 쿼리를 실행하는 메소드
	 * 
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "insert into member values('yoogs', '유관순', 'F', to_date('19990909'), 'yoogs@gmail.com', '서울시 강남구', default)";
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, default)"; // 미완성 sql문
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);

			// 2. Connection 객체 생성(setAutoCommit(false))
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. PreparedStatement 객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			
			// 4. Statement실행 및 int(처리행수) 반환
			result = pstmt.executeUpdate();
			
			// 5.1 트랜잭션처리
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
			// 5.2 트랜잭션처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// 6. 자원반납
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

	/**
	 * member테이블의 모든 행을 리턴하는 메소드
	 * @return
	 */
	public List<Member> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member order by reg_date desc";
		List<Member> list = new ArrayList<>();
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);
			// 2. Connection 객체 생성
			conn = DriverManager.getConnection(url, user, password);
			// 3. PreparedStatement 객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			// 4. PreparedStatement 실행 및 ResultSet 반환
			rset = pstmt.executeQuery();
			// 5. ResultSet 한행씩 fetch. Member객체 전환 후 list에 추가
			// 한행씩 접근해 처리
			while(rset.next()) {
				String id = rset.getString("id");
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String address = rset.getString("address");
				Timestamp regDate = rset.getTimestamp("reg_date");
				Member member = new Member(id, name, gender, birthday, email, address, regDate);
				list.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public Member selectOne(String id) {
		String sql = "select * from member where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);
			// 2. Connection 객체 생성
			conn = DriverManager.getConnection(url, user, password);	
			// 3. PreparedStatement 객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4. PreparedStatement 실행 및 ResultSet 반환
			rset = pstmt.executeQuery();
			// 5. ResultSet 한행씩 fetch. Member객체 전환 후 list에 추가
			while(rset.next()) {
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String address = rset.getString("address");
				Timestamp regDate = rset.getTimestamp("reg_date");
				member = new Member(id, name, gender, birthday, email, address, regDate);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}

	public List<Member> findMemberByName(String name) {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where name like ?";
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member member = new Member();
				//컬럼명은 대소문자 구분이 없다.
				member.setId(rset.getString("id"));
				member.setName(rset.getString("name"));
				member.setGender(rset.getString("gender"));
				member.setBirthday(rset.getDate("birthday"));
				member.setEmail(rset.getString("email"));
				member.setAddress(rset.getString("address"));
				member.setRegDate(rset.getTimestamp("reg_date"));
				list.add(member);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update member "
				   + "set name = ?, birthday = ?, email = ?, address = ? "
				   + "where id = ?"; // 미완성 sql문
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);

			// 2. Connection 객체 생성(setAutoCommit(false))
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. PreparedStatement 객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setDate(2, member.getBirthday());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getId());
			
			// 4. Statement실행 및 int(처리행수) 반환
			result = pstmt.executeUpdate();
			
			// 5.1 트랜잭션처리
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
			// 5.2 트랜잭션처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// 6. 자원반납
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

	public int deleteMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete from member where id = ?"; // 미완성 sql문
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);

			// 2. Connection 객체 생성(setAutoCommit(false))
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. PreparedStatement 객체 생성(미완성 sql전달 & 값대입)
			pstmt.setString(1, member.getId());
			
			// 4. Statement실행 및 int(처리행수) 반환
			result = pstmt.executeUpdate();
			
			// 5.1 트랜잭션처리
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
			// 5.2 트랜잭션처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// 6. 자원반납
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

}




