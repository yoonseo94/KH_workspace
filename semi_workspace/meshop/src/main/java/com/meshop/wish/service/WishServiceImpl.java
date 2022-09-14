package com.meshop.wish.service;

import static com.meshop.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.meshop.wish.dao.WishDAO;
import com.meshop.wish.dao.WishDAOImpl;

public class WishServiceImpl implements WishService{
	WishDAO wishDAO = new WishDAOImpl();
	@Override
	public List<Integer> findByMemberId(String memberId) {
		Connection conn = getConnection();
		List<Integer> list = new ArrayList<>();
		try {
			list = wishDAO.findByMemberId(conn, memberId);
			
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}
		return list;
	}
	@Override
	public int insertWish(String memberId, int productId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = wishDAO.insertWish(conn, memberId, productId);
			commit(conn);
			
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	@Override
	public int deleteWish(String memberId, int productId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = wishDAO.deleteWish(conn, memberId, productId);
			commit(conn);
			
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

}
