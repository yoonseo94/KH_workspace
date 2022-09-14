package com.meshop.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meshop.common.MeshopUtils;
import com.meshop.product.entity.ProductExt;
import com.meshop.product.service.ProductService;
import com.meshop.product.service.ProductServiceImpl;

/**
 * Servlet implementation class ProductListByCategoryServlet
 */
@WebServlet("/product/productListByCategory")
public class ProductListByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			String category = request.getParameter("category");
			int numPerPage = productService.NUM_PER_PAGE;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				// 예외발생시 현재페이지 1
			}
			
			Map<String, Object> param = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			param.put("start", start);
			param.put("end", end);
			param.put("category", category);
			
			// 2. 업무 로직
			// 컨텐츠 영역
			List<ProductExt> list = productService.findAllByCategory(param);
			
			// 페이지바 영역
			int totalProducts = productService.getTotalProductsByCategory(category);
			String url = request.getRequestURI();
			String pagebar = MeshopUtils.getPagebar(cPage, numPerPage, totalProducts, url);
			
			// 3. 뷰단 위임
			request.setAttribute("productList", list);
			request.setAttribute("pagebar", pagebar);
			request.setAttribute("category", category);
			request.getRequestDispatcher("/WEB-INF/views/product/productList.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
