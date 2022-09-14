package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberNickCheck
 */
@WebServlet("/member/nickCheck")
public class MemberNickCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String memberNickname = request.getParameter("memberNickname");
		System.out.println("memberNick@CheckIdDuplicateServlet = " + memberNickname);
		
		// 2. 업무로직
		Member member = memberService.findByMemberNickname(memberNickname);
		boolean available = (member == null);
		System.out.println(member);
		System.out.println(available);
		
		// 3. view단 처리
		request.setAttribute("available", available);
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberNickCheck.jsp")
			.forward(request, response);
	}
}