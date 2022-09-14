package com.meshop.product.service;

import static com.meshop.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.meshop.product.dao.ProductDAO;
import com.meshop.product.dao.ProductDAOImpl;
import com.meshop.product.entity.Attachment;
import com.meshop.product.entity.ProductExt;

public class ProductServiceImpl implements ProductService{
	
	ProductDAO productDAO = new ProductDAOImpl();
	@Override
	public List<ProductExt> findAll() {
		return productDAO.findAll();
	}

	@Override
	public int insertProduct(ProductExt product) {
		int result = 0;
		Connection conn = getConnection();
		try {
			// 1. board 등록
			result = productDAO.insertProduct(conn, product);
			
			// 2. board pk 가져오기
			int no = productDAO.findCurrentProductId(conn);
			System.out.println("product current no = " + no);
			
			// 3. attachment에 등록
			List<Attachment> attachments = product.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					System.out.println("hi");
					attach.setProductId(no);
					result = productDAO.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	@Override
	public int insertProductBuy(ProductExt product) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = productDAO.insertProductBuy(conn, product);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}
	
	@Override
	public List<ProductExt> findAllOrderBy(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ProductExt> productList = productDAO.findAllOrderBy(conn, param);
		close(conn);
		return productList;
	}
	
	@Override
	public int getTotalProducts() {
		Connection conn = getConnection();
		int totalProducts = productDAO.getTotalProducts(conn);
		close(conn);
		return totalProducts;
	}
	
	@Override
	public int getStatusPlaceTotalProducts(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalProducts = productDAO.getStatusPlaceTotalProducts(conn, param);
		close(conn);
		return totalProducts;
	}
	
	@Override
	public int getStatusTotalProducts(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalProducts = productDAO.getStatusTotalProducts(conn, param);
		close(conn);
		return totalProducts;
	}
	
	@Override
	public int getPlaceTotalProducts(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalProducts = productDAO.getPlaceTotalProducts(conn, param);
		close(conn);
		return totalProducts;
	}
	
	@Override
	public ProductExt findOneByProductId(int productId) {
		Connection conn = getConnection();
		ProductExt product = productDAO.findOneByProductId(conn, productId);
		close(conn);
		return product;
	}

	@Override
	public List<ProductExt> findByStatusPlace(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ProductExt> productList = productDAO.findByStatusPlace(conn, param);
		close(conn);
		return productList;
	}

	@Override
	public List<ProductExt> findByStatus(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ProductExt> productList = productDAO.findByStatus(conn, param);
		close(conn);
		return productList;
	}

	@Override
	public List<ProductExt> findByPlace(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ProductExt> productList = productDAO.findByPlace(conn, param);
		close(conn);
		return productList;
	}
	
	@Override
	public List<ProductExt> findAllByCategory(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ProductExt> productList = productDAO.findAllByCategory(conn, param);
		close(conn);
		return productList;
	}

	@Override
	public int getTotalProductsByCategory(String category) {
		Connection conn = getConnection();
		int totalProducts = productDAO.getTotalProductsByCategory(conn, category);
		close(conn);
		return totalProducts;
	}
	
	@Override
	public List<ProductExt> findByMemberId(String memberId) {
		Connection conn = getConnection();
		
		List<ProductExt> productList;
		productList = productDAO.findByMemberId(conn, memberId);
		close(conn);
		
		return productList;
	}
}
