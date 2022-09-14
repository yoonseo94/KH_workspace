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
import community.model.dto.QnaBoardExt;
import community.model.service.QnaBoardService;

/**
 * Servlet implementation class QnaBoardUpdateServlet
 */
@WebServlet("/qna/qnaBoardUpdate")
public class qnaBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaBoardService bs = new QnaBoardService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public qnaBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 수정 폼 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자입력값 처리
				int no = Integer.parseInt(request.getParameter("no"));
				
				// 2.업무로직
				QnaBoardExt board = bs.findByNo(no);
				
				// 3.view단처리
				request.setAttribute("board", board);
				request.getRequestDispatcher("/WEB-INF/views/community/qna/qnaBoardUpdate.jsp")
					.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. MultipartRequest객체 생성 - 파일저장완료
		String saveDirectory = getServletContext().getRealPath("/upload/community/qna");
		int maxPostSize = 1024 * 1024 * 10;
		String encoding = "utf-8";
		HomeSweetHomeFileRenamePolicy policy = new HomeSweetHomeFileRenamePolicy();
		MultipartRequest multiReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		// 3. 사용자입력값 처리 - HttpServletRequest가 아닌 MultipartRequest에서 값을 가져오기
		// update board set title = ?, content = ? where no = ?
		int no = Integer.parseInt(multiReq.getParameter("no"));
		String title = multiReq.getParameter("title");
		String memberId = multiReq.getParameter("memberId");
		String nickName = multiReq.getParameter("nickName");
		String content = multiReq.getParameter("content");
		String[] delFiles = multiReq.getParameterValues("delFile"); // 삭제하려는 첨부파일 pk
		
		QnaBoardExt board = new QnaBoardExt();
		board.setNo(no);
		board.setTitle(title);
		board.setMemberId(memberId);
		board.setNickName(nickName);
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
		
		// 4. 업무로직 - 
		// db board(update), attachment(insert) 레코드 등록
		int result = bs.updateBoard(board);
		// 첨부파일 삭제 처리
		if(delFiles != null) {
			for(String temp : delFiles) {
				int attachNo = Integer.parseInt(temp); // attachment pk
				Attachment attach = bs.findAttachmentByNo(attachNo);
				// a. 파일 삭제
				File delFile = new File(saveDirectory, attach.getRenamedFilename());
				if(delFile.exists()) delFile.delete();

				// b. db record 삭제
				result = bs.deleteAttachment(attachNo);
				System.out.println("> " + attachNo + "번 첨부파일 (" + attach.getRenamedFilename() + ") 삭제!");
			}
		}
		// 5. redirect
		response.sendRedirect(request.getContextPath() + "/qna/qnaBoardView?no=" + no);
		
	}

	private Attachment getAttachment(MultipartRequest multiReq, int boardNo, String name) {
		Attachment attach = new Attachment();
		attach.setBoardNo(boardNo);
		attach.setOriginalFilename(multiReq.getOriginalFileName(name));
		attach.setRenamedFilename(multiReq.getFilesystemName(name));
		return attach;
	}

}
