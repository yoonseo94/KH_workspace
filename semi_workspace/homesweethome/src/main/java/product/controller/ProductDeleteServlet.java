package product.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.model.dto.ProductImage;
import product.model.service.ProductService;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/product/productDelete")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			String productId = request.getParameter("productId");
			
			//2. 서비스로직호출
			//2.1. 첨부파일 삭제
			String deleteDirectory = getServletContext().getRealPath("/upload/product");
			List<ProductImage> productImges = productService.findProductImagesByProductId(productId);
			if(productImges != null && !productImges.isEmpty()) {
				for(ProductImage img : productImges) {
					File deleteFile = new File(deleteDirectory, img.getRenamedFilename());
					deleteFile.delete();				
				}
			}
			
			//2.2. 삭제
			int result = productService.deleteProduct(productId);
			
			//3. 리다이렉트 처리
			HttpSession session = request.getSession();
			session.setAttribute("msg", "등록된 상품의 삭제가 성공적으로 처리되었습니다.");
			response.sendRedirect(request.getContextPath() + "/admin/manageProduct");			
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
