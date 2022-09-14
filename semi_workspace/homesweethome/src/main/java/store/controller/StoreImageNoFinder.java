package store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.model.dto.ProductExt;
import product.model.dto.ProductImage;
import product.model.service.ProductService;

/**
 * Servlet implementation class StoreImageNoFinder
 */
@WebServlet("/store/findImgNo")
public class StoreImageNoFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		int noVal = Integer.parseInt(request.getParameter("noVal"));

		ProductExt product = productService.findProductByProductId(productId);
		List<ProductImage> pImg = product.getProductImages();
		String productFileName = pImg.get(noVal).getRenamedFilename();
	
		String imgSrc = "<img class='production-selling-cover-image-entry-image' src='" + request.getContextPath() +"/upload/product/" + productFileName +"'>";
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter()
				.append(imgSrc);
	}

}
