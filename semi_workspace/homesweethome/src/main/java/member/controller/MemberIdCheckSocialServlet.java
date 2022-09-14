package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/member/memberIdCheckSocial")
public class MemberIdCheckSocialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String id = request.getParameter("memberId");
		System.out.println("memberId@CheckIdDuplicateServlet = " + id);
		
		// 2. 업무로직
		Member member = memberService.findByMemberId(id);
		boolean available = (member == null);
		System.out.println("available = " + available);
		
		String idExistence = "F";
		if(available == false) {
			idExistence = "T";
		}
		response.getWriter().append(idExistence);
			
	}
}