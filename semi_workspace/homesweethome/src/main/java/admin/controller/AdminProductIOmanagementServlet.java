package admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.HomeSweetHomeUtils;
import product.model.dto.ProductIO;
import product.model.dto.ProductIOExt;
import product.model.service.ProductService;

/**
 * Servlet implementation class AdminProductIOmanagementServlet
 */
@WebServlet("/admin/productIOManagement")
public class AdminProductIOmanagementServlet extends HttpServlet {
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
			
			// 2. 업무로직
			// 2.1. content 영역
			List<ProductIOExt> productsIOExtList = productService.findAllProductsIO(pageBarPoint);
			
			// 2.2. page bar 영역
			int totalContents = productService.getTotalProductsIO();
			String url = request.getRequestURI(); 
			String pagebar = HomeSweetHomeUtils.getPagebar(cPage, numPerPage, totalContents, url);
	
			// 3. view단 처리
			request.setAttribute("productsIOExtList", productsIOExtList);
			request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/product/productIOPage.jsp")
		.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}

}
