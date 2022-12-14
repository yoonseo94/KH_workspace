package com.kh.ajax.jquery;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ajax.celeb.dto.Celeb;
import com.kh.ajax.celeb.manager.CelebManager;

/**
 * Servlet implementation class HtmlServlet
 */
@WebServlet("/jquery/html")
public class HtmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		
		// 2. 업무로직
		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
		
		// 3. view단 처리
		request.setAttribute("celebList", celebList);
		request.getRequestDispatcher("/WEB-INF/views/jquery/html/celebList.jsp")
			.forward(request, response);
		
		
	}

}
