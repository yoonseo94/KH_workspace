package com.kh.product.controller;

import java.util.List;

import com.kh.product.model.exception.InsufficientOutputAmountException;
import com.kh.product.model.exception.ProductException;
import com.kh.product.model.service.ProductService;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductIO;

public class ProductController {

	private ProductService productService = new ProductService();

	public List<Product> selectProductList() {
		List<Product> list = null;
		try {
			list = productService.selectProductList();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("관리자에게 문의하세요 - " + e.getMessage());
		}
		return list;
	}

	public int insertProduct(Product product) {
		int result = 0;
		try {
			result = productService.insertProduct(product);
		} catch (ProductException e) {
			e.printStackTrace();
			System.err.println("관리자에게 문의하세요 - " + e.getMessage());
		}
		return result;
	}

	public int insertProductIO(ProductIO pio) {
		int result = 0;

		try {
			result = productService.insertProductIO(pio);
		} catch (InsufficientOutputAmountException e) {
			System.err.println(e.getMessage());
		} catch (ProductException e) {
			e.printStackTrace();
			System.err.println("관리자에게 문의하세요 - " + e.getMessage());
		}
		return result;
	}

	public List<ProductIO> selectProductIOList(String productId) {
		List<ProductIO> pioList = null;
		try {
			pioList = productService.selectProductIOList(productId);
		} catch (ProductException e) {
			e.printStackTrace();
			System.err.println("관리자에게 문의하세요 - " + e.getMessage());
		}
		return pioList;
	}

	public Product selectOneProduct(String productId) {
		Product product = null;
		try {
			product = productService.selectOneProduct(productId);
		} catch (ProductException e) {
			e.printStackTrace();
			System.err.println("관리자에게 문의하세요 - " + e.getMessage());
		}
		return product;
	}

	public List<Product> searchProductBy(String column, String keyword) {
		List<Product> list = null;
		try {
			list = productService.searchProductBy(column, keyword);
		} catch (ProductException e) {
			e.printStackTrace();
			System.err.println("관리자에게 문의하세요 - " + e.getMessage());
		}
		return list;
	}

	public int updateProduct(Product product) {
		int result = 0;
		try {
			result = productService.updateProduct(product);
		} catch (ProductException e) {
			e.printStackTrace();
			System.err.println("관리자에게 문의하세요 - " + e.getMessage());
		}
		return result;
	}

	public int deleteProduct(String productId) {
		int result = 0;
		try {
			result = productService.deleteProduct(productId);
		} catch (ProductException e) {
			e.printStackTrace();
			System.err.println("관리자에게 문의하세요 - " + e.getMessage());
		}
		return result;
	}

}
