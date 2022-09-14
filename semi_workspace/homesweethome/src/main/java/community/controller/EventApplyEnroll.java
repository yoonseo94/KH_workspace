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
import community.model.dto.EventAppAtt;
import community.model.dto.EventAppExt;
import community.model.service.EventAppService;

/**
 * Servlet implementation class eventApplyEnroll
 */
@WebServlet("/event/eventApplyEnroll")
public class EventApplyEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventAppService es = new EventAppService();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/community/event/eventApplyEnroll.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String saveDirectory = getServletContext().getRealPath("/upload/community/event");
			System.out.println("saveDirectory = " + saveDirectory);
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			HomeSweetHomeFileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
			MultipartRequest multiReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			//사용자입력값 처리
			String eventapplycode = multiReq.getParameter("eventapplycode");
			String memberId = multiReq.getParameter("memberId");
			String nickName = multiReq.getParameter("nickName");
			String content = multiReq.getParameter("content");
			int eventNo = Integer.parseInt(multiReq.getParameter("eventNo"));
			
			EventAppExt event = new EventAppExt();
			event.setEventapplyCode(eventapplycode);
			event.setMemberId(memberId); 
			event.setNickName(nickName); 
			event.setContent(content);
			event.setEventNo(eventNo);
		
			
			File upFile1 = multiReq.getFile("upFile1");
			File upFile2 = multiReq.getFile("upFile2");
			

			if(upFile1 != null || upFile2 != null) {
				List<EventAppAtt> attachments = new ArrayList<>();
				if(upFile1 != null) 
					attachments.add(getAttachment(multiReq, "upFile1"));
				if(upFile2 != null) 
					attachments.add(getAttachment(multiReq, "upFile2"));
				event.setAttachments(attachments);
			}
			
			System.out.println("이벤트 참가작@ = " + event);
			
			int result = es.insertBoard(event);
			
			response.sendRedirect(request.getContextPath() + "/event/eventApplyView?no=" + event.getNo());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private EventAppAtt getAttachment(MultipartRequest multiReq, String name) {
		EventAppAtt attach = new EventAppAtt();
		String originalFilename = multiReq.getOriginalFileName(name);
		String renamedFilename = multiReq.getFilesystemName(name); 
		attach.setOriginalFilename(originalFilename);
		attach.setRenamedFilename(renamedFilename);
		return attach;
	
	}

}
