package community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.model.dao.QnaNoticeDao;
import community.model.dto.LikeDTO;
import community.model.dto.QnaBoardExt;
import community.model.dto.QnaNoticeComment;
import community.model.dto.QnaNoticeExt;
import community.model.service.QnaNoticeService;
import member.model.dto.Member;

/**
 * Servlet implementation class qnaNoticeViewServlet
 */
@WebServlet("/qna/qnaNoticeView")
public class qnaNoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaNoticeService ns = new QnaNoticeService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
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
				int result = ns.updateReadCount(no);
				// 쿠키 새로 전송 (boardCookie 없는 경우 | 요청된 boardCookie에 현재 no가 없는 경우)
				Cookie cookie = new Cookie("boardCookie", boardCookieVal + "|" + no + "|");
				cookie.setPath(request.getContextPath() + "community/qna/qnaNoticeView"); // 쿠키를 사용할 경로
				cookie.setMaxAge(365 * 24 * 60 * 60); // 1년
				response.addCookie(cookie); // 응답헤더에 Set-Cookie로 전송
				System.out.println("> NoticeCookie가 새로 생성되었음.");
			}
			
			// 게시글 조회
			QnaNoticeExt board = ns.findByNo(no);

			
			// XSS공격대비(Cross-site Scripting공격) 변환
			board.setTitle(board.getTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			board.setContent(board.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			
			// board#content 개행처리
			board.setContent(board.getContent().replaceAll("\n", "<br/>"));
			
			System.out.println(board);

						
			
			// 3.view단 위임
			request.setAttribute("board", board);

			request.getRequestDispatcher("/WEB-INF/views/community/qna/qnaNoticeView.jsp")
				.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	


}
