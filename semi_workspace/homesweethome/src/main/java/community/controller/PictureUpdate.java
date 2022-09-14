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
import community.model.dto.PictureAttachment;
import community.model.dto.PictureExt;
import community.model.service.PictureService;

/**
 * Servlet implementation class PictureUpdate
 */
@WebServlet("/picture/pictureUpdate")
public class PictureUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureService ps = new PictureService();   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		PictureExt picture = ps.findByNo(no);
		
		request.setAttribute("picture", picture);
		request.getRequestDispatcher("/WEB-INF/views/community/picture/pictureUpdate.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = getServletContext().getRealPath("/upload/community/picture");
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
		String[] delFiles = multiReq.getParameterValues("delFile"); 
	
		PictureExt picture = new PictureExt();
		picture.setImgNo(no);
		picture.setTitle(title);
		picture.setMemberId(memberId);
		picture.setContent(content);
		picture.setNickName(nickName);
		
		File upFile1 = multiReq.getFile("upFile1");
		File upFile2 = multiReq.getFile("upFile2");
		File upFile3 = multiReq.getFile("upFile3");
		if(upFile1 != null || upFile2 != null || upFile3 != null) {
			List<PictureAttachment> attachments = new ArrayList<>();
			if(upFile1 != null)
				attachments.add(getAttachment(multiReq, no, "upFile1"));
			if(upFile2 != null)
				attachments.add(getAttachment(multiReq, no, "upFile2"));
			if(upFile3 != null)
				attachments.add(getAttachment(multiReq, no, "upFile3"));
			picture.setAttachments(attachments);
		}
		
		
		int result = ps.updateBoard(picture);
		
		// 첨부파일 삭제 처리
		if(delFiles != null) {
			for(String temp : delFiles) {
				int attachNo = Integer.parseInt(temp); // attachment pk
				PictureAttachment attach = ps.findAttachmentByNo(attachNo);

				File delFile = new File(saveDirectory, attach.getRenamedFilename());
				if(delFile.exists()) delFile.delete();

				result = ps.deleteAttachment(attachNo);
				System.out.println("> " + attachNo + "번 첨부파일 (" + attach.getRenamedFilename() + ") 삭제!");
			}
		}

		response.sendRedirect(request.getContextPath() + "/picture/pictureView?no=" + no);
		
		
	}

	private PictureAttachment getAttachment(MultipartRequest multiReq, int no, String name) {
		PictureAttachment attach = new PictureAttachment();
		attach.setImgNo(no);
		attach.setOriginalFilename(multiReq.getOriginalFileName(name));
		attach.setRenamedFilename(multiReq.getFilesystemName(name));
		return attach;
	}

}
