package product.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import member.model.dto.Member;
import product.model.dao.ProductDao;
import product.model.dto.Product;
import product.model.dto.ProductBrand;
import product.model.dto.ProductDescriptionImage;
import product.model.dto.ProductExt;
import product.model.dto.ProductIO;
import product.model.dto.ProductIOExt;
import product.model.dto.ProductImage;
import product.model.dto.ProductMainCode;
import product.model.dto.ProductStock;
import product.model.dto.ProductSubCode;
import store.model.dao.TodayDeal;

public class ProductService {
	public static final int NUM_PER_PAGE = 10;
	private ProductDao productDao = new ProductDao();
	
	public List<ProductExt> findAllProducts(Map<String, Object> param) {
		Connection conn = getConnection();
		List<ProductExt> productList = productDao.findAllProducts(conn, param);
		close(conn);
		return productList;
	}

	public int getTotalProducts() {
		Connection conn = getConnection();
		int totalContents = productDao.getTotalProducts(conn);
		close(conn);
		return totalContents;
	}

	public int enrollProduct(ProductExt product) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = productDao.enrollProduct(conn, product);
			
			String productId = product.getProductId();
			
			result = productDao.enrollProductStock(conn, productId);
			
			List<ProductImage> productImages = product.getProductImages();
			List<ProductDescriptionImage> productDescriptionImages = product.getProductDescriptionImages();
		
