package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.HomeSweetHomeUtils;
import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberPasswordUpdateServlet
 */
@WebServlet("/member/passwordUpdate")
public class MemberPasswordUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * 패스워드 수정 요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { // alt shift z 로 트라이캐치절 감싸기
			// 1. 사용자입력값 처리
			String memberId = request.getParameter("memberId");
			String oldPassword = HomeSweetHomeUtils.encrypt(request.getParameter("oldPassword"), memberId);
			String newPassword = HomeSweetHomeUtils.encrypt(request.getParameter("newPassword"), memberId);
			
			System.out.println("memberId = " + memberId);
			System.out.println("oldPassword = " + oldPassword);
			System.out.println("newPassword = " + newPassword);
			// 2. 업무로직 - 기존비밀번호가 일치하는 경우에만 비밀번호를 수정
			Member member = memberService.findByMemberId(memberId);
			String msg = "";
			String location = request.getContextPath();
			// 성공 실패 분기처리
			if(member != null && oldPassword.equals(member.getPassword())) {
				// 비밀번호 수정
				Member updateMember = new Member();
				updateMember.setMemberId(memberId);
				updateMember.setPassword(newPassword);
				int result = memberService.updatePassword(updateMember);
				msg = "비밀번호를 성공적으로 변경했습니다.";
				location += "/member/mypage";
			}
			else {
				msg = "기존 비밀번호가 일치하지 않습니다.";
				location += "/member/memberPassword";
			}
			
			// 3. 리다이렉트처리
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(location);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
