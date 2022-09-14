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
import community.model.dto.Event;
import community.model.dto.EventExt;

import community.model.service.EventService;

/**
 * Servlet implementation class eventListServlet
 */
@WebServlet("/community/eventList")
public class eventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService es = new EventService();


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


			List<EventExt> list = es.findAll(param);
			System.out.println(list);


			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/community/event/eventList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}

	}

}
