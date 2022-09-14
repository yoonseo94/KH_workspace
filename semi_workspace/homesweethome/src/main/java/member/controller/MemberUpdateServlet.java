package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// 1. 사용자입력값처리
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String nickname = request.getParameter("memberNickname");
		String email = request.getParameter("memberEmail");
		String phone = request.getParameter("memberPhone");
		String gender = request.getParameter("gender");
		
		// null값에 들어갈 것 확인하기!
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberName(memberName);
		member.setNickname(nickname);
		member.setPhone(phone);
		member.setEmail(email);
		member.setGender(gender);
		// 3. 업무로직 - service에 updateMember요청
		int result = memberService.updateMember(member);
		String msg = "회원정보를 성공적으로 수정했습니다."; 
		
		// 세션 정보 갱신
		Member updateMember = memberService.findByMemberId(memberId);
		request.getSession().setAttribute("loginMember", updateMember);
		
		// 4. redirect - msg는 session에 저장
		request.getSession().setAttribute("msg", msg); // redirect 후에 꺼내서 출력
		response.sendRedirect(request.getContextPath() + "/member/mypage");
		
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
