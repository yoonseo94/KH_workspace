package cart.model.dao;

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

import cart.model.dto.Cart;
import cart.model.exception.CartException;
import member.model.dto.Member;
import member.model.dto.MemberRole;
import product.model.exception.ProductException;

public class CartDao {
	
	private Properties prop = new Properties();

	public CartDao() {
		String fileName = CartDao.class.getResource("/sql/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Cart> findCartsByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Cart> list = new ArrayList<>();
		String sql = prop.getProperty("findCartsByMemberId");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Cart cart = handleCartResultSet(rset);	
				list.add(cart);
			}
		} catch (Exception e) {
			throw new CartException("장바구니 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	private Cart handleCartResultSet(ResultSet rset) throws SQLException {
		Cart cart = new Cart();
		cart.setCartNo(rset.getInt("cart_no"));
		cart.setMemberId(rset.getString("member_id"));
		cart.setProductId(rset.getString("product_id"));
		cart.setProductCount(rset.getInt("product_count"));
		return cart;
	}

	public int deleteCartByProductId(Connection conn, String memberId, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println("DAO@ memberId, productId" + memberId + productId);
		String sql = prop.getProperty("deleteCart");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, productId);
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			throw new CartException("장바구니 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}	
	

}
