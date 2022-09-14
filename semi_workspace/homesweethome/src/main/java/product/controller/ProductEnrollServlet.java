package product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.HomeSweetHomeFileRenamePolicy;
import product.model.dto.ProductBrand;
import product.model.dto.ProductDescriptionImage;
import product.model.dto.ProductExt;
import product.model.dto.ProductImage;
import product.model.dto.ProductMainCode;
import product.model.dto.ProductSubCode;
import product.model.service.ProductService;

/**
 * Servlet implementation class ProductEnrollServlet
 */
@WebServlet("/product/enrollProduct")
public class ProductEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductMainCode> productMainCodes = productService.findAllMainCodes();
		List<ProductSubCode> productSubCodes = productService.findAllSubCodes();
		List<ProductBrand> productBrandIds = productService.findAllBrandIds();
		for(ProductSubCode subcode : productSubCodes) {
			System.out.println("productSubCode = " + subcode.getProductMainCode());	
		}
		request.setAttribute("productMainCodes", productMainCodes);
		request.setAttribute("productSubCodes", productSubCodes);
		request.setAttribute("productBrandIds", productBrandIds);
		request.getRequestDispatcher("/WEB-INF/views/product/productEnroll.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	
			String saveDirectory = getServletContext().getRealPath("/upload/product");
			// c. 최대파일크기 10MB 
			int maxPostSize = 1024 * 1024 * 10;
			// d. 인코딩
			String encoding = "utf-8";
			// e. 파일명 재지정 정책 객체
			FileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
			MultipartRequest multiReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 3. 사용자입력값 처리
			// dto 객체 생성
			String productName = multiReq.getParameter("productName"); 
			String mainCode = multiReq.getParameter("mainCode");
			String subCode = multiReq.getParameter("subCode");
			String brandId = multiReq.getParameter("brandId");
			
			String productId = brandId + productName;
			System.out.println("productId = "  + productId);
					
			double productHeight = Double.parseDouble(multiReq.getParameter("productHeight"));
			double productWidth = Double.parseDouble(multiReq.getParameter("productWidth"));
			double productDepth = Double.parseDouble(multiReq.getParameter("productDepth"));
			String productColor = multiReq.getParameter("productColor");
			int productPrice = Integer.parseInt(multiReq.getParameter("productPrice"));
			String pContent = multiReq.getParameter("content");
			
			ProductExt product = new ProductExt();
			product.setProductId(productId);
			product.setProductName(productName);
			product.setMainCode(mainCode);
			product.setSubCode(subCode);
			product.setBrandId(brandId);
			product.setProductHeight(productHeight);
			product.setProductWidth(productWidth);
			product.setProductDepth(productDepth);
			product.setProductColor(productColor);
			product.setProductPrice(productPrice);
			product.setPContent(pContent);
			

			
			File upFile1 = multiReq.getFile("upFile1");
			File upFile2 = multiReq.getFile("upFile2");
			File upFile3 = multiReq.getFile("upFile3");
			
			File desFile1 = multiReq.getFile("desFile1");
			File desFile2 = multiReq.getFile("desFile2");
			File desFile3 = multiReq.getFile("desFile3");
			File desFile4 = multiReq.getFile("desFile4");
			
	
			
			// 첨부한 파일이 하나라도 있는 경우
			if(upFile1 != null || upFile2 != null || upFile3 != null) {
				List<ProductImage> productImages = new ArrayList<>();
				if(upFile1 != null) 
					productImages.add(getProductImage(multiReq, "upFile1"));
				if(upFile2 != null) 
					productImages.add(getProductImage(multiReq, "upFile2"));
				if(upFile3 != null) 
					productImages.add(getProductImage(multiReq, "upFile3"));
				product.setProductImages(productImages);
			}
			
			if(desFile1 != null || desFile2 != null || desFile3 != null || desFile4 != null) {
				List<ProductDescriptionImage> productDescriptionImages = new ArrayList<>();
				if(desFile1 != null) 
					productDescriptionImages.add(getProductDescriptionImage(multiReq, "desFile1"));
				if(desFile2 != null) 
					productDescriptionImages.add(getProductDescriptionImage(multiReq, "desFile2"));
				if(desFile3 != null) 
					productDescriptionImages.add(getProductDescriptionImage(multiReq, "desFile3"));
				if(desFile4 != null) 
					productDescriptionImages.add(getProductDescriptionImage(multiReq, "desFile4"));
				product.setProductDescriptionImages(productDescriptionImages);
			}
			
			System.out.println("product = " + product);
			
			// 4. 업무로직
			int result = productService.enrollProduct(product);
			
			// 5. redirect
			response.sendRedirect(request.getContextPath() + "/admin/manageProduct");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private ProductImage getProductImage(MultipartRequest multiReq, String name) {
		ProductImage productImage = new ProductImage();
		String originalFilename = multiReq.getOriginalFileName(name); // 업로드한 파일명
		String renamedFilename = multiReq.getFilesystemName(name); // 저장된 파일명
		productImage.setOriginalFilename(originalFilename);
		productImage.setRenamedFilename(renamedFilename);
		return productImage;
	}
	
	private ProductDescriptionImage getProductDescriptionImage(MultipartRequest multiReq, String name) {
		ProductDescriptionImage productDescriptionImage = new ProductDescriptionImage();
		String originalFilename = multiReq.getOriginalFileName(name); // 업로드한 파일명
		String renamedFilename = multiReq.getFilesystemName(name); // 저장된 파일명
		productDescriptionImage.setOriginalFilename(originalFilename);
		productDescriptionImage.setRenamedFilename(renamedFilename);
		return productDescriptionImage;
	}

}
