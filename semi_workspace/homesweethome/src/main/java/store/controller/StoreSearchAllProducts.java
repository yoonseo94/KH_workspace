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
 * Servlet implementation class StoreSearchAllProducts
 */
@WebServlet("/store/searchAllProductsByKeyword")
public class StoreSearchAllProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductService();   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = request.getParameter("search");
			
			// 1. 업무 로직
			List<ProductExt> productsExtDealList = productService.findFourProductsByDeal();
			List<ProductExt> productDealExtLists = new ArrayList<>();
			for(ProductExt product : productsExtDealList) {
				List<ProductImage> productImages = productService.findProductImagesByProductId(product.getProductId());
				product.setProductImages(productImages);
				productDealExtLists.add(product);
			}
			
			
			
			
			 // List<ProductExt> productListKeyword = productService.findProductsByKeyword();
			  List<ProductExt> productListByKeyword = new ArrayList<>();
		/*	  for(ProductExt products : productListKeyword) { 
				  List<ProductImage> productImages =  productService.findProductImagesByProductId(products.getProductId());
				  products.setProductImages(productImages);
				  productListByKeyword.add(products); 
			}*/
			
			
			List<TodayDeal> todayDeals = productService.findAllTodayDeal();
				
			// 2. view단 처리
			//request.setAttribute("productDealExtLists", productDealExtLists);
		//	request.setAttribute("productListByKeyword", productListByKeyword);
			request.setAttribute("todayDeals", todayDeals);
			request.getRequestDispatcher("/WEB-INF/views/store/storeCategory.jsp")
			.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}
}
