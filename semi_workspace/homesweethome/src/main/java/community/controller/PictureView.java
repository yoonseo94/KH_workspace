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

import community.model.dto.LikeDTO;
import community.model.dto.PictureExt;
import community.model.dto.QnaBoardExt;
import community.model.service.PictureService;
import member.model.dto.Member;

/**
 * Servlet implementation class PictureView
 */
@WebServlet("/picture/pictureView")
public class PictureView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureService ps = new PictureService();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
						boardCookieVal = value;
						if (value.contains("|" + no + "|")) {
							hasRead = true;
						}
						break;
					}
				}
			}

			if (!hasRead) {
				int result = ps.updateReadCount(no);
				Cookie cookie = new Cookie("boardCookie", boardCookieVal + "|" + no + "|");
				cookie.setPath(request.getContextPath() + "community/picture/pictureView"); 
				cookie.setMaxAge(365 * 24 * 60 * 60); // 1년
				response.addCookie(cookie); 
				System.out.println("> NoticeCookie가 새로 생성되었음.");
			}

			PictureExt picture = ps.findByNo(no);
			
			
			HttpSession session = request.getSession();

			
			picture.setContent(picture.getContent().replaceAll("\n", "<br/>"));

			System.out.println(picture);


			/*******************/
		
			
			Member loginMember
			= (Member)session.getAttribute("loginMember");
	
			LikeDTO resultLD =ps.selectLikeOne(loginMember.getMemberId(),no);
			boolean like = resultLD == null ? false:true;
			
			/* int cnt = ps.like_count(no); */
		
			
			request.setAttribute("picture", picture);
			request.setAttribute("like", like);

			request.getRequestDispatcher("/WEB-INF/views/community/picture/pictureView.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String memberId = request.getParameter("memberId");
		
		LikeDTO bl = ps.selectLikeOne(memberId,no);
		boolean existLike = bl == null ? false : true;
		if(existLike) {
			int result = ps.deleteLike(bl);
			System.out.println("insertLike(취소) = "+result);
		}else {
			LikeDTO like =new LikeDTO(memberId,no,"T");
			int result = ps.insertLike(like);
			System.out.println("insertLike(추가) = "+result);
		}
		
		LikeDTO resultLD = ps.selectLikeOne(memberId,no);
		boolean like = resultLD == null ? false:true;
		
	
	}

}
