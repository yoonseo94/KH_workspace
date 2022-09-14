package purchase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaywayInfoServlet
 */
@WebServlet("/purchase/paywayInfo")
public class PaywayInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 들어온 입력값 처리
			String memberId = request.getParameter("memberId");
			String totalPrice = request.getParameter("totalPrice");

			System.out.println("맴버아디:" + memberId);
			System.out.println("총가격:" + totalPrice);
			// 2 업무로직
			
			// 3. view단 처리
			request.setAttribute("memberId", response);
			request.setAttribute("totalPrice", totalPrice);
			request.getRequestDispatcher("/WEB-INF/views/purchase/purchaseView.jsp")
				.forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}


}
