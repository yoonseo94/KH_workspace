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

import common.HomeSweetHomeUtils;
import product.model.dto.ProductExt;
import product.model.dto.ProductIOExt;
import product.model.service.ProductService;

/**
 * Servlet implementation class ProductFinderBySearchServlet
 */
@WebServlet("/product/productFinder")
public class ProductFinderBySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// page bar 관련 변수
			int numPerPage = productService.NUM_PER_PAGE; // 한 페이지당 출력되는 content의 수
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
			
			
			// 1. 사용자입력값처리
			String searchType = request.getParameter("searchType");  
			String searchKeyword = request.getParameter("searchKeyword");
			Map<String, String> searchParam = new HashMap<>();
			searchParam.put("searchType", searchType);
			searchParam.put("searchKeyword", searchKeyword);
			System.out.println("searchType@servlet = " + searchType);
			System.out.println("searchKeyword@servlet = " + searchKeyword);
			// 2. 업무로직
			List<ProductExt> productsExtList = productService.findAllProductsBySomething(searchParam, pageBarPoint);
			for(ProductExt product : productsExtList) {
				System.out.print("@servletAfter = ");
				System.out.println(product);
			}
			int findContents = productService.getSearchProductsContent(searchParam);
			
			String url = request.getRequestURI(); 
			String pagebar = HomeSweetHomeUtils.getPagebar(cPage, numPerPage, findContents, url);
			
			// 3. view단처리
			request.setAttribute("pagebar", pagebar);
			request.setAttribute("productsExtList", productsExtList);
			request.getRequestDispatcher("/WEB-INF/views/product/productsManage.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
