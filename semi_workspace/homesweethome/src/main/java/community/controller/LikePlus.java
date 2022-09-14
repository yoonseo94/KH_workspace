/*
 * package community.controller;
 * 
 * import java.io.IOException; import java.sql.Ref;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import community.model.dao.PictureDao; import community.model.dto.LikeDTO;
 * import community.model.service.PictureService;
 * 
 *//**
	 * Servlet implementation class LikePlus
	 *//*
		 * @WebServlet(urlPatterns ="/picture/Like.do")
		 * 
		 * @WebServlet("/picture/like") public class LikePlus extends HttpServlet {
		 * private static final long serialVersionUID = 1L; private PictureService ps =
		 * new PictureService();
		 * 
		 * @Override protected void doGet(HttpServletRequest request,
		 * HttpServletResponse response) throws ServletException, IOException {
		 * 
		 * try { request.setCharacterEncoding("UTF-8"); LikeDTO likedto = new LikeDTO();
		 * 
		 * likedto.setBoardNo(Integer.parseInt(request.getParameter("no")));
		 * likedto.setMemberId(request.getParameter("member_id"));
		 * 
		 * PictureDao likeDao = new PictureDao(); likeDao.insertLike(likedto);
		 * 
		 * response.sendRedirect(request.getContextPath()+"/picture/pictureView?no=" +
		 * request.getParameter("no")); } catch(Exception e) { e.printStackTrace();
		 * response.sendError(500); }
		 * 
		 * 
		 * try { int no = Integer.parseInt(request.getParameter("no"));
		 * 
		 * String memberId = request.getParameter("memberId");
		 * 
		 * LikeDTO like = ps.likeCheck(memberId,no);
		 * 
		 * if(like == null) { ps.updateLikeCount(memberId,no); } else {
		 * ps.setPostingLike(like); }
		 * 
		 * request.setAttribute("like", like);
		 * response.sendRedirect(request.getContextPath() + "/picture/pictureView?no=" +
		 * no); } catch (Exception e) { e.printStackTrace(); throw e; } }
		 * 
		 * }
		 */