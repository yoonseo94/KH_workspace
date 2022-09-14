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
import com.oreilly.servlet.multipart.FileRenamePolicy;
import common.HomeSweetHomeFileRenamePolicy;
import community.model.dto.Attachment;
import community.model.dto.QnaNoticeExt;
import community.model.service.QnaNoticeService;

/**
 * Servlet implementation class qnaNoticeEnrollServlet
 */
@WebServlet("/qna/qnaNoticeEnroll")
public class qnaNoticeEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaNoticeService ns = new QnaNoticeService();
       

	//공지사항 작성 폼으로
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/community/qna/qnaNoticeEnroll.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String saveDirectory = getServletContext().getRealPath("/upload/community/qna");
			System.out.println("saveDirectory = " + saveDirectory);
			//최대파일크기 10MB 
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			HomeSweetHomeFileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
			MultipartRequest multiReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			//사용자입력값 처리
			String title = multiReq.getParameter("title");
			String memberId = multiReq.getParameter("memberId");
			String nickName = multiReq.getParameter("nickName");
			String content = multiReq.getParameter("content");
			
			QnaNoticeExt board = new QnaNoticeExt();
			board.setTitle(title);
			board.setMemberId(memberId);
			board.setNickName(nickName);
			board.setContent(content);
			
			File upFile1 = multiReq.getFile("upFile1");
			File upFile2 = multiReq.getFile("upFile2");
			
			// 첨부한 파일이 하나라도 있는 경우
			if(upFile1 != null || upFile2 != null) {
				List<Attachment> attachments = new ArrayList<>();
				if(upFile1 != null) 
					attachments.add(getAttachment(multiReq, "upFile1"));
				if(upFile2 != null) 
					attachments.add(getAttachment(multiReq, "upFile2"));
				board.setAttachments(attachments);
			}
			
			System.out.println("notice = " + board);
			
			// 4. 업무로직
			int result = ns.insertNotice(board);
			
			// 5. redirect
			response.sendRedirect(request.getContextPath() + "/qna/qnaNoticeView?no=" + board.getNo());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	private Attachment getAttachment(MultipartRequest multiReq, String name) {
		Attachment attach = new Attachment();
		String originalFilename = multiReq.getOriginalFileName(name); // 업로드한 파일명
		String renamedFilename = multiReq.getFilesystemName(name); // 저장된 파일명
		attach.setOriginalFilename(originalFilename);
		attach.setRenamedFilename(renamedFilename);
		return attach;
	
	
	}

}
