package mainPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageBar;
import notice.model.dto.NoticeExt;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet("/main/mainPage")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoticeService noticeService = new NoticeService();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			int numPerPage = 7;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {}
			
			Map<String, Object> param = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			param.put("start", start);
			param.put("end", end);
			
			List<NoticeExt> list = noticeService.findAll(param);
			System.out.println(list);
			// 2.b pagebar영역
			int totalContents = noticeService.getTotalContents();
			String url = request.getRequestURI();
			String pagebar = PageBar.getPagebar(cPage, numPerPage, totalContents, url);
			System.out.println("mainPagebar = " + pagebar);
	
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			
			request.getRequestDispatcher("/WEB-INF/views/main/mainPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
