package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.QnaNoticeService;

/**
 * Servlet implementation class qnaNoticeCommentDeleteServlet
 */
@WebServlet("/qna/qnaNoticeCommentDelete")
public class qnaNoticeCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private QnaNoticeService ns = new QnaNoticeService();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("noticeNo = "+noticeNo+", no= "+no);
			
			int result = ns.deleteNoticeComment(no);
			
			request.getSession().setAttribute("msg", "공지 댓글 삭제 성공!");
			response.sendRedirect(request.getContextPath() + "/qna/qnaNoticeView?no=" + noticeNo);
		}catch(Exception e) {
			System.out.println("공지 댓글 삭제 오류");
			e.printStackTrace();
		}
	}

}
