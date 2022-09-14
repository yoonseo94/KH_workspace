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

@WebServlet(name="memberFindPwServlet", urlPatterns = "/member/find/pw")
public class MemberFindPwServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/auth/find_password.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("id");
		
		MemberService memberServivce = new MemberServiceImpl();
		String result = memberServivce.findPw(memberId);
		
		System.out.println("result "+ result);
		
		if(result != null) {
//			request.setAttribute("loginError","Incorrect password");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=EUC-KR");
			response.setCharacterEncoding("EUC-KR");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('"+ result + "');");
//			out.println("location='/meshop/member/login';");
			out.println("</script>");
			
		}
		
		
//        RequestDispatcher reqDispatcher = request.getRequestDispatcher(request.getContextPath() + "/main");
//        reqDispatcher.forward(request, response);
	
	}

}
