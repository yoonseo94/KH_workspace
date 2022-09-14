package community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.QnaNoticeComment;
import community.model.service.QnaNoticeService;

/**
 * Servlet implementation class qnaNoticeCommentServlet
 */
@WebServlet("/qna/qnaNoticeComment")
public class qnaNoticeCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QnaNoticeService ns = new QnaNoticeService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.사용자 입력값 처리
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
			int commentRef = Integer.parseInt(request.getParameter("commentRef"));
			String memberId = request.getParameter("memberId");
			String content = request.getParameter("content");
			String nickName = request.getParameter("nickName");
			
			QnaNoticeComment nc = 
			new QnaNoticeComment(0, memberId, nickName, noticeNo, content, null,0,commentLevel, commentRef);
			System.out.println("noticeComment = " + nc);
			
			
			//2.업무로직
			int result = ns.insertNoticeComment(nc);
			
			//3.리다이렉트
			response.sendRedirect(request.getContextPath() + "/qna/qnaNoticeView?no=" + noticeNo);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
