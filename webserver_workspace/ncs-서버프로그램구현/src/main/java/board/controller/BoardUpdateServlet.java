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
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			int bId = Integer.parseInt(request.getParameter("bId"));
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			Board board = new Board();
			board.setbId(bId);
			board.setbTitle(title);
			board.setbWriter(writer);
			board.setbContent(content);

			int result = boardService.updateBoard(board);
			
			response.sendRedirect(request.getContextPath() + "detail.bo?bId=" + bId);

		} catch (Exception e) {
			e.printStackTrace();
			throw e; // WAS에 예외상황 알림. 에러페이지 전환
		}
	}

}
