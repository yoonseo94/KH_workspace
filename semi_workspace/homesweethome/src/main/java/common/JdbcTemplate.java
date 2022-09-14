package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTemplate {
	
	static String driverClass;
	static String url;
	static String user;
	static String password;
	
	static {
		// datasource.properties에서 설정정보 가져오기
		Properties prop = new Properties();
		try {
			String fileName = JdbcTemplate.class.getResource("/datasource.properties").getPath();
			prop.load(new FileReader(fileName));
			System.out.println(JdbcTemplate.class.getResource("/datasource.properties").getPath());
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			// 1. driver class 등록 
			Class.forName(driverClass);
//			System.out.println("시작 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
//			System.out.println("시작 실패");
		}
		
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 2. Connection 생성(url, user, password) - setAutoCommit(false)
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
//			System.out.println("connection 성공");
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("connection 실패");
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
//			System.out.println("conn close 성공");
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("conn close 실패");
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && !pstmt.isClosed())
				pstmt.close();
//			System.out.println("pstmt close 성공");
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("pstmt close 실패");
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
//			System.out.println("rset close 성공");
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("rset close 실패");
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
//			System.out.println("conn commit 성공");
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("conn commit 실패");
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
//			System.out.println("conn rollback 성공");
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("conn rollback 실패");
		}
	}
}