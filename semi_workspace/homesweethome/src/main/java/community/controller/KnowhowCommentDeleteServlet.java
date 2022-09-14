package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.KnowhowService;

/**
 * Servlet implementation class KnowhowCommentDeleteServlet
 */
@WebServlet("/knowhow/knowhowCommentDelete")
public class KnowhowCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private KnowhowService ks = new KnowhowService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int knowhowNo = Integer.parseInt(request.getParameter("knowhowNo"));
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("knowhowNo = "+knowhowNo+", no= "+no);
			
			int result = ks.deleteBoardComment(no);
			
			request.getSession().setAttribute("msg", "댓글 삭제 성공!");
			response.sendRedirect(request.getContextPath() + "/knowhow/knowhowListView?no=" + knowhowNo);
		}catch(Exception e) {
			System.out.println("댓글 삭제 오류");
			e.printStackTrace();
		}
	}

}
