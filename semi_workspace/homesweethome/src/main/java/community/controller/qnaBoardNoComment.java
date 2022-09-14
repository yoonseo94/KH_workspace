package community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.QnaBoard;
import community.model.dto.QnaBoardComment;
import community.model.service.QnaBoardService;

/**
 * Servlet implementation class qnaBoardNoComment
 */
@WebServlet("/qna/qnaBoardNoComment")
public class qnaBoardNoComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       QnaBoardService bs = new QnaBoardService();
  
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
    	int[] replycount = new int[300];
   		int i = 0;
   		
   		List<QnaBoard> comlist = bs.noComment();
   		for(QnaBoard qb : comlist) {
   			int result = bs.commentCount(qb.getNo());
   			replycount[i++] = result;
   		}
   			
   		System.out.println(comlist);
   		System.out.println(replycount);
   		
   		request.setAttribute("comlist", comlist);
   		request.setAttribute("replycount", replycount);
   		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/community/qna/qnaBoardNoComment.jsp");
   		reqDispatcher.forward(request, response);
   	}



}