package purchase.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaywayOneInfo
 */
@WebServlet("/purchase/cartPurchaseOne")
public class PaywayOneInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 들어온 입력값 처리
			String memberId = request.getParameter("memberId");
			String productPrice = request.getParameter("productPrice");

			System.out.println("맴버아디:" + memberId);
			System.out.println("총가격:" + productPrice);
			// 2 업무로직
			
			// 3. view단 처리
			request.setAttribute("memberId", response);
			request.setAttribute("totalPrice", productPrice);
			request.getRequestDispatcher("/WEB-INF/views/purchase/purchaseView.jsp")
				.forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}



}
