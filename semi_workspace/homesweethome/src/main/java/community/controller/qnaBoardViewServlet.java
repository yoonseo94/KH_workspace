package community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.QnaBoardComment;
import community.model.dto.QnaBoardExt;
import community.model.dto.QnaCommentLike;
import community.model.service.QnaBoardService;


/**
 * Servlet implementation class QnaBoardViewServlet
 */
@WebServlet("/qna/qnaBoardView")
public class qnaBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnaBoardService bs = new QnaBoardService();
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// 1.사용자입력값처리
			int no = Integer.parseInt(request.getParameter("no"));
			
			// 쿠키처리
			boolean hasRead = false;
			String boardCookieVal = "";
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {				
				for(Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if("boardCookie".equals(name)) {
						boardCookieVal = value; // 기존쿠키값
						if(value.contains("|" + no + "|")) {
							hasRead = true;
						}
						break;
					}
				}
			}
			
			
			// 2.업무로직
			
			// 조회수증가
			if(!hasRead) {
				int result = bs.updateReadCount(no);
				// 쿠키 새로 전송 (boardCookie 없는 경우 | 요청된 boardCookie에 현재 no가 없는 경우)
				Cookie cookie = new Cookie("boardCookie", boardCookieVal + "|" + no + "|");
				cookie.setPath(request.getContextPath() + "community/qna/qnaBoardView"); // 쿠키를 사용할 경로
				cookie.setMaxAge(365 * 24 * 60 * 60); // 1년
				response.addCookie(cookie); // 응답헤더에 Set-Cookie로 전송
				System.out.println("> boardCookie가 새로 생성되었음.");
			}
			
			// 게시글 조회
			QnaBoardExt board = bs.findByNo(no);
			
			// XSS공격대비(Cross-site Scripting공격) 변환
			board.setTitle(board.getTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			board.setContent(board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			
			// board#content 개행처리
			board.setContent(board.getContent().replaceAll("\n", "<br/>"));
			
			System.out.println(board);
			
			// 3.view단 위임
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/community/qna/qnaBoardView.jsp")
				.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberId = request.getParameter("memberId");
			int cono = Integer.parseInt(request.getParameter("cono"));
	
			List<QnaBoardComment> replylist = bs.selectCommentList(cono);
			QnaCommentLike cl =bs.selectLikeOne(cono, memberId);
			boolean exitistLike = cl == null ? false : true;
			if(exitistLike) {
				//like 삭제
				int result = bs.deleteLike(cl);
				System.out.println("insertLike="+result);
			}else {
				//like 추가
				QnaCommentLike like = new QnaCommentLike(memberId, cono, "T");
				int result = bs.insertLike(cono);
				System.out.println("insertLike="+result);
			}
			
			QnaCommentLike resultCl = bs.selectLikeOne(cono, memberId);
			boolean like = resultCl == null ? false : true;
		} catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}
	}
}