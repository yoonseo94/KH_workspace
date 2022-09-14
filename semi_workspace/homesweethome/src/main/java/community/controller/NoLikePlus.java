/*
 * package community.controller;
 * 
 * import java.io.IOException; import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import community.model.dao.PictureDao; import community.model.dto.LikeDTO;
 * 
 *//**
	 * Servlet implementation class NoLikePlus
	 *//*
		 * @WebServlet(urlPatterns ="/picture/NoLike.do") public class NoLikePlus
		 * extends HttpServlet { private static final long serialVersionUID = 1L;
		 * 
		 * @Override
		 * 
		 * protected void doGet(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException { try{
		 * request.setCharacterEncoding("UTF-8"); LikeDTO likeDto = new LikeDTO();
		 * likeDto.setBoardNo(Integer.parseInt(request.getParameter("no")));
		 * likeDto.setMemberId(request.getParameter("member_id"));
		 * 
		 * PictureDao likedao = new PictureDao(); likedao.deleteLike(likeDto);
		 * 
		 * response.sendRedirect(request.getContextPath()+"/picture/pictureView?no=" +
		 * request.getParameter("no")); } catch(Exception e) { e.printStackTrace();
		 * response.sendError(500); } }
		 * 
		 * }
		 */