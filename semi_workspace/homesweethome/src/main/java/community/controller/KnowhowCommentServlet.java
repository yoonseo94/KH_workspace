package community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.KnowhowComment;
import community.model.service.KnowhowService;

/**
 * Servlet implementation class KnowhowComment
 */
@WebServlet("/knowhow/knowhowComment")
public class KnowhowCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KnowhowService ks = new KnowhowService();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.사용자 입력값 처리
			int knowhowNo = Integer.parseInt(request.getParameter("knowhowNo"));
			int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
			int commentRef = Integer.parseInt(request.getParameter("commentRef"));
			String memberId = request.getParameter("memberId");
			String nickName = request.getParameter("nickName");
			String content = request.getParameter("content");
			
			KnowhowComment kc = 
			new KnowhowComment(0,commentLevel,commentRef,knowhowNo,memberId,nickName,content,0,null) ;
			System.out.println("boardComment = " + kc);

			int result = ks.insertBoardComment(kc);
			
			

			response.sendRedirect(request.getContextPath() + "/knowhow/knowhowListView?no=" + knowhowNo);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
