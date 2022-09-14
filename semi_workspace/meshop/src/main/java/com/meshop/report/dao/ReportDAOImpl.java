package com.meshop.report.dao;

import static com.meshop.common.JdbcTemplate.close;
import static com.meshop.common.JdbcTemplate.getConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.meshop.product.entity.Product;
import com.meshop.report.entity.Report;

public class ReportDAOImpl implements ReportDAO{
    private Properties properties = new Properties();
	public ReportDAOImpl() {
		//생성됨과 동시에 쿼리 설정 파일 가져오기
		String filename = ReportDAOImpl.class.getResource("/sql/report-query.properties").getPath();
        try{
            properties.load(new FileReader(filename));

        }catch (IOException e){
            String message = e.getMessage();
        	System.out.println(message);
        }
        System.out.println("filename = " + filename);
	}

	@Override
	public List<Report> findByprodutId(String productId) {
		return null;
	}

	@Override
	public List<Report> findAll() {
        //준비
		Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // SQL
        String sql = properties.getProperty("findAll");
        System.out.println(sql);
        
        List<Report> list = new ArrayList<>();
        
        //DB 로직
        try {
        	pstmt = conn.prepareStatement(sql);
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
        		Report report = new Report();
        		Product p = new Product();
        		
        		p.setProductId(rs.getInt("product_id"));
        		p.setTitle(rs.getString("title"));
        		
        		report.setCount(rs.getInt("count"));
        		report.setProduct(p);
        		
        		list.add(report);
        	}
        }catch(SQLException e) {
        	String message = e.getMessage();
        	System.out.println(message);
        	
        }finally {
        	//자원 반납.
        	close(rs);
        	close(pstmt);
        	close(conn);
        }
        System.out.println(list.toString());
		return list;
	}
	
}
