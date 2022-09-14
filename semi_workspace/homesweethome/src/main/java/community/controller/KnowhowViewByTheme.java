package community.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.KnowhowExt;
import community.model.service.KnowhowService;

/**
 * Servlet implementation class KnowhowViewByTheme
 */
@WebServlet("/knowhow/knowhowView")
public class KnowhowViewByTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KnowhowService ks = new KnowhowService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String catenumStr = request.getParameter("catenum");
		int catenum = 0;
		if(catenumStr != null) {
			catenum = Integer.parseInt(catenumStr);
		}
		
		final int PAGE_SIZE = 20;  //한페이지당 글 수 
		final int PAGE_BLOCK = 3;  //한화면에 나타날 페이지 링크 수
		int rCount = 0;  //총 글수
		int pageCount = 0;  //총페이지 수 
		int startPage = 1;  //화면에 나타날 시작페이지
		int endPage = 1;  //화면에 나타날 마지막페이지
		int currentPage =1;  //눌려진 페이지
		int startRnum = 1; //화면에 나타날 글 번호
		int endRnum = 1; //화면에 나타날 글 번호
		
		String pageNum = request.getParameter("pagenum");
		if(pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		rCount = ks.getProductCount(catenum);
		
		pageCount = (rCount/PAGE_SIZE) + (rCount%PAGE_SIZE == 0 ? 0 : 1);

		startRnum = (currentPage - 1) * PAGE_SIZE + 1;
		endRnum = startRnum + PAGE_SIZE -1;
		if(endRnum > rCount) {
			endRnum = rCount;
		}
		
		if(currentPage%PAGE_BLOCK == 0) {
			startPage = (currentPage/PAGE_BLOCK - 1) *PAGE_BLOCK + 1;
		}else {
			startPage = (currentPage/PAGE_BLOCK) *PAGE_BLOCK + 1;
		}
		
		endPage = startPage + PAGE_BLOCK - 1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		
		
		ArrayList<KnowhowExt> list =ks.productList(startRnum, endRnum, catenum);

		request.setAttribute("productList", list);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("catenum", catenum);

		request.getRequestDispatcher("/WEB-INF/views/community/knowhow/knowhowViewbyTheme.jsp").forward(request, response);
	}

}
