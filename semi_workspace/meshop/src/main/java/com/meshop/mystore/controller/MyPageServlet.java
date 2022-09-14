package com.meshop.mystore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.meshop.product.entity.ProductExt;
import com.meshop.product.service.ProductService;
import com.meshop.product.service.ProductServiceImpl;

/**
 * Servlet implementation class MyStoreMyproduct
 */
@WebServlet("/mystore/myProduct")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/mystore/myProduct.jsp")
			.forward(request, response);
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//memberId 받아야 돼죠??
		// 1. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		System.out.print("memberId : " + memberId);
		
		// 2. 회원 아이디에 따른 product 정보 가져오기..
		// view -> Servlet (Controller)-> productService (Service) 
		// -> productDAO (Repository or Data Access Object) -> DB -> productDAO -> productService ->
		List<ProductExt> list = productService.findByMemberId(memberId);
		
		// 3. 뷰에 전달.. (응답) (웹은 요청인 request와 응답인 response로 나누어져있다.
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.append(new Gson().toJson(list));
		
		
	}
	
}




