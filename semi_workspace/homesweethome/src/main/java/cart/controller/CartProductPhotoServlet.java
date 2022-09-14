package cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cart.model.dto.Cart;
import cart.model.service.CartService;
import member.model.dto.Member;
import product.model.dto.ProductExt;
import product.model.service.ProductService;

/**
 * Servlet implementation class CartProductPhotoServlet
 */
@WebServlet("/cart/productPhoto")
public class CartProductPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProductService productService = new ProductService();
			HttpServletRequest httpReq = (HttpServletRequest) request; 
			HttpServletResponse httpRes = (HttpServletResponse) response; 
			
			// 로그인객체 아이디 정보 가져오기
			HttpSession session = httpReq.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			Member socialMember = (Member) session.getAttribute("socialMember");
			System.out.println("/member/cart@ loginMember " + loginMember);
			String memberId = loginMember.getMemberId();
			
			// 2. 업무로직
			List<Cart> cartList = cartService.findCartsByMemberId(memberId);
			System.out.println("cartList@ = " + cartList);
			List<ProductExt> productList = new ArrayList<>();
			for(Cart cart : cartList) {
				productList.add(productService.findProductByProductId(cart.getProductId())); // cartList로 찾아온 productId				
			}
			System.out.println("prouctList@ = " + productList);

			
			// 3. view단 처리
			response.setContentType("application/json; charset=utf-8"); // 
			new Gson().toJson(productList, response.getWriter());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
