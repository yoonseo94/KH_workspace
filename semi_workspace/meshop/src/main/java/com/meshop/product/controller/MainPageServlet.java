package com.meshop.product.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meshop.product.entity.ProductExt;
import com.meshop.product.service.ProductService;
import com.meshop.product.service.ProductServiceImpl;

@WebServlet(name="mainPageServlet", urlPatterns = "/main")
public class MainPageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 데이터 요청
		List<ProductExt> list = productService.findAll();
		
		//productList random shuffle
		Collections.shuffle(list);
		
		// 2. 뷰에 전달
		request.setAttribute("productList", list);
		request.getRequestDispatcher("/WEB-INF/views/board/main.jsp")
		//request.getRequestDispatcher("/index.jsp")
			.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
