package store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.HomeSweetHomeUtils;
import product.model.dto.ProductExt;
import product.model.dto.ProductImage;
import product.model.service.ProductService;
import store.model.dao.TodayDeal;

/**
 * Servlet implementation class StoreMainServlet
 */
@WebServlet("/store/storeMain")
public class StoreMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// 1. 업무 로직
			List<ProductExt> productsExtList = productService.findFourProductsByDeal();
			System.out.println("productsExtList@servlet = " + productsExtList);	
			List<ProductExt> productExtLists = new ArrayList<>();
			for(ProductExt product : productsExtList) {
				List<ProductImage> productImages = productService.findProductImagesByProductId(product.getProductId());
				product.setProductImages(productImages);
				productExtLists.add(product);
			}
			
			List<TodayDeal> todayDeals = productService.findAllTodayDeal();
			System.out.println("productExtLists@servlet = " + productExtLists);	
			// 2. view단 처리
			request.setAttribute("productExtLists", productExtLists);
			request.setAttribute("todayDeals", todayDeals);
			request.getRequestDispatcher("/WEB-INF/views/store/storeMain.jsp")
			.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
		
		
		

	}

}
