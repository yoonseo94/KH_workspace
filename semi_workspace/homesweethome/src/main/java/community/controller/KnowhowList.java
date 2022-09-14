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
import community.model.dto.KnowhowExt;
import community.model.service.KnowhowService;

@WebServlet("/community/knowhow")
public class KnowhowList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KnowhowService ks = new KnowhowService();

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

			List<KnowhowExt> list = ks.findAll(param);
			System.out.println(list);


			int totalContents = ks.getTotalContents();
			String pagebar = HomeSweetHomeUtils.getPagebar(cPage, numPerPage, totalContents, request.getRequestURI());
			System.out.println(pagebar);
			String tab = request.getParameter(pagebar);
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/community/knowhow/knowhowList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
	}

}
