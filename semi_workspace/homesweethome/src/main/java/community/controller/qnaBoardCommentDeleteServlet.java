package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.QnaBoardService;

/**
 * Servlet implementation class QnaBoardCommentServlet
 */
@WebServlet("/qna/qnaBoardCommentDelete")
public class qnaBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService bs = new QnaBoardService();
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("boardNo = "+boardNo+", no= "+no);
			
			int result = bs.deleteBoardComment(no);
			
			request.getSession().setAttribute("msg", "댓글 삭제 성공!");
			response.sendRedirect(request.getContextPath() + "/qna/qnaBoardView?no=" + boardNo);
		}catch(Exception e) {
			System.out.println("댓글 삭제 오류");
			e.printStackTrace();
		}
	
	}

}
