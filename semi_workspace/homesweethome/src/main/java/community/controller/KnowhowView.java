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

import community.model.dao.KnowhowDao;
import community.model.dto.Knowhow;
import community.model.dto.KnowhowComment;
import community.model.dto.KnowhowExt;
import community.model.dto.LikeDTO;
import community.model.service.KnowhowService;
import member.model.dto.Member;
import oracle.net.aso.l;

/**
 * Servlet implementation class KnowhowView
 */
@WebServlet("/knowhow/knowhowListView")
public class KnowhowView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KnowhowService ks = new KnowhowService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		try {
			int no = Integer.parseInt(request.getParameter("no"));

			boolean hasRead = false;
			String boardCookieVal = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if ("boardCookie".equals(name)) {
						boardCookieVal = value; // 기존쿠키값
						if (value.contains("|" + no + "|")) {
							hasRead = true;
						}
						break;
					}
				}
			}

			// 조회수증가
			if (!hasRead) {
				int result = ks.updateReadCount(no);
				Cookie cookie = new Cookie("boardCookie", boardCookieVal + "|" + no + "|");
				cookie.setPath(request.getContextPath() + "community/knowhow/knowhowListView"); // 쿠키를 사용할 경로
				cookie.setMaxAge(365 * 24 * 60 * 60); // 1년
				response.addCookie(cookie); // 응답헤더에 Set-Cookie로 전송
				System.out.println("> NoticeCookie가 새로 생성되었음.");
			}
			
			// 게시글 조회
			KnowhowExt knowhow = ks.findByNo(no);

			// board#content 개행처리
			knowhow.setContent(knowhow.getContent().replaceAll("\n", "<br/>"));

			HttpSession session = request.getSession();
			Member loginMemberId
			= (Member)session.getAttribute("loginMember");
			
			LikeDTO resultLD =ks.selectLikeOne(loginMemberId.getMemberId(),no);
			boolean like = resultLD == null ? false:true;
			System.out.println(knowhow);
			
		

			// 3.view단 위임		
			request.setAttribute("like", like);
			request.setAttribute("knowhow", knowhow);
			request.getRequestDispatcher("/WEB-INF/views/community/knowhow/knowhowView.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int no = Integer.parseInt(request.getParameter("no"));
		
		String memberId = request.getParameter("memberId");
		
		LikeDTO bl = ks.selectLikeOne(memberId,no);
		boolean existLike = bl == null ? false : true;
		if(existLike) {
			int result = ks.deleteLike(bl);
			System.out.println("insertLike(취소) = "+result);
		}else {
			LikeDTO like =new LikeDTO(memberId,no,"T");
			int result = ks.insertLike(like);
			System.out.println("insertLike(추가) = "+result);
		}
		
		LikeDTO resultLD = ks.selectLikeOne(memberId,no);
		boolean like = resultLD == null ? false:true;
		
	}		
}

