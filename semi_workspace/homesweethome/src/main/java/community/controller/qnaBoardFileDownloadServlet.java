package community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.Attachment;
import common.HomeSweetHomeUtils;
import community.model.service.QnaBoardService;

/**
 * Servlet implementation class QnaBoardFileDownloadServlet
 */
@WebServlet("/board/fileDownload")
public class qnaBoardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService bs = new QnaBoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.사용자입력값처리
		int no = Integer.parseInt(request.getParameter("no"));

		// 2.업무로직
		Attachment attach = bs.findAttachmentByNo(no);
		System.out.println(attach);

		String saveDirectory = getServletContext().getRealPath("/upload/board");
		String originalFilename = attach.getOriginalFilename();
		String renamedFilename = attach.getRenamedFilename();

		// 3.응답처리
		HomeSweetHomeUtils.fileDownload(response, saveDirectory, originalFilename, renamedFilename);
	}
}