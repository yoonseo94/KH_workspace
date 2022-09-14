package product.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import admin.model.exception.AdminException;
import member.model.dto.Member;
import member.model.exception.MemberException;
import product.model.dto.Product;
import product.model.dto.ProductBrand;
import product.model.dto.ProductDescriptionImage;
import product.model.dto.ProductExt;
import product.model.dto.ProductIO;
import product.model.dto.ProductIOExt;
import product.model.dto.ProductImage;
import product.model.dto.ProductMainCode;
import product.model.dto.ProductSubCode;
import product.model.dto.Status;
import product.model.exception.ProductException;
import store.model.dao.TodayDeal;

public class ProductDao {
	
	private Properties prop = new Properties();
	
	public ProductDao() {
		String fileName = ProductDao.class.getResource("/sql/product-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ProductExt handleProductResultSet(ResultSet rset) throws SQLException {
		ProductExt product = new ProductExt();
		product.setProductId(rset.getString("product_id"));
		product.setProductName(rset.getString("product_name"));
		product.setMainCode(rset.getString("main_code"));
		product.setSubCode(rset.getString("sub_code"));
		product.setBrandId(rset.getString("brand_id"));				
		product.setProductHeight(rset.getDouble("product_height"));
		product.setProductWidth(rset.getDouble("product_width"));
		product.setProductDepth(rset.getDouble("product_depth"));
		product.setProductColor(rset.getString("product_color"));				
		product.setProductPrice(rset.getInt("product_price"));				
		product.setRegDate(rset.getDate("reg_date"));	
		product.setBrandName(rset.getString("brand_name"));
		product.setMainCategoryName(rset.getString("main_category_name"));
		product.setSubCategoryName(rset.getString("sub_category_name"));
		product.setDiscountRate(rset.getInt("discount_rate"));
		return product;
	}
	
	private ProductImage handleProductImageResultSet(ResultSet rset) throws SQLException {
		ProductImage img = new ProductImage();
		img.setNo(rset.getInt("attach_no"));
		img.setProductId(rset.getString("product_id"));
		img.setOriginalFilename(rset.getString("original_filename"));
		img.setRenamedFilename(rset.getString("renamed_filename"));
		img.setRegDate(rset.getDate("reg_date"));
		return img;
	}
	
	private ProductDescriptionImage handleProductDescriptionImageResultSet(ResultSet rset) throws SQLException {
		ProductDescriptionImage des = new ProductDescriptionImage();
		des.setNo(rset.getInt("attach_no"));
		des.setProductId(rset.getString("product_id"));
		des.setOriginalFilename(rset.getString("original_filename"));
		des.setRenamedFilename(rset.getString("renamed_filename"));
		des.setRegDate(rset.getDate("reg_date"));
		return des;
	}
	private TodayDeal handleTodayDealResultSet(ResultSet rset) throws SQLException {
		TodayDeal todayDeal = new TodayDeal();
		todayDeal.setTodayDealNo(rset.getInt("today_deal_no"));
		todayDeal.setProductId(rset.getString("product_id"));
		todayDeal.setDiscountRate(rset.getInt("discount_rate")); 
		return todayDeal;
	}
	
	private ProductIOExt handleProductIOExtResultSet(ResultSet rset) throws SQLException {
		ProductIOExt productIOExt = new ProductIOExt();
		productIOExt.setProductName(rset.getString("product_name"));
		productIOExt.setNo(rset.getInt("no"));
		productIOExt.setProductId(rset.getString("product_id"));
		productIOExt.setCount(rset.getInt("count"));
		productIOExt.setStatus(Status.valueOf(rset.getString("status")));
		productIOExt.setIoDateTime(rset.getTimestamp("io_datetime"));
		productIOExt.setStock(rset.getInt("stock"));
		return productIOExt;
	}

	public List<ProductExt> findAllProducts(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("findAllProducts");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductExt product = handleProductResultSet(rset);
				productList.add(product);
			}
		} catch (Exception e) {
			throw new ProductException("관리자 상품 리스트 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}

	public int getTotalProducts(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("getTotalProducts");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new ProductException("총 등록 상품수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int enrollProduct(Connection conn, ProductExt product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollProduct");
		try {	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProductId());
			pstmt.setString(2, product.getProductName());
			pstmt.setString(3, product.getMainCode());
			pstmt.setString(4, product.getSubCode());
			pstmt.setString(5, product.getBrandId()); 
			pstmt.setDouble(6, product.getProductHeight());
			pstmt.setDouble(7, product.getProductWidth());
			pstmt.setDouble(8, product.getProductDepth());
			pstmt.setString(9, product.getProductColor());
			pstmt.setInt(10, product.getProductPrice());
			pstmt.setString(11, product.getPContent());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<ProductImage> findProductImagesByProductId(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductImage> productImages = new ArrayList<>();
		String sql = prop.getProperty("findProductImagesByProductId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductImage img = handleProductImageResultSet(rset);
				productImages.add(img);
			}
		} catch (Exception e) {
			throw new ProductException("상품 아이디에 의한 첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productImages;
	}

	public int deleteProduct(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateProduct(Connection conn, ProductExt product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateProduct");
		try {	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getMainCode());
			pstmt.setString(3, product.getSubCode());
			pstmt.setString(4, product.getBrandId()); 
			pstmt.setDouble(5, product.getProductHeight());
			pstmt.setDouble(6, product.getProductWidth());
			pstmt.setDouble(7, product.getProductDepth());
			pstmt.setString(8, product.getProductColor());
			pstmt.setInt(9, product.getProductPrice());
			pstmt.setString(10, product.getPContent());
			pstmt.setString(11, product.getProductId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}


	public ProductExt findProductByProductId(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProductExt product = null;
		String sql = prop.getProperty("findProductByProductId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				product = handleProductResultSet(rset);
			}
		} catch (Exception e) {
			throw new ProductException("상품 아이디로 상품 찾기 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return product;
	}
	
	public int enrollProductImages(Connection conn, ProductImage img) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollProductImages");
		System.out.println("img@dao" + img.getProductId());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, img.getProductId());
			pstmt.setString(2, img.getOriginalFilename());
			pstmt.setString(3, img.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ProductImage findProductImagesByImgNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProductImage img = null;
		String sql = prop.getProperty("findProductImagesByImgNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				img = handleProductImageResultSet(rset);
			}
		} catch (Exception e) {
			throw new ProductException("번호에 의한 첨부파일조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return img;
	}

	public int deleteProductProductImages(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteProductProductImages");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품삭제 - 첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteProductImage(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteProductImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 수정 - 이미지 첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateProductImages(Connection conn, ProductImage img) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductImages");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, img.getOriginalFilename());
			pstmt.setString(2, img.getRenamedFilename());
			pstmt.setInt(3, img.getNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("이미지 첨부파일 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<ProductMainCode> findAllMainCodes(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductMainCode> mainCodeList = new ArrayList<>();
		String sql = prop.getProperty("findAllMainCodes");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductMainCode productMainCode = new ProductMainCode();
				productMainCode.setProductMainCode(rset.getString("main_code"));
				productMainCode.setProductMainName(rset.getString("main_category_name"));  
				mainCodeList.add(productMainCode);
			}
		} catch (Exception e) {
			throw new ProductException("상품 대분류 리스트 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return mainCodeList;
	}

	public List<ProductSubCode> findAllSubCodes(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductSubCode> subCodeList = new ArrayList<>();
		String sql = prop.getProperty("findAllSubCodes");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductSubCode productSubCode = new ProductSubCode();
				productSubCode.setProductSubCode(rset.getString("sub_code"));
				productSubCode.setProductMainCode(rset.getString("main_code"));
				productSubCode.setProductSubName(rset.getString("sub_category_name"));  
				subCodeList.add(productSubCode);
			}
		} catch (Exception e) {
			throw new ProductException("상품 소분류 리스트 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return subCodeList;
	}

	public List<ProductBrand> findAllBrandIds(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductBrand> brandList = new ArrayList<>();
		String sql = prop.getProperty("findAllBrandIds");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductBrand productBrand = new ProductBrand();
				productBrand.setBrandId(rset.getString("brand_id"));
				productBrand.setBrandName(rset.getString("brand_name"));  
				brandList.add(productBrand);
			}
		} catch (Exception e) {
			throw new ProductException("상품 브랜드 리스트 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return brandList;
	}

	public List<ProductIOExt> findAllProductsIO(Connection conn, Map<String, Object> pageBarPoint) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductIOExt> productIOList = new ArrayList<>();
		String sql = prop.getProperty("findAllProductsIO");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) pageBarPoint.get("start"));
			pstmt.setInt(2, (int) pageBarPoint.get("end"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductIOExt ProductIOExt = handleProductIOExtResultSet(rset);
				productIOList.add(ProductIOExt);
			}
		} catch (Exception e) {
			throw new ProductException("관리자 상품 입출고 리스트 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productIOList;
	}

	public int getTotalProductsIO(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalProductsIO = 0;
		String sql = prop.getProperty("getTotalProductsIO");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalProductsIO = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new ProductException("총 상품 입출고수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalProductsIO;
	}

	public List<ProductExt> findAllProductForSearch(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("findAllProductForSearch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductExt product = handleProductResultSet(rset);
				productList.add(product);
			}
		} catch (Exception e) {
			throw new ProductException("관리자 상품명검색을 위한 전체 상품 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}

	public int enrollProductIO(Connection conn, ProductIO productIO) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollProductIO");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productIO.getProductId());
			pstmt.setInt(2, productIO.getCount());
			pstmt.setString(3, productIO.getStatus().toString());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 입출고 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int enrollProductStock(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollProductStock");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 재고 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<ProductIOExt> findAllProductsIOBySomething(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductIOExt> productIOExtList = new ArrayList<>();
		String sql = prop.getProperty("findAllProductsIOBySomething");
		sql = sql.replace("#", param.get("searchType"));
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.get("maincode"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductIOExt productIOExt = handleProductIOExtResultSet(rset);
				productIOExtList.add(productIOExt);
			}
		} catch (Exception e) {
			throw new ProductException("관리자 상품 입출고 분류별 리스트 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productIOExtList;
	}

	public List<ProductIOExt> findBySomething(Connection conn, Map<String, String> searchParam) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductIOExt> productIOExtList = new ArrayList<>();
		String sql = prop.getProperty("findBySomething");
		sql = sql.replace("#", searchParam.get("searchType"));
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchParam.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductIOExt productIOExt = handleProductIOExtResultSet(rset);
				productIOExtList.add(productIOExt);
			}
			
		} catch (Exception e) {
			throw new ProductException("관리자 상품 입출고 기록 검색 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productIOExtList;
	}

	public int getSearchProductsIOContent(Connection conn, Map<String, String> searchParam) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int findContents = 0;
		String sql = prop.getProperty("getSearchProductsIOContent");
		sql = sql.replace("#", searchParam.get("searchType"));
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchParam.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while(rset.next())
				findContents = rset.getInt(1);  
		} catch (Exception e) {
			throw new ProductException("검색 상품 입출고수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return findContents;
	}

	public List<ProductExt> findAllProductsBySomething(Connection conn, Map<String, String> searchParam, Map<String, Object> pageBarPoint) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productExtList = new ArrayList<>();
		String sql = prop.getProperty("findAllProductsBySomething");
		sql = sql.replace("#", searchParam.get("searchType"));
		System.out.println("Sql@DaO = " + sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchParam.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductExt productExt = handleProductResultSet(rset);
				productExtList.add(productExt);
			}
			
		} catch (Exception e) {
			throw new ProductException("관리자 상품 검색 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productExtList;
	}

	public int getSearchProductsContent(Connection conn, Map<String, String> searchParam) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int findContents = 0;
		String sql = prop.getProperty("getSearchProductsContent");
		sql = sql.replace("#", searchParam.get("searchType"));
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchParam.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while(rset.next())
				findContents = rset.getInt(1);  
		} catch (Exception e) {
			throw new ProductException("검색 상품수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return findContents;
	}

	public List<ProductExt> findFourProductsByDeal(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("findFourProductsByDeal");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductExt product = handleProductResultSet(rset);
				productList.add(product);
				System.out.println("product = " + product);
			}
		} catch (Exception e) {
			throw new ProductException("오늘의 딜 상품 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}

	public int enrollProductDescriptionImages(Connection conn, ProductDescriptionImage des) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("enrollProductDescriptionImages");
		System.out.println("des@dao" + des.getProductId());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, des.getProductId());
			pstmt.setString(2, des.getOriginalFilename());
			pstmt.setString(3, des.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 설명 이미지 파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteProductProductDescriptionImages(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteProductProductDescriptionImages");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 삭제 - 첨부 설명 파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteProductDescriptionImages(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteProductDescriptionImages");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new ProductException("상품 수정 -  설명 이미지 첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<TodayDeal> findAllTodayDeal(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<TodayDeal> todayDeals = new ArrayList<>();
		String sql = prop.getProperty("findAllTodayDeal");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				TodayDeal todayDeal = handleTodayDealResultSet(rset);
				todayDeals.add(todayDeal);
			}
		} catch (Exception e) {
			throw new ProductException("오늘의 딜 상품 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return todayDeals;
	}

	public ProductDescriptionImage findProductDescriptionImagesByImgNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProductDescriptionImage img = null;
		String sql = prop.getProperty("findProductDescriptionImagesByImgNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				img = handleProductDescriptionImageResultSet(rset);
			}
		} catch (Exception e) {
			throw new ProductException("번호에 의한 첨부파일조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return img;
	}

	public List<ProductDescriptionImage> findProductDescriptionImageByProductId(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductDescriptionImage> productDescriptionImages = new ArrayList<>();
		String sql = prop.getProperty("findProductDescriptionImageByProductId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductDescriptionImage img = handleProductDescriptionImageResultSet(rset);
				productDescriptionImages.add(img);
			}
		} catch (Exception e) {
			throw new ProductException("상품 아이디에 의한 첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productDescriptionImages;
	}

	public List<ProductExt> findAllProductsByDeal(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("findAllProductsByDeal");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductExt product = handleProductResultSet(rset);
				productList.add(product);
			}
		} catch (Exception e) {
			throw new ProductException("오늘의 딜 상품 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}

	public List<ProductExt> finallProductsByDefault(Connection conn, String mainCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productList = new ArrayList<>();
		String sql = prop.getProperty("finallProductsByDefault");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mainCode);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductExt product = handleProductResultSet(rset);
				productList.add(product);
			}
		} catch (Exception e) {
			throw new ProductException("카테고리 홈 기본 상품(가구) 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productList;
	}

	public List<ProductExt> findProductsByCategory(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductExt> findAllProductsByCategory(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductExt> productExtList = new ArrayList<>();
		String sql = prop.getProperty("findAllProductsByCategory");
		sql = sql.replace("#", param.get("searchType"));
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.get("maincode"));
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductExt products = handleProductResultSet(rset);
				productExtList.add(products);
			}
		} catch (Exception e) {
			throw new ProductException("카테고리별 상품 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return productExtList;
	}




}
