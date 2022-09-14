package customerservice.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import customerservice.model.dto.CsEmailLogExt;
import customerservice.model.dto.CsEmailImage;
import customerservice.model.exception.CustomerServiceException;
import product.model.dao.ProductDao;

public class CustomerServiceDao {
	
	private Properties prop = new Properties();
	
	public CustomerServiceDao() {
		String fileName = ProductDao.class.getResource("/sql/customerservice-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertEmailLog(Connection conn, CsEmailLogExt emailFormExt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertEmailLog");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emailFormExt.getName());
			pstmt.setString(2, emailFormExt.getEmail());
			pstmt.setString(3, emailFormExt.getTitle());
			pstmt.setString(4, emailFormExt.getContent());
			pstmt.setString(5, emailFormExt.getSelectType());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomerServiceException("이메일 전송 기록 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int findEmailFrmCurrentNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int no = 0;
		String sql = prop.getProperty("findEmailFrmCurrentNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next())
				no = rset.getInt(1);
			
		} catch (Exception e) {
			throw new CustomerServiceException("이메일 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}

	public int enrollEmailImages(Connection conn, CsEmailImage emailImage) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollEmailImages");
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emailImage.getCsEmailLogNo());
			pstmt.setString(2, emailImage.getOriginalFilename());
			pstmt.setString(3, emailImage.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new CustomerServiceException("이메일 첨부 이미지 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
}
