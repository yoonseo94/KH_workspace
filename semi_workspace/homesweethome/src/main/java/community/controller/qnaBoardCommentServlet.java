package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.QnaBoardComment;
import community.model.service.QnaBoardService;

/**
 * Servlet implementation class QnaBoardCommentServlet
 */
@WebServlet("/qna/qnaBoardComment")
public class qnaBoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService bs = new QnaBoardService();
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.사용자 입력값 처리
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
			int commentRef = Integer.parseInt(request.getParameter("commentRef"));
			String memberId = request.getParameter("memberId");
			String nickName = request.getParameter("nickName");
			String content = request.getParameter("content");
			
			QnaBoardComment bc = 
			new QnaBoardComment(0, boardNo,memberId,nickName,content,0,null,commentLevel,commentRef );
			System.out.println("boardComment = " + bc);
			
			
			//2.업무로직

			int result = bs.insertBoardComment(bc);
			
			
			//3.리다이렉트
			response.sendRedirect(request.getContextPath() + "/qna/qnaBoardView?no=" + boardNo);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
