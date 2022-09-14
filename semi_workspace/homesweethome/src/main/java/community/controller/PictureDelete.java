package community.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.PictureAttachment;
import community.model.service.PictureService;

/**
 * Servlet implementation class PictureDelete
 */
@WebServlet("/picture/pictureDelete")
public class PictureDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureService ps = new PictureService();

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			
			List<PictureAttachment> attachments = ps.findByNo(no).getAttachments();
			if (attachments != null && !attachments.isEmpty())
				for (PictureAttachment attach : attachments) {
					String saveDirectory = getServletContext().getRealPath("/upload/community/picture");
					File delFile = new File(saveDirectory, attach.getRenamedFilename());
					if (delFile.exists()) {
						delFile.delete();
						System.out.println("> " + attach.getRenamedFilename() + "파일 삭제!");
					}
				}
			int result = ps.deleteBoard(no);
		
			request.getSession().setAttribute("msg", "노하우 삭제 완료");
			response.sendRedirect(request.getContextPath() + "/community/picture");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
