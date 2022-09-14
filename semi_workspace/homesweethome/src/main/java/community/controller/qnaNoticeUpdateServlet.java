package community.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.HomeSweetHomeFileRenamePolicy;
import community.model.dto.Attachment;
import community.model.dto.QnaNoticeExt;
import community.model.service.QnaNoticeService;

/**
 * Servlet implementation class qnaNoticeUpdateServlet
 */
@WebServlet("/qna/qnaNoticeUpdate")
public class qnaNoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaNoticeService ns = new QnaNoticeService();
	
	//공지사항 수정폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		QnaNoticeExt board = ns.findByNo(no);
		
		// 3.view단처리
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/community/qna/qnaNoticeUpdate.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String saveDirectory = getServletContext().getRealPath("/upload/community/qna");
		int maxPostSize = 1024 * 1024 * 10;
		String encoding = "utf-8";
		HomeSweetHomeFileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
		MultipartRequest multiReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
	
		
		int no = Integer.parseInt(multiReq.getParameter("no"));
		String title = multiReq.getParameter("title");
		String memberId = multiReq.getParameter("memberId");
		String content = multiReq.getParameter("content");
		String[] delFiles = multiReq.getParameterValues("delFile"); // 삭제하려는 첨부파일 pk
	
		QnaNoticeExt board = new QnaNoticeExt();
		board.setNo(no);
		board.setTitle(title);
		board.setMemberId(memberId);
		board.setContent(content);
		
		File upFile1 = multiReq.getFile("upFile1");
		File upFile2 = multiReq.getFile("upFile2");
		if(upFile1 != null || upFile2 != null) {
			List<Attachment> attachments = new ArrayList<>();
			if(upFile1 != null)
				attachments.add(getAttachment(multiReq, no, "upFile1"));
			if(upFile2 != null)
				attachments.add(getAttachment(multiReq, no, "upFile2"));
			board.setAttachments(attachments);
		}
		
		
		int result = ns.updateNotice(board);
		// 첨부파일 삭제 처리
		if(delFiles != null) {
			for(String temp : delFiles) {
				int attachNo = Integer.parseInt(temp); // attachment pk
				Attachment attach = ns.findAttachmentByNo(attachNo);

				File delFile = new File(saveDirectory, attach.getRenamedFilename());
				if(delFile.exists()) delFile.delete();

				result = ns.deleteAttachment(attachNo);
				System.out.println("> " + attachNo + "번 첨부파일 (" + attach.getRenamedFilename() + ") 삭제!");
			}
		}
		// 5. redirect
		response.sendRedirect(request.getContextPath() + "/qna/qnaNoticeView?no=" + no);
		
	}

	private Attachment getAttachment(MultipartRequest multiReq, int no, String name) {
		Attachment attach = new Attachment();
		attach.setBoardNo(no);
		attach.setOriginalFilename(multiReq.getOriginalFileName(name));
		attach.setRenamedFilename(multiReq.getFilesystemName(name));
		return attach;
	}

}
