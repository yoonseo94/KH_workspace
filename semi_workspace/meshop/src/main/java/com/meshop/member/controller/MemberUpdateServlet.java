package com.meshop.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meshop.member.entity.Member;
import com.meshop.member.service.MemberService;
import com.meshop.member.service.MemberServiceImpl;

@WebServlet(name="memberupdateServlet", urlPatterns = "/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MemberService memberSerivce = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String storeName = request.getParameter("storeName");
		String password = request.getParameter("password");
		String region = request.getParameter("region");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setStoreName(storeName);
		member.setPassword(password);
		member.setPlace(region);
		
		System.out.println("update" + member);
		
		int result = memberSerivce.updateMember(member);
		
		// 1. session객체 가져오기
		// session객체가 존재하지 않으면, null을 리턴.
		HttpSession session = request.getSession(false);
		if(session != null)
			session.invalidate();
		
		// 2. redirect
		response.sendRedirect(request.getContextPath() + "/main");
	}
	
}
