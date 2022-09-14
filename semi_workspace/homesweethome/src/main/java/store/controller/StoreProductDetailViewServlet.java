package store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.dto.ProductExt;
import product.model.service.ProductService;
import store.model.dao.TodayDeal;

/**
 * Servlet implementation class StoreProductDetailViewServlet
 */
@WebServlet("/store/productView")
public class StoreProductDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.사용자입력값처리
			
			  String productId = request.getParameter("productId");

			  // 2.업무로직 
			 // 상품 조회 
			 ProductExt product = productService.findProductByProductId(productId);
			 List<TodayDeal> todayDeals = productService.findAllTodayDeal();
			 // 3.view단 위임
			 request.setAttribute("products", product);
			 request.setAttribute("todayDeals", todayDeals);
			request.getRequestDispatcher("/WEB-INF/views/store/storeProductDetailView.jsp")
					.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
