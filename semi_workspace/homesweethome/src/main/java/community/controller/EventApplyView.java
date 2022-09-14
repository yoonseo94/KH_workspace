package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.EventAppExt;
import community.model.service.EventAppService;


@WebServlet("/event/eventApplyView")
public class EventApplyView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventAppService es = new EventAppService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
			int no = Integer.parseInt(request.getParameter("no"));

			EventAppExt event = es.findByNo(no);
			event.setContent(event.getContent().replaceAll("\n", "<br/>"));
			System.out.println(event);
			
			// 3.view단 위임
			request.setAttribute("event", event);
			request.getRequestDispatcher("/WEB-INF/views/community/event/eventApplyView.jsp")
				.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



}
