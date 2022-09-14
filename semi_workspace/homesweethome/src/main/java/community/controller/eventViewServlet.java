package community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.dto.EventExt;
import community.model.service.EventService;

/**
 * Servlet implementation class eventViewServlet
 */
@WebServlet("/community/eventView")
public class eventViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventService es = new EventService();
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			EventExt event = es.findByNo(no);
			
			event.setEventContent(event.getEventContent().replaceAll("\n", "<br/>"));
			System.out.println(event);	
			
			request.setAttribute("event", event);
			request.getRequestDispatcher("/WEB-INF/views/community/event/eventView.jsp")
				.forward(request, response);
			
		}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
	}


}
