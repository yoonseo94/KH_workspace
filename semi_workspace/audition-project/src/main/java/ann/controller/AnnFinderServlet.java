package ann.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ann.model.dto.Ann;
import ann.model.service.AnnService;
import common.HelloMvcUtils;

@WebServlet("/ann/annFinder")
public class AnnFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnService annService = new AnnService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String searchKeyword = request.getParameter("searchKeyword");
		
			List<Ann> list = annService.findByAnnTitle(searchKeyword);
			
			request.setAttribute("list", list);
			request.setAttribute("searchKeyword", searchKeyword);
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
