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
import community.model.dto.EventAppAtt;
import community.model.dto.EventAppExt;
import community.model.service.EventAppService;

/**
 * Servlet implementation class eventApplyUpdate
 */
@WebServlet("/event/eventApplyUpdate")
public class EventApplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventAppService es = new EventAppService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		EventAppExt eventapp = es.findByNo(no);

		request.setAttribute("eventapp", eventapp);
		request.getRequestDispatcher("/WEB-INF/views/community/event/eventApplyUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String saveDirectory = getServletContext().getRealPath("/upload/community/event");
		int maxPostSize = 1024 * 1024 * 10;
		String encoding = "utf-8";
		FileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
		MultipartRequest multiReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
	
		
		int no = Integer.parseInt(multiReq.getParameter("no"));
		String eventapplyCode = multiReq.getParameter("eventapplyCode");
		String memberId = multiReq.getParameter("memberId");
		String nickName = multiReq.getParameter("nickName");
		String content = multiReq.getParameter("content");
		String[] delFiles = multiReq.getParameterValues("delFile"); // 삭제하려는 첨부파일 pk
	
		EventAppExt eventapp = new EventAppExt();
		eventapp.setNo(no);
		eventapp.setEventapplyCode(eventapplyCode);
		eventapp.setMemberId(memberId);
		eventapp.setNickName(nickName);
		eventapp.setContent(content);
		

		
		File upFile1 = multiReq.getFile("upFile1");
		File upFile2 = multiReq.getFile("upFile2");
		if(upFile1 != null || upFile2 != null) {
			List<EventAppAtt> attachments = new ArrayList<>();
			if(upFile1 != null)
				attachments.add(getAttachment(multiReq, no, "upFile1"));
			if(upFile2 != null)
				attachments.add(getAttachment(multiReq, no, "upFile2"));
			eventapp.setAttachments(attachments);
		}
		
		
		int result = es.updateBoard(eventapp);
		
		// 첨부파일 삭제 처리
		if(delFiles != null) {
			for(String temp : delFiles) {
				int attachNo = Integer.parseInt(temp); // attachment pk
				EventAppAtt attach = es.findAttachmentByNo(attachNo);

				File delFile = new File(saveDirectory, attach.getRenamedFilename());
				if(delFile.exists()) delFile.delete();

				result = es.deleteAttachment(attachNo);
				System.out.println("> " + attachNo + "번 첨부파일 (" + attach.getRenamedFilename() + ") 삭제!");
			}
		}

		response.sendRedirect(request.getContextPath() + "/event/eventApplyView?no=" + no);

	}

	private static EventAppAtt getAttachment(MultipartRequest multiReq, int no, String name) {
		EventAppAtt attach = new EventAppAtt();
		attach.setNo(no);
		attach.setOriginalFilename(multiReq.getOriginalFileName(name));
		attach.setRenamedFilename(multiReq.getFilesystemName(name));
		return attach;
	}

}