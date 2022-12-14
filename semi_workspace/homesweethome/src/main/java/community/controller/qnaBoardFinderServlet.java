package community.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.QnaBoard;
import community.model.dto.QnaBoardExt;
import community.model.service.QnaBoardService;
import member.model.dto.Member;

/**
 * Servlet implementation class qnaFinderServlet
 */
@WebServlet("/qna/qnaBoardFinder")
public class qnaBoardFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService bs = new QnaBoardService();
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자입력값처리
		String searchType = request.getParameter("searchType"); // member_id member_name gender
		String searchKeyword = request.getParameter("searchKeyword");
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param = " + param);
		
		// 2. 업무로직
		List<QnaBoard> list = bs.findBy(param);
		System.out.println("list = " + list);
		
		// 3. view단처리
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/community/qna/qnaBoardFinder.jsp")
			.forward(request, response);

	}

}
