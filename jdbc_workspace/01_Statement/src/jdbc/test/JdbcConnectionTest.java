package jdbc.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnectionTest {
	
	String driverClass = "oracle.jdbc.OracleDriver"; // 드라이버클래스명
	String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 접속할 db서버주소 (db접속프로토콜@ip:port:sid)
	String user = "student";
	String password = "student";

	public static void main(String[] args) {
		JdbcConnectionTest instance = new JdbcConnectionTest();
//		instance.test1();
		instance.test2();
	}

	/**
	 * DQL
	 * - select
	 */
	public void test1() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// 1. jdbc driver class 등록
			Class.forName(driverClass);
			System.out.println("> driver class 등록 완료!");
			
			// 2. Connection객체 생성 (url, user, password)
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("> Connection객체 생성 성공!");
			
			// 3. Statement객체(쿼리실행객체) 생성
			stmt = conn.createStatement();
			System.out.println("> Statement객체 생성 성공!");
			
			// 4. Statement실행(sql전달)(executeQuery) - ResultSet객체 반환
			String sql = "select * from member";
			rset = stmt.executeQuery(sql);
			System.out.println("> Statement실행 및 ResultSet 반환 성공!");
			
			// 5. ResultSet을 1행씩 열람
			// 다음행이 존재하면 true 리턴
			while(rset.next()) {
				String id = rset.getString("id");
				String name = rset.getString("name");
				Date birthday = rset.getDate("birthday");
				System.out.printf("%s\t%s\t%s%n", id, name, birthday);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			// 객체생성 역순
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("> 자원반납 성공!");
		}
	}
	
	/**
	 * DML
	 * - insert
	 * - update
	 * - delete
	 */
	public void test2() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 1. jdbc driver class 등록
			Class.forName(driverClass);
			
			// 2. Connection객체 생성 (url, user, password)
			// setAutoCommit(false) : 코드로써 트랜잭션을 직접 관리하기 위한 설정
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. Statement객체(쿼리실행객체) 생성
			stmt = conn.createStatement();
			
			// 4. Statement실행(executeUpdate) - int리턴(처리된 행수)
			String sql = "update member set name = '고길동' where id = 'honngd'";
			int result = stmt.executeUpdate(sql);
			System.out.println("> " + result + "행이 수정되었습니다.");
			
			// 5.1 트랜잭션처리
			if(result > 0)
				conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// 5.2 트랜잭션처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
