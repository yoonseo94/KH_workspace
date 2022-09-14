package store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.dto.ProductExt;
import product.model.dto.ProductImage;
import product.model.service.ProductService;
import store.model.dao.TodayDeal;

/**
 * Servlet implementation class StoreCategoryServlet
 */
@WebServlet("/store/storeCategory")
public class StoreCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			String code = request.getParameter("mainCategory");
			System.out.println("code = " + code);
			String mainCode = "furniture";
			String mainNameVal = "";
			if(code != null) {
				mainCode = code;
				
				switch(code) {
				case "furniture" : mainNameVal = "가구"; break;
				case "electorinics" : mainNameVal = "가전"; break;
				case "lighting" : mainNameVal = "조명"; break;
				case "organizing" : mainNameVal = "정리/수납"; break;
				case "living" : mainNameVal = "생활용품"; break;
				}
			} 
//			else {
//				mainNameVal = "가구";
//			}
			// 1. 업무 로직
			List<ProductExt> productsExtDealList = productService.findFourProductsByDeal();
			List<ProductExt> productDealExtLists = new ArrayList<>();
			for(ProductExt product : productsExtDealList) {
				List<ProductImage> productImages = productService.findProductImagesByProductId(product.getProductId());
				product.setProductImages(productImages);
				productDealExtLists.add(product);
			}
			
			List<ProductExt> productListDefault = productService.finallProductsByDefault(mainCode);
			List<ProductExt> productListByDefaults = new ArrayList<>();
			for(ProductExt products : productListDefault) {
				List<ProductImage> productImages = productService.findProductImagesByProductId(products.getProductId());
				products.setProductImages(productImages);
				productListByDefaults.add(products);
			}
			
			
			
			List<TodayDeal> todayDeals = productService.findAllTodayDeal();
				
			// 2. view단 처리
			request.setAttribute("mainNameVal", mainNameVal);
			request.setAttribute("productDealExtLists", productDealExtLists);
			request.setAttribute("productListByDefaults", productListByDefaults);
			request.setAttribute("todayDeals", todayDeals);
			request.getRequestDispatcher("/WEB-INF/views/store/storeCategory.jsp")
			.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}
}
