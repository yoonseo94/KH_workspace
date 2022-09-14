package com.meshop.wish.dao;

import java.sql.Connection;
import java.util.List;

public interface WishDAO {
	List<Integer> findByMemberId(Connection conn, String memberId);
	int insertWish(Connection conn, String memberId, int productId);
	int deleteWish(Connection conn, String memberId, int productId);
}
