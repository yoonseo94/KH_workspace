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
import community.model.service.KnowhowService;

/**
 * Servlet implementation class KnowhowDelete
 */
@WebServlet("/knowhow/knowhowDelete")
public class KnowhowDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KnowhowService ks = new KnowhowService();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			int no = Integer.parseInt(request.getParameter("no"));

			List<Attachment> attachments = ks.findByNo(no).getAttachments();
			if (attachments != null && !attachments.isEmpty())
				for (Attachment attach : attachments) {
					String saveDirectory = getServletContext().getRealPath("/upload/community/knowhow");
					File delFile = new File(saveDirectory, attach.getRenamedFilename());
					if (delFile.exists()) {
						delFile.delete();
						System.out.println("> " + attach.getRenamedFilename() + "파일 삭제!");
					}
				}
			int result = ks.deleteBoard(no);
		
			request.getSession().setAttribute("msg", "노하우 삭제 완료");
			response.sendRedirect(request.getContextPath() + "/community/knowhow");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
