package com.meshop.product.service;

import java.util.List;
import java.util.Map;

import com.meshop.product.entity.ProductExt;

public interface ProductService {
	List<ProductExt> findAll();
	
	int insertProduct(ProductExt product);
	
	int insertProductBuy(ProductExt product);
	
	int getTotalProducts();
	
	// product List
	int NUM_PER_PAGE = 12;
	List<ProductExt> findAllOrderBy(Map<String, Object> param);

	ProductExt findOneByProductId(int productId);

	List<ProductExt> findByStatusPlace(Map<String, Object> param);

	List<ProductExt> findByStatus(Map<String, Object> param);

	List<ProductExt> findByPlace(Map<String, Object> param);

	int getStatusPlaceTotalProducts(Map<String, Object> param);

	int getStatusTotalProducts(Map<String, Object> param);

	int getPlaceTotalProducts(Map<String, Object> param);

	List<ProductExt> findAllByCategory(Map<String, Object> param);

	int getTotalProductsByCategory(String category);
	
	List<ProductExt> findByMemberId(String memberId);
}
