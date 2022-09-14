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

import com.google.gson.Gson;

import product.model.dto.ProductIO;
import product.model.dto.ProductIOExt;
import product.model.service.ProductService;

/**
 * Servlet implementation class ProductIOSearchTabServlet
 */
@WebServlet("/product/findProductIOBySomething")
public class ProductIOSearchTabServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maincode = request.getParameter("maincode");
		String searchType = request.getParameter("searchType");
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("maincode", maincode);
		List<ProductIOExt> productIOExtList = productService.findAllProductsIOBySomething(param);
		
		// 2. 응답처리 -  json 변환해서 출력
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(productIOExtList);
		System.out.println("jsonStr = " + jsonStr);
		response.getWriter().append(jsonStr);
	}

}
