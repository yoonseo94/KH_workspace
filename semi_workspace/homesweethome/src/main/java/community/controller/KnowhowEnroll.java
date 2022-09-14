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
import community.model.service.KnowhowService;

/**
 * Servlet implementation class KnowhowEnroll
 */
@WebServlet("/knowhow/knowhowEnroll")
public class KnowhowEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KnowhowService ks = new KnowhowService();
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/community/knowhow/knowhowEnroll.jsp")
		.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String saveDirectory = getServletContext().getRealPath("/upload/community/knowhow");
			System.out.println("saveDirectory = " + saveDirectory);

			int maxPostSize = 1024 * 1024 * 10;
			
			String encoding = "utf-8";
			FileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
			MultipartRequest multiReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			String memberId = multiReq.getParameter("memberId");
			String nickName = multiReq.getParameter("nickName");
			int categoryNo= Integer.parseInt(multiReq.getParameter("categoryNo"));
			String content = multiReq.getParameter("content");
			String title = multiReq.getParameter("title");
			String coverPhoto  = multiReq.getParameter("coverPhoto");
			
			KnowhowExt kh = new KnowhowExt();
			kh.setMemberId(memberId);
			kh.setNickName(nickName);
			kh.setCategoryNo(categoryNo);
			kh.setContent(content);
			kh.setTitle(title);
			kh.setCoverPhoto(coverPhoto);

			File upFile1 = multiReq.getFile("upFile1");
			File upFile2 = multiReq.getFile("upFile2");

			if(upFile1 != null || upFile2 != null) {
				List<Attachment> attachments = new ArrayList<>();
				if(upFile1 != null) 
					attachments.add(getAttachment(multiReq, "upFile1"));
				if(upFile2 != null) 
					attachments.add(getAttachment(multiReq, "upFile2"));
				kh.setAttachments(attachments);
			}
			
			System.out.println("knowhow = " + kh);
			
			int result = ks.insertBoard(kh);
			
			response.sendRedirect(request.getContextPath() + "/knowhow/knowhowListView?no=" +kh.getNo());
			
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


