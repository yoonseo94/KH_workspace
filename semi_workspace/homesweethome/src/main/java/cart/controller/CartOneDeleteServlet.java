package cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cart.model.service.CartService;

/**
 * Servlet implementation class CartOneDeleteServlet
 */
@WebServlet("/cart/cartOneDelete")
public class CartOneDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 업무로직
		// 장바구니에 한가지 삭제한 후 결과 리턴
		String memberId = request.getParameter("memberId");
		String productId = request.getParameter("productId");
		
		System.out.println(memberId);
		System.out.println(productId);
		
		// 2. 응답처리 - json변환해서 출력
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonStr = gson.toJson("dasd"); // gson으로 만들고 전달할떄 toJson 
		System.out.println("jsonStr = " + jsonStr);
		response.getWriter().append(jsonStr);

		// 3. 
	}

}
