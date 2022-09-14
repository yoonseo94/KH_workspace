package member.controller;

import java.io.IOException;
import java.sql.Date;

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
 * Servlet implementation class MemberSignUpSocial
 */
@WebServlet("/member/memberSignUpSocial")
public class MemberSignUpSocial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("id");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String socialType = request.getParameter("socialType");
		
		request.setAttribute("id", memberId);
		request.setAttribute("gender", gender);
		request.setAttribute("email", email);
		request.setAttribute("socialType", socialType);
		 
		request.getRequestDispatcher("/WEB-INF/views/member/memberSignUpSocialPage.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.. 사용자입력값 처리
				// 1.1. 배송지
				String postCode = request.getParameter("postcode");
				String address = request.getParameter("address");
				String addressDetail = request.getParameter("address_detail");
				String addressExtra = request.getParameter("extraAddress");

				// 1.2. 회원
				String memberId = request.getParameter("id");
				String memberName = request.getParameter("user_name");
				String nickname = request.getParameter("user_nickname");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				int _day = Integer.parseInt(request.getParameter("day"));
				String day = null;
				if(_day < 10) {
					day = "0" + String.valueOf(_day);
				}
				else {
					day = String.valueOf(_day);				
				}
				String _birthday = year + "-" + month + "-" + day;
				
				String _gender = request.getParameter("gender");
				String gender = "";
				if(_gender != null) {
					gender = _gender == "male" ? "M" : "F";					
				}
				
		
				Date birthday = null;
				if(_birthday != null && !"".equals(_birthday))
					birthday = Date.valueOf(_birthday);
				
			String socialType = request.getParameter("socialType");
			System.out.println("servlet : socialType = " + socialType);
			Member member = new Member(memberId, null, memberName, nickname, MemberRole.U, phone, email, birthday, gender, socialType, null);
			Address addressInfo = new Address();
			addressInfo.setMemberId(memberId);
			addressInfo.setPostCode(postCode);
			addressInfo.setAddress(address);
			addressInfo.setAddressDetail(addressDetail);
			addressInfo.setAddressExtra(addressExtra);
			
			
			// 2. 업무로직 (db insert) 
			 int resultMember = memberService.insertMember(member); 
			 int resultAddr = memberService.insertMemberAddr(addressInfo);
			
			String msg = "성공적으로 회원가입했습니다.";
			
			// 3. 리다이렉트 
			request
				.getSession()
				.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}