			if(productImages != null && !productImages.isEmpty()) {
				for(ProductImage img : productImages) {
					img.setProductId(productId);
					result = productDao.enrollProductImages(conn, img);
				}
			}
			if(productDescriptionImages != null && !productDescriptionImages.isEmpty()) {
				for(ProductDescriptionImage des : productDescriptionImages) {
					des.setProductId(productId);
					result = productDao.enrollProductDescriptionImages(conn, des);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<ProductImage> findProductImagesByProductId(String productId) {
		Connection conn = getConnection();
		List<ProductImage> productImages = productDao.findProductImagesByProductId(conn, productId);
		close(conn);
		return productImages;
	}
	


	public int deleteProduct(String productId) {
		Connection conn = getConnection();
		int result = 0;
		int resultImg = 0;
		int resultDes = 0;
		try {
			result = productDao.deleteProduct(conn, productId);
			resultImg = productDao.deleteProductProductImages(conn, productId);
			resultDes = productDao.deleteProductProductDescriptionImages(conn, productId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateProduct(ProductExt product) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = productDao.updateProduct(conn, product);
			String id = product.getProductId();
			List<ProductImage> productImages = product.getProductImages();
			List<ProductDescriptionImage> productDescriptionImages = product.getProductDescriptionImages();
			if(productImages != null && !productImages.isEmpty()) {
				for(ProductImage img : productImages) {
					img.setProductId(id);
					result = productDao.enrollProductImages(conn, img);
				}
			}
			if(productDescriptionImages != null && !productDescriptionImages.isEmpty()) {
				for(ProductDescriptionImage img : productDescriptionImages) {
					img.setProductId(id);
					result = productDao.enrollProductDescriptionImages(conn, img);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public ProductImage findProductImagesByImgNo(int no) {
		Connection conn = getConnection();
		ProductImage img = productDao.findProductImagesByImgNo(conn, no);
		close(conn);
		return img;
	}

	public int deleteProductImage(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = productDao.deleteProductImage(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	public int deleteProductDescriptionImage(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = productDao.deleteProductDescriptionImages(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public ProductExt findProductByProductId(String productId) {
		Connection conn = getConnection();
		ProductExt product = productDao.findProductByProductId(conn, productId);
		List<ProductImage> productImages = productDao.findProductImagesByProductId(conn, productId);
		List<ProductDescriptionImage> productDescriptionImages = productDao.findProductDescriptionImageByProductId(conn, productId);
		product.setProductImages(productImages);
		product.setProductDescriptionImages(productDescriptionImages);
		close(conn);
		return product;
	}

	public List<ProductMainCode> findAllMainCodes() {
		Connection conn = getConnection();
		List<ProductMainCode> mainCodeList = productDao.findAllMainCodes(conn);
		close(conn);
		return mainCodeList;
	}

	public List<ProductSubCode> findAllSubCodes() {
		Connection conn = getConnection();
		List<ProductSubCode> subCodeList = productDao.findAllSubCodes(conn);
		close(conn);
		return subCodeList;
	}

	public List<ProductBrand> findAllBrandIds() {
		Connection conn = getConnection();
		List<ProductBrand> brandList = productDao.findAllBrandIds(conn);
		close(conn);
		return brandList;
	}
	

	public List<ProductIOExt> findAllProductsIO(Map<String, Object> pageBarPoint) {
		Connection conn = getConnection();
		List<ProductIOExt> productIOList = productDao.findAllProductsIO(conn, pageBarPoint);
		close(conn);
		return productIOList;
	}

	public int getTotalProductsIO() {
		Connection conn = getConnection();
		int totalContents = productDao.getTotalProductsIO(conn);
		close(conn);
		return totalContents;
	}

	public List<ProductExt> findAllProductForSearch() {
		Connection conn = getConnection();
		List<ProductExt> productList = productDao.findAllProductForSearch(conn);
		close(conn);
		return productList;
	}

	public int enrollProductIO(ProductIO productIO) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = productDao.enrollProductIO(conn, productIO);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<ProductIOExt> findAllProductsIOBySomething(Map<String, String> param) {
		Connection conn = getConnection();
		List<ProductIOExt> productIOExtList = productDao.findAllProductsIOBySomething(conn, param);
		close(conn);
		return productIOExtList;
	}

	public List<ProductIOExt> findBySomething(Map<String, String> searchParam) {
		Connection conn = getConnection();
		List<ProductIOExt> productIOExtList = productDao.findBySomething(conn, searchParam);
		close(conn);
		return productIOExtList;
	}

	public int getSearchProductsIOContent(Map<String, String> searchParam) {
		Connection conn = getConnection();
		int getSearchProductsIOContent = productDao.getSearchProductsIOContent(conn, searchParam);
		close(conn);
		return getSearchProductsIOContent;
	}

	public List<ProductExt> findAllProductsBySomething(Map<String, String> searchParam, Map<String, Object> pageBarPoint) {
		Connection conn = getConnection();
		List<ProductExt> productExtList = productDao.findAllProductsBySomething(conn, searchParam, pageBarPoint);
		close(conn);
		return productExtList;
	}

	public int getSearchProductsContent(Map<String, String> searchParam) {
		Connection conn = getConnection();
		int getSearchProductsContent = productDao.getSearchProductsContent(conn, searchParam);
		close(conn);
		return getSearchProductsContent;
	}

	public List<ProductExt> findFourProductsByDeal() {
		Connection conn = getConnection();
		List<ProductExt> productList = productDao.findFourProductsByDeal(conn);
		close(conn);
		return productList;
	}

	public List<TodayDeal> findAllTodayDeal() {
		Connection conn = getConnection();
		List<TodayDeal> todayDeal = productDao.findAllTodayDeal(conn);
		close(conn);
		return todayDeal;
	}

	public ProductDescriptionImage findProductDescriptionImagesByImgNo(int no) {
		Connection conn = getConnection();
		ProductDescriptionImage img = productDao.findProductDescriptionImagesByImgNo(conn, no);
		close(conn);
		return img;
	}
	
	public List<ProductDescriptionImage> findProductDescriptionImageByProductId(String productId) {
		Connection conn = getConnection();
		List<ProductDescriptionImage> productDescriptionImages = productDao.findProductDescriptionImageByProductId(conn, productId);
		close(conn);
		return productDescriptionImages;
	}

	public List<ProductExt> findAllProductsByDeal() {
		Connection conn = getConnection();
		List<ProductExt> productList = productDao.findAllProductsByDeal(conn);
		close(conn);
		return productList;
	}

	public List<ProductExt> findProductsByCategory() {
		Connection conn = getConnection();
		List<ProductExt> productList = productDao.findProductsByCategory(conn);
		close(conn);
		return productList;
	}

	public List<ProductExt> finallProductsByDefault(String mainCode) {
		Connection conn = getConnection();
		List<ProductExt> productList = productDao.finallProductsByDefault(conn, mainCode);
		close(conn);
		return productList;
	}

	public List<ProductExt> findAllProductsByCategory(Map<String, String> param) {
		Connection conn = getConnection();
		List<ProductExt> productExtList = productDao.findAllProductsByCategory(conn, param);
		close(conn);
		return productExtList;
	}
}



