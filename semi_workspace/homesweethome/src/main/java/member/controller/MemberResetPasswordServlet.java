package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.HomeSweetHomeUtils;
import member.model.dto.Address;
import member.model.dto.Member;
import member.model.dto.MemberRole;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberResetPasswordServlet
 */
@WebServlet("/member/resetPassword")
public class MemberResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * 비밀번호 재설정 view단 출력
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		
		// 2. view단 처리
		request.setAttribute("memberId", memberId);
		request.getRequestDispatcher("/WEB-INF/views/member/memberResetPassword.jsp")
				.forward(request, response);	
	}

	/**
	 *  비밀번호 재설정 처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 사용자 입력값 처리
			String memberId = request.getParameter("memberId");
			String password = HomeSweetHomeUtils.encrypt(request.getParameter("password"), memberId);
			
			// 2. 업무로직 
			Member updateMember = memberService.findByMemberId(memberId);
			String msg = "";
			String location = request.getContextPath() + "/member/SignInPage";
			if(updateMember != null) {
				updateMember.setPassword(password);
				int result = memberService.resetPasswordOfMember(updateMember);
				msg = "비밀번호 재설정이 정상적으로 처리되었습니다.";
			}

			// 3. 리다이렉트처리
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(location);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}