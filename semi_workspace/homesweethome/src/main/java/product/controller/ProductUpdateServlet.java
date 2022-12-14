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
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/product/productUpdate")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		
		List<ProductMainCode> productMainCodes = productService.findAllMainCodes();
		List<ProductSubCode> productSubCodes = productService.findAllSubCodes();
		List<ProductBrand> productBrandIds = productService.findAllBrandIds();
		
		ProductExt product = productService.findProductByProductId(productId);
		
		request.setAttribute("productMainCodes", productMainCodes);
		request.setAttribute("productSubCodes", productSubCodes);
		request.setAttribute("productBrandIds", productBrandIds);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/WEB-INF/views/product/productUpdate.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String saveDirectory = getServletContext().getRealPath("/upload/product");
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			FileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
			MultipartRequest multiReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			String productName = multiReq.getParameter("productName"); 
			String mainCode = multiReq.getParameter("mainCode");
			String subCode = multiReq.getParameter("subCode");
			String brandId = multiReq.getParameter("brandId");
			
	
			String productId = brandId + productName;

			double productHeight = Double.parseDouble(multiReq.getParameter("productHeight"));
			double productWidth = Double.parseDouble(multiReq.getParameter("productWidth"));
			double productDepth = Double.parseDouble(multiReq.getParameter("productDepth"));
			String productColor = multiReq.getParameter("productColor");
			int productPrice = Integer.parseInt(multiReq.getParameter("productPrice"));
			String pContent = multiReq.getParameter("content");
			String[] delFiles = multiReq.getParameterValues("delFile"); 
			String[] delDesFiles = multiReq.getParameterValues("delDesFile"); 
			
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
			
			
			// ????????? ????????? ???????????? ?????? ??????
			if(upFile1 != null || upFile2 != null) {
				List<ProductImage> productImages = new ArrayList<>();
				if(upFile1 != null) {
					productImages.add(getProductImage(multiReq, "upFile1"));					
				}
				if(upFile2 != null) {
					productImages.add(getProductImage(multiReq, "upFile2"));					
				}
				product.setProductImages(productImages);
			}
			
			// ????????? ????????? ???????????? ?????? ??????
			if(desFile1 != null || desFile2 != null || desFile3 != null || desFile4 != null) {
				List<ProductDescriptionImage> productDescriptionImages = new ArrayList<>();
				if(desFile1 != null) {
					productDescriptionImages.add(getProductDescriptionImage(multiReq, "desFile1"));					
				}
				if(desFile2 != null) {
					productDescriptionImages.add(getProductDescriptionImage(multiReq, "desFile2"));					
				}
				if(desFile1 != null) {
					productDescriptionImages.add(getProductDescriptionImage(multiReq, "desFile3"));					
				}
				if(desFile2 != null) {
					productDescriptionImages.add(getProductDescriptionImage(multiReq, "desFile4"));					
				}
				product.setProductDescriptionImages(productDescriptionImages);
			}
			
			int result = productService.updateProduct(product);
			System.out.println("result = " + result);
			
			// ???????????? ?????? ??????
			if(delFiles != null) {
				for(String temp : delFiles) {
					int no = Integer.parseInt(temp); 
					ProductImage attach = productService.findProductImagesByImgNo(no);
					// a. ?????? ??????
					File delFile = new File(saveDirectory, attach.getRenamedFilename());
					if(delFile.exists()) {
						delFile.delete();
					}
					// b. db ????????? ??????
					result = productService.deleteProductImage(no);
					System.out.println("> " + no + "??? ???????????? ??????");
				}
			}
			// ???????????? ?????? ??????
			if(delDesFiles != null) {
				for(String temp : delDesFiles) {
					int no = Integer.parseInt(temp); 
					ProductDescriptionImage attach = productService.findProductDescriptionImagesByImgNo(no);
					// a. ?????? ??????
					File delDesFile = new File(saveDirectory, attach.getRenamedFilename());
					if(delDesFile.exists()) {
						delDesFile.delete();
					}
					// b. db ????????? ??????
					result = productService.deleteProductDescriptionImage(no);
					System.out.println("> " + no + "??? ???????????? ??????");
				}
			}
			
			
			request.setAttribute("msg", "?????? ?????? ?????? ??????");
			response.sendRedirect(request.getContextPath() + "/admin/manageProduct?productId=" + product.getProductId());		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	} 
	
	private ProductImage getProductImage(MultipartRequest multiReq, String name) {
		ProductImage img = new ProductImage();
		String originalFilename = multiReq.getOriginalFileName(name);
		String renamedFilename = multiReq.getFilesystemName(name);
		img.setOriginalFilename(originalFilename);
		img.setRenamedFilename(renamedFilename);
		return img;
		
	}
	
	private ProductDescriptionImage getProductDescriptionImage(MultipartRequest multiReq, String name) {
		ProductDescriptionImage productDescriptionImage = new ProductDescriptionImage();
		String originalFilename = multiReq.getOriginalFileName(name); // ???????????? ?????????
		String renamedFilename = multiReq.getFilesystemName(name); // ????????? ?????????
		productDescriptionImage.setOriginalFilename(originalFilename);
		productDescriptionImage.setRenamedFilename(renamedFilename);
		return productDescriptionImage;
	}

}
