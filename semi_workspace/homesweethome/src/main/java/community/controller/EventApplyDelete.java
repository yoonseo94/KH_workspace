package community.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.Attachment;
import community.model.dto.EventAppAtt;
import community.model.service.EventAppService;

/**
 * Servlet implementation class eventApplyDelete
 */
@WebServlet("/event/eventApplyDelete")
public class EventApplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventAppService es = new EventAppService(); 
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			int no = Integer.parseInt(request.getParameter("no"));

			List<EventAppAtt> attachments = es.findByNo(no).getAttachments();
			if (attachments != null && !attachments.isEmpty())
				for (EventAppAtt attach : attachments) {
					String saveDirectory = getServletContext().getRealPath("/upload/community/event");
					File delFile = new File(saveDirectory, attach.getRenamedFilename());
					if (delFile.exists()) {
						delFile.delete();
						System.out.println("> " + attach.getRenamedFilename() + "파일 삭제!");
					}
				}
			int result = es.deleteBoard(no);

			request.getSession().setAttribute("msg", "이벤트 참여작 삭제 완료");
			response.sendRedirect(request.getContextPath() + "/event/eventApplyList");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
