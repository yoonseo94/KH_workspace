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
import com.meshop.member.exception.MemberException;
import com.meshop.member.service.MemberService;
import com.meshop.member.service.MemberServiceImpl;
import com.meshop.wish.service.WishService;
import com.meshop.wish.service.WishServiceImpl;


@WebServlet(name="memberLoginServlet", urlPatterns = "/login")
public class MemberLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String memberId = request.getParameter("memberId");
			String password = request.getParameter("password");

			Member member = new Member();
			member.setMemberId(memberId);
			member.setPassword(password);
				
			System.out.println("memberId : " + memberId);
			System.out.println("password : " + password);

			Member findMember = null;
			MemberService memberService = new MemberServiceImpl();
			findMember = memberService.findMember(member);

			if(findMember.getMemberId() != null && memberId.equals(findMember.getMemberId()) && password.equals(findMember.getPassword())) {
				// 로그인 했을 때 찜 목록 가져오기
				WishService wishService = new WishServiceImpl();
				List<Integer> wishList = wishService.findByMemberId(memberId);
				findMember.setWish(wishList);

				HttpSession session = request.getSession();

				session.setAttribute("loginMember", findMember);
				
				response.sendRedirect(request.getContextPath() +"/main");
			}else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().print("<script>alert('아이디 및 비밀번호 확인'); history.back();</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
