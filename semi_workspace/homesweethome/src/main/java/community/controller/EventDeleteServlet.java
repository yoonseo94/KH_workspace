package community.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.EventAttachment;
import community.model.service.EventService;

/**
 * Servlet implementation class eventDeleteServlet
 */
@WebServlet("/event/eventDelete")
public class EventDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService es = new EventService();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			List<EventAttachment> attachments = es.findByNo(no).getAttachments();
			if (attachments != null && !attachments.isEmpty())
				for (EventAttachment attach : attachments) {
					String saveDirectory = getServletContext().getRealPath("/upload/community/event");
					File delFile = new File(saveDirectory, attach.getRenamed_filename());
					if (delFile.exists()) {
						delFile.delete();
						System.out.println("> " + attach.getRenamed_filename() + "파일 삭제!");
					}
				}
			int result = es.deleteBoard(no);
		
			request.getSession().setAttribute("msg", "게시글 삭제완료");
			response.sendRedirect(request.getContextPath()+"/community/eventList");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}