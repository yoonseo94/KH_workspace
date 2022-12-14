package board.question.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.BoardExt;
import board.model.service.QuestionService;
import common.PageBar;

/**
 * Servlet implementation class QuestionListServlet
 */
@WebServlet("/board/questionList")
public class QuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService = new QuestionService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			int numPerPage = 13;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {}
			
			Map<String, Object> param = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			param.put("start", start);
			param.put("end", end);
			
			// 2. 업무로직
			// 2.a content영역
			List<BoardExt> list = questionService.findAll(param);
			System.out.println(list);
			// 2.b pagebar영역
			int totalContents = questionService.getTotalContents();
			String url = request.getRequestURI();
			String pagebar = PageBar.getPagebar(cPage, numPerPage, totalContents, url);
			System.out.println("pagebar = " + pagebar);
				
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/board/question/questionList.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
