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
import community.model.dto.PictureAttachment;
import community.model.dto.PictureExt;
import community.model.service.PictureService;

/**
 * Servlet implementation class PictureEnroll
 */
@WebServlet("/picture/pictureEnroll")
public class PictureEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureService ps = new PictureService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/community/picture/pictureEnroll.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String saveDirectory = getServletContext().getRealPath("/upload/community/picture");
			System.out.println("saveDirectory = " + saveDirectory);

			int maxPostSize = 1024 * 1024 * 10;

			String encoding = "utf-8";
			FileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);

			String memberId = multiReq.getParameter("memberId");
			String nickName = multiReq.getParameter("nickName");
			String title = multiReq.getParameter("title");
	
			int categoryShape = Integer.parseInt(multiReq.getParameter("categoryShape"));
			String content = multiReq.getParameter("content");
			String coverPhoto = multiReq.getParameter("coverPhoto");

			PictureExt pic = new PictureExt();

			pic.setMemberId(memberId);
			pic.setNickName(nickName);
			pic.setTitle(title);

			pic.setCategoryShape(categoryShape);
			pic.setContent(content);
			pic.setCoverPhoto(coverPhoto);

			File upFile1 = multiReq.getFile("upFile1");
			File upFile2 = multiReq.getFile("upFile2");
			File upFile3 = multiReq.getFile("upFile3");

			if (upFile1 != null || upFile2 != null) {
				List<PictureAttachment> attachments = new ArrayList<>();
				if (upFile1 != null)
					attachments.add(getAttachment(multiReq, "upFile1"));
				if (upFile2 != null)
					attachments.add(getAttachment(multiReq, "upFile2"));
				if (upFile3 != null)
					attachments.add(getAttachment(multiReq, "upFile3"));
				pic.setAttachments(attachments);
			}

			System.out.println("picture = " + pic);

			int result = ps.insertBoard(pic);
		
			response.sendRedirect(request.getContextPath() + "/picture/pictureView?no=" + pic.getImgNo());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private PictureAttachment getAttachment(MultipartRequest multiReq, String name) {
		PictureAttachment attach = new PictureAttachment();
		String originalFilename = multiReq.getOriginalFileName(name); // 업로드한 파일명
		String renamedFilename = multiReq.getFilesystemName(name); // 저장된 파일명
		attach.setOriginalFilename(originalFilename);
		attach.setRenamedFilename(renamedFilename);
		return attach;
	}
}
