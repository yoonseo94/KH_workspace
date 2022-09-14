package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberFindIdByEmail
 */
@WebServlet("/member/findMemberByEmail")
public class MemberFindIdByEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		String memberEmail = request.getParameter("memberEmail");
		System.out.println(memberEmail);
		// 2. 업무로직
		Member member = memberService.findMemberIdByEmail(memberEmail);
		boolean available = (member != null);
		
		// 3. view단 처리
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 3.1. available
		if(available == true) {
			String memberId = member.getMemberId();
			out.append(memberId);
		}
	}
}