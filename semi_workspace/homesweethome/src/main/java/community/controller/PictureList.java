package community.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.HomeSweetHomeUtils;
import community.model.dto.PictureExt;
import community.model.service.PictureService;


/**
 * Servlet implementation class CommunityImgList
 */
@WebServlet("/community/picture")
public class PictureList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureService ps = new PictureService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		try {
			int numPerPage = 10;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
			}
			Map<String, Object> param = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			param.put("start", start);
			param.put("end", end);

			List<PictureExt> list = ps.findAll(param);
			System.out.println(list);
			for(PictureExt pics : list) {
				System.out.println("likeCount@servlet = " + pics.getLikeCount() );
				
			}
			int totalContents = ps.getTotalContents();
			String pagebar = HomeSweetHomeUtils.getPagebar(cPage, numPerPage, totalContents, request.getRequestURI());
			System.out.println(pagebar);
			String tab = request.getParameter(pagebar);
			
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/community/picture/pictureList.jsp").forward(request, response);
		
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
		
		
	}


}
