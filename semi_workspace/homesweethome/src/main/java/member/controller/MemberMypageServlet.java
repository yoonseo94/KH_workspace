package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberMypageServlet
 */
@WebServlet("/member/mypage")
public class MemberMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내정보 보기 페이지
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/memberMypage.jsp");
		reqDispatcher.forward(request, response);
	}


}
