package com.meshop.product.dao;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.meshop.product.entity.Attachment;
import com.meshop.product.entity.ProductExt;

public interface ProductDAO {
	List<ProductExt> findAll();
	
	int insertProduct(Connection conn, ProductExt product);
	
	int insertProductBuy(Connection conn, ProductExt product);
	
	int findCurrentProductId(Connection conn);

	int insertAttachment(Connection conn, Attachment attach);
	
	int getTotalProducts(Connection conn);

	List<ProductExt> findAllOrderBy(Connection conn, Map<String, Object> param);

	ProductExt findOneByProductId(Connection conn, int productId);

	List<ProductExt> findByStatusPlace(Connection conn, Map<String, Object> param);

	List<ProductExt> findByStatus(Connection conn, Map<String, Object> param);

	List<ProductExt> findByPlace(Connection conn, Map<String, Object> param);

	int getStatusPlaceTotalProducts(Connection conn, Map<String, Object> param);

	int getStatusTotalProducts(Connection conn, Map<String, Object> param);

	int getPlaceTotalProducts(Connection conn, Map<String, Object> param);

	List<ProductExt> findAllByCategory(Connection conn, Map<String, Object> param);

	int getTotalProductsByCategory(Connection conn, String category);

	
	List<ProductExt> findByMemberId(Connection conn, String memberId);

	
}
