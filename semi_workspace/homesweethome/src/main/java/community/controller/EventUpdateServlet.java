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
import community.model.dto.EventAttachment;
import community.model.dto.EventExt;
import community.model.service.EventService;

/**
 * Servlet implementation class eventUpdateServlet
 */
@WebServlet("/event/eventUpdate")
public class EventUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService es = new EventService();
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		EventExt event = es.findByNo(no);

		request.setAttribute("event", event);
		request.getRequestDispatcher("/WEB-INF/views/community/event/eventUpdate.jsp")
			.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String saveDirectory = getServletContext().getRealPath("/upload/community/event");
		int maxPostSize = 1024 * 1024 * 10;
		String encoding = "utf-8";
		 HomeSweetHomeFileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
		MultipartRequest multiReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
	
	
		int no = Integer.parseInt(multiReq.getParameter("no"));
		String title = multiReq.getParameter("title");
		String content = multiReq.getParameter("content");
		String[] delFiles = multiReq.getParameterValues("delFile"); 
	
		EventExt event = new EventExt();
		event.setNo(no);
		event.setEventTitle(title);
		event.setEventContent(content);
		
		File upFile1 = multiReq.getFile("upFile1");
		File upFile2 = multiReq.getFile("upFile2");
		if(upFile1 != null || upFile2 != null) {
			List<EventAttachment> attachments = new ArrayList<>();
			if(upFile1 != null)
				attachments.add(getAttachment(multiReq, no, "upFile1"));
			if(upFile2 != null)
				attachments.add(getAttachment(multiReq, no, "upFile2"));
			event.setAttachments(attachments);
		}
		
		int result = es.updateBoard(event);

		
		if(delFiles != null) {
			for(String temp : delFiles) {
				int attachNo = Integer.parseInt(temp); 
				EventAttachment attach = es.findAttachmentByNo(attachNo);
				File delFile = new File(saveDirectory, attach.getRenamed_filename());
				if(delFile.exists()) delFile.delete();

				result = es.deleteAttachment(attachNo);
				System.out.println("> " + attachNo + "번 첨부파일 (" + attach.getRenamed_filename() + ") 삭제!");
			}
		}
		response.sendRedirect(request.getContextPath() + "/community/eventView?no=" + no);

	}


	private EventAttachment getAttachment(MultipartRequest multiReq, int no, String string) {
		EventAttachment attach = new EventAttachment();
		attach.setEventNo(no);
		attach.setOriginal_filename(multiReq.getOriginalFileName(string));
		attach.setRenamed_filename(multiReq.getFilesystemName(string));	
		return attach;
	}

}
