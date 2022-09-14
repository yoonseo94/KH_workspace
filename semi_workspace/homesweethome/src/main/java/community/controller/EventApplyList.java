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

import community.model.dto.EventAppExt;
import community.model.service.EventAppService;

/**
 * Servlet implementation class eventApplyList
 */
@WebServlet("/event/eventApplyList")
public class EventApplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventAppService es = new EventAppService();

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

			List<EventAppExt> list = es.findAll(param);
			System.out.println(list);

			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/community/event/eventApplyList.jsp").forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
