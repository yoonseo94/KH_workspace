package customerservice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customerservice.model.service.CustomerServiceService;
import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class CSSweetTalkServlet
 */
@WebServlet("/customerservice/sweettalk")
public class CSSweetTalkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private CustomerServiceService customerServiceService = new CustomerServiceService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId =  request.getParameter("loginMemberId");
		System.out.println("memberId = " + memberId);
		Member loginMember =  memberService.findByMemberId(memberId);
		System.out.println("loginMember = " + loginMember);
		
		request.setAttribute("loginMember", loginMember);
		request.getRequestDispatcher("/WEB-INF/views/common/sweettalk.jsp")
		.forward(request, response);

	}

}
