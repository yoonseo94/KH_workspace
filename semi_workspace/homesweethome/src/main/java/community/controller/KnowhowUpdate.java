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
import community.model.dto.KnowhowExt;
import community.model.dto.QnaNoticeExt;
import community.model.service.KnowhowService;

/**
 * Servlet implementation class KnowhowUpdate
 */
@WebServlet("/knowhow/knowhowUpdate")
public class KnowhowUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KnowhowService ks = new KnowhowService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		KnowhowExt knowhow = ks.findByNo(no);
		
		request.setAttribute("knowhow", knowhow);
		request.getRequestDispatcher("/WEB-INF/views/community/knowhow/knowhowUpdate.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = getServletContext().getRealPath("/upload/community/knowhow");
		int maxPostSize = 1024 * 1024 * 10;
		String encoding = "utf-8";
		FileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
		MultipartRequest multiReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
	
		
		int no = Integer.parseInt(multiReq.getParameter("no"));
		String title = multiReq.getParameter("title");
		String memberId = multiReq.getParameter("memberId");
		String nickName = multiReq.getParameter("nickName");
		String content = multiReq.getParameter("content");
		String[] delFiles = multiReq.getParameterValues("delFile"); // 삭제하려는 첨부파일 pk
	
		KnowhowExt knowhow = new KnowhowExt();
		knowhow.setNo(no);
		knowhow.setTitle(title);
		knowhow.setMemberId(memberId);
		knowhow.setContent(content);
		knowhow.setNickName(nickName);
		
		File upFile1 = multiReq.getFile("upFile1");
		File upFile2 = multiReq.getFile("upFile2");
		if(upFile1 != null || upFile2 != null) {
			List<Attachment> attachments = new ArrayList<>();
			if(upFile1 != null)
				attachments.add(getAttachment(multiReq, no, "upFile1"));
			if(upFile2 != null)
				attachments.add(getAttachment(multiReq, no, "upFile2"));
			knowhow.setAttachments(attachments);
		}
		
		
		int result = ks.updateBoard(knowhow);
		
		// 첨부파일 삭제 처리
		if(delFiles != null) {
			for(String temp : delFiles) {
				int attachNo = Integer.parseInt(temp); // attachment pk
				Attachment attach = ks.findAttachmentByNo(attachNo);

				File delFile = new File(saveDirectory, attach.getRenamedFilename());
				if(delFile.exists()) delFile.delete();

				result = ks.deleteAttachment(attachNo);
				System.out.println("> " + attachNo + "번 첨부파일 (" + attach.getRenamedFilename() + ") 삭제!");
			}
		}

		response.sendRedirect(request.getContextPath() + "/knowhow/knowhowListView?no=" + no);
		
		
	}

	private Attachment getAttachment(MultipartRequest multiReq, int no, String name) {
		Attachment attach = new Attachment();
		attach.setBoardNo(no);
		attach.setOriginalFilename(multiReq.getOriginalFileName(name));
		attach.setRenamedFilename(multiReq.getFilesystemName(name));
		return attach;
	}

}
