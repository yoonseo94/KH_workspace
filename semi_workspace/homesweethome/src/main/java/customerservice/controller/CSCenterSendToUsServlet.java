package customerservice.controller;

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
import customerservice.model.dto.CsEmailLogExt;
import customerservice.model.dto.CsEmailImage;
import customerservice.model.service.CustomerServiceService;

/**
 * Servlet implementation class CSCenterSendToUsServlet
 */
@WebServlet("/customerservice/sendtous")
public class CSCenterSendToUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerServiceService customerServiceService = new CustomerServiceService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String saveDirectory = getServletContext().getRealPath("/upload/customerservice");
		
			// c. 최대파일크기 10MB 
			int maxPostSize = 1024 * 1024 * 10;
			// d. 인코딩
			String encoding = "utf-8";
			HomeSweetHomeFileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
			MultipartRequest multiReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 3. 사용자입력값 처리
			// dto 객체 생성
			String name = multiReq.getParameter("name");
			String selectType =multiReq.getParameter("selectType");
			String email = multiReq.getParameter("email");
			String title = multiReq.getParameter("title");
			String content = multiReq.getParameter("contents");
			String File = multiReq.getParameter("name");
			
			CsEmailLogExt emailFormExt = new CsEmailLogExt();
			emailFormExt.setName(name);
			emailFormExt.setSelectType(selectType);
			emailFormExt.setEmail(email);
			emailFormExt.setTitle(title);
			emailFormExt.setContent(content);
			
			File file = multiReq.getFile("file");
	
			
			// 첨부한 파일이 하나라도 있는 경우
			if(file != null ) {
				CsEmailImage img = new CsEmailImage();
					img = getImg(multiReq, "file");
					emailFormExt.setEmailImage(img);
			}
			 
			// 4. 업무로직
			int result = customerServiceService.insertEmailLog(emailFormExt);
			
			// 5. redirect
			response.sendRedirect(request.getContextPath() + "/customerservice/cscenter");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
		private CsEmailImage getImg(MultipartRequest multiReq, String name) {
			CsEmailImage img = new CsEmailImage();
			String originalFilename = multiReq.getOriginalFileName(name); // 업로드한 파일명
			String renamedFilename = multiReq.getFilesystemName(name); // 저장된 파일명
			img.setOriginalFilename(originalFilename);
			img.setRenamedFilename(renamedFilename);
			return img;
		}
	}


