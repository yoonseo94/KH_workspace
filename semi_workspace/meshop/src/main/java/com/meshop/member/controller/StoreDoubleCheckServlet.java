package com.meshop.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meshop.member.entity.Member;
import com.meshop.member.service.MemberService;
import com.meshop.member.service.MemberServiceImpl;
import com.meshop.wish.service.WishService;
import com.meshop.wish.service.WishServiceImpl;

@WebServlet(name="storeDoublecheckServlet", urlPatterns = "/member/store-check")
public class StoreDoubleCheckServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 값
		String storeName = request.getParameter("storeName");
		System.out.println("storeName : " + storeName);
		
		// 2. DB 로직
		MemberService memberService = new MemberServiceImpl();
		boolean result = memberService.storeDoubleCheck(storeName);
		
		// 3. view 전달
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
