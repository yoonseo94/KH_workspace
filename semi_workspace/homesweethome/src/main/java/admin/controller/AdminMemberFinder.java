package admin.controller;

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
import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberFinder
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				try {
					// page bar 관련 변수
					int numPerPage = MemberService.NUM_PER_PAGE; // 한 페이지당 출력되는 content의 수
					int cPage = 1;								 // 현재 페이지
					
					try {
						cPage = Integer.parseInt(request.getParameter("cPage"));
					} catch (NumberFormatException e) {
					}
					
					Map<String, Object> pageBarPoint = new HashMap<>();
					int start = (cPage - 1) * numPerPage + 1;
					int end = cPage * numPerPage;
					pageBarPoint.put("start", start);
					pageBarPoint.put("end", end);
					
					
					// 1. 사용자입력값처리
					String searchType = request.getParameter("searchType");  
					String searchKeyword = request.getParameter("searchKeyword");
					Map<String, String> searchParam = new HashMap<>();
					searchParam.put("searchType", searchType);
					searchParam.put("searchKeyword", searchKeyword);
					
					// 2. 업무로직
					List<Member> searchList = memberService.findBySomething(searchParam);
					
					int findContents = memberService.getFindContents(searchParam);
					String url = request.getRequestURI(); 
					String pagebar = HomeSweetHomeUtils.getPagebar(cPage, numPerPage, findContents, url);
					
					// 3. view단처리
					request.setAttribute("pagebar", pagebar);
					request.setAttribute("memberList", searchList);
					request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
						.forward(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
	}

}
