package com.meshop.common;

import static com.meshop.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateTest {
	
	public void test() {
		Connection conn = getConnection();
		String sql = "select * from member";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("member_id");
				System.out.println(title);
			}
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
	}
	public static void main(String[] args) {
		JdbcTemplateTest test = new JdbcTemplateTest();
			test.test();
	}
}
