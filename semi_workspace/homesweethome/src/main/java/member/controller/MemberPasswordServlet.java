package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberPasswordServlet
 */
@WebServlet("/member/memberPassword")
public class MemberPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 변경 페이지
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/memberPasswordUpdate.jsp");
		reqDispatcher.forward(request, response);	
	}

}
