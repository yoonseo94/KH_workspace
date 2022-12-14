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

import community.model.dto.QnaNoticeExt;
import community.model.service.QnaNoticeService;

/**
 * Servlet implementation class qnaNoticeListServlet
 */
@WebServlet("/qna/qnaNoticeList")
public class qnaNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QnaNoticeService ns = new QnaNoticeService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
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

			// 2. 업무로직
			// 2.a content영역
			List<QnaNoticeExt> list = ns.findAll(param);
			System.out.println(list);



			// 3. view단 처리
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/community/qna/qnaBoardList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		}
	
	}


}
