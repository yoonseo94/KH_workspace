package cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.model.service.CartService;

/**
 * Servlet implementation class CartDeleteChoiceServlet
 */
@WebServlet("/cart/cartDelete")
public class CartDeleteChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 들어온 입력값 처리
			String memberId = request.getParameter("memberId");
			String[] productIdArr = request.getParameterValues("productId");

			System.out.println("맴버아디:" + memberId);
			System.out.println("제품아디:" + productIdArr);
			// 2 업무로직
			for (int i = 0; i < productIdArr.length; i++) {
				System.out.println(productIdArr[i]);
				cartService.deleteCartByProductId(memberId, productIdArr[i]);
			}
			// 3. 돌아가기
			// response.sendRedirect(request.getContextPath() + "/member/cart");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
