package com.kh.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * - 1개이상의 추상메소드를 가진 경우
 * - new연산자 호출을 방지할 목적 abstract class생성 가능 
 *
 */
public abstract class AbstractController {

	/**
	 * 
	 * @param request
	 * @param response
	 * @return viewName:String (forwarding할 jsp경로, redirect할 location) 
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 자식클래스에서 오버라이드에서 사용할 것.
	 * 오버라이드하지 않고 호출하면 MethodNotAllowedException 발생!
	 */
	public String doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		throw new MethodNotAllowedException("GET");
	}
	
	public String doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		throw new MethodNotAllowedException("POST");
	}
}
