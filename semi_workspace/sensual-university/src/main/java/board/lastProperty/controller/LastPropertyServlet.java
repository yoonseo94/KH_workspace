package board.lastProperty.controller;

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

import board.model.dto.BoardAttachment;
import board.model.dto.BoardExt;
import board.model.service.LastPropertyService;
import common.BoardFileRenamePolicy;

/**
 * Servlet implementation class LastPropertyEnrollServlet
 */
@WebServlet("/board/lastPropertyEnroll")
public class LastPropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LastPropertyService lastPropertyService = new LastPropertyService(); 
 
	/**
	 * 게시판 등록 요청
	 * - 게시판 등록시 /board/boardList
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/lastProperty/lastPropertyEnroll.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			/*
			 * 첨부파일 등록
			 * 
			 */
			// MultipartRequest객체 생성
			// 파일저장경로
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			System.out.println("saveDirectory = " + saveDirectory);
			
			// 최대파일크기 10MB
			int maxPostSize = 1024 * 1024 * 10;
			
			// 인코딩
			String encoding = "utf-8";
			
			// 파일명 재지정 정책 객체
			// DefaultFileRenamePolicy 파일명 중복시 numbering처리함.
			FileRenamePolicy policy = new BoardFileRenamePolicy();
			MultipartRequest multiReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 사용자 입력값 처리
			// dto 객체 생성
			String writer = multiReq.getParameter("writer");
			String title = multiReq.getParameter("title");
			String content = multiReq.getParameter("content");
			
			BoardExt board = new BoardExt();
			board.setMemberId(writer);
			board.setBoardTitle(title);
			board.setBoardContent(content);
			System.out.println("board = " + board);
			
			File upFile1 = multiReq.getFile("upFile1");
			File upFile2 = multiReq.getFile("upFile2");
			
			// 첨부한 파일이 하나라도 있는 경우
			if(upFile1 != null || upFile2 != null) {
				List<BoardAttachment> boardAttachments = new ArrayList<>();
				if(upFile1 != null) 
					boardAttachments.add(getBoardAttachment(multiReq, "upFile1"));
				if(upFile2 != null) 
					boardAttachments.add(getBoardAttachment(multiReq, "upFile2"));
				
				board.setBoardAttachments(boardAttachments);
			}
			
			// 업무로직
			int result = lastPropertyService.insertBoard(board);
			
			// 리다이렉트
			response.sendRedirect(request.getContextPath() + "/board/lastPropertyList");
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

		private BoardAttachment getBoardAttachment(MultipartRequest multiReq, String name) {
			BoardAttachment noticeAttach = new BoardAttachment();
			String originalFilename = multiReq.getOriginalFileName(name); // 업로드한 파일
			String renameFilename = multiReq.getFilesystemName(name); // 저장된 파일명
			noticeAttach.setOriginalFilename(originalFilename);
			noticeAttach.setRenameFilename(renameFilename);
			return noticeAttach;
		}
}