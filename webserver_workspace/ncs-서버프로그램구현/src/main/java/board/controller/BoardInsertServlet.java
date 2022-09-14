package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/binsert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String boardTitle = request.getParameter("btitle");
			String boardWriter = request.getParameter("bwriter");
			String boardContent = request.getParameter("bcontent");
					
			Board b = new Board();
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardWriter);
			b.setBoardWriter(boardContent);
			int result = boardService.insertBoard(b);
			
			response.sendRedirect("/first/blist?page=1");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // WAS에 예외상황 알림. 에러페이지 전환
		}
	}

}
