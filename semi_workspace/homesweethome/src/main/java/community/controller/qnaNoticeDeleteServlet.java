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
import community.model.service.QnaNoticeService;

/**
 * Servlet implementation class qnaNoticeDeleteServlet
 */
@WebServlet("/qna/qnaNoticeDelete")
public class qnaNoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaNoticeService ns = new QnaNoticeService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			int no = Integer.parseInt(request.getParameter("no"));

			List<Attachment> attachments = ns.findByNo(no).getAttachments();
			if (attachments != null && !attachments.isEmpty())
				for (Attachment attach : attachments) {
					String saveDirectory = getServletContext().getRealPath("/upload/community/qna");
					File delFile = new File(saveDirectory, attach.getRenamedFilename());
					if (delFile.exists()) {
						delFile.delete();
						System.out.println("> " + attach.getRenamedFilename() + "파일 삭제!");
					}
				}

			// board 레코드(행) 삭제 (attachment는 on delete cascade에 의해 자동으로 제거된다.)
			int result = ns.deleteNotice(no);

		
			request.getSession().setAttribute("msg", "공지 삭제 완료");
			response.sendRedirect(request.getContextPath() + "/community/qna");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
