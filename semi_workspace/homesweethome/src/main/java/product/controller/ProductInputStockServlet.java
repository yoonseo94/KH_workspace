package product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.dto.ProductExt;
import product.model.dto.ProductIO;
import product.model.dto.ProductIOExt;
import product.model.dto.Status;
import product.model.service.ProductService;

/**
 * Servlet implementation class ProductInputStockServlet
 */
@WebServlet("/product/productInputStock")
public class ProductInputStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// page bar 관련 변수
			int numPerPage = ProductService.NUM_PER_PAGE; // 한 페이지당 출력되는 content의 수
			int cPage = 1;								 // 현재 페이지
			
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
			}
			
			Map<String, Object> pageBarPoint = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			pageBarPoint.put("start", start);
			pageBarPoint.put("end", end);
			List<ProductExt> productsList = productService.findAllProducts(pageBarPoint);
			List<ProductIOExt> productIOExtList = productService.findAllProductsIO(pageBarPoint);
			request.setAttribute("productsList", productsList);
			request.getRequestDispatcher("/WEB-INF/views/product/productInputStock.jsp")
			.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String productId = request.getParameter("productId"); 
			int count = Integer.parseInt(request.getParameter("count"));
			Status status = Status.valueOf(request.getParameter("status"));
			
			ProductIO productIO = new ProductIO();
			productIO.setProductId(productId);
			productIO.setCount(count);
			productIO.setStatus(status);
		
			int result = productService.enrollProductIO(productIO);

			
			// 5. redirect
			response.sendRedirect(request.getContextPath() + "/admin/productIOManagement");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		
	}

}
