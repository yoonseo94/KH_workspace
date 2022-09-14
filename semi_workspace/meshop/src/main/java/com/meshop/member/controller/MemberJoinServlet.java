package com.meshop.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meshop.member.entity.Member;
import com.meshop.member.entity.MemberRole;
import com.meshop.member.service.MemberService;
import com.meshop.member.service.MemberServiceImpl;

@WebServlet(name="memberJoinServlet", urlPatterns = "/member/join")
public class MemberJoinServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/join.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// 1. 사용자 입력값
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String memberName =  request.getParameter("memberName");
		String storeName =  request.getParameter("storeName");
		String place =  request.getParameter("region");
		
		Member member = new Member();
		
		member.setMemberId(memberId);
		member.setPassword(password);
		member.setMemberName(memberName);
		member.setStoreName(storeName);
		member.setPlace(place);
		member.setMemberRole(MemberRole.U);
		
		System.out.println(member);
		
		// 2. DB 로직
		MemberService memberService = new MemberServiceImpl();
		memberService.insertMember(member);
		
		response.sendRedirect(request.getContextPath() +"/main");
		}catch(Exception e) {
			// 예외던지기 - 톰캣에 통보
			throw e;
		}
	}
}
