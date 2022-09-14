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

@WebServlet(name="memberDoubleServlet", urlPatterns = "/member/id-check")
public class MemberDoubleCheckServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 값
		String memberId = request.getParameter("memberId");
		System.out.println("memberId : " + memberId);
		
		// 2. DB 로직
		Member member = new Member();
		member.setMemberId(memberId);
		
		MemberService memberService = new MemberServiceImpl();
		boolean result = memberService.doubleCheck(memberId);
		
		// 3. view 전달
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
