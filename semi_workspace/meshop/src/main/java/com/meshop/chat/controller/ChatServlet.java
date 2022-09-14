package com.meshop.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meshop.chat.entity.Chatroom;
import com.meshop.chat.service.ChatServiceImpl;


@WebServlet(name = "chatServlet", urlPatterns = "/chat")
public class ChatServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	ChatServiceImpl chatService = new ChatServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 채팅 페이지로 이동
		request.getRequestDispatcher("/WEB-INF/views/chat/chat.jsp")
			.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		String sellerId = request.getParameter("sellerId");
		String buyerId = request.getParameter("buyerId");
		
		System.out.println("productId : " + productId);
		System.out.println("sellerId : " + sellerId);
		System.out.println("buyerId : " + buyerId);
		
		Chatroom c = new Chatroom();
		c.setProductId(productId);
		c.setSellerId(sellerId);
		c.setBuyerId(buyerId);
		
		boolean exist = chatService.existChatroom(c);
		if(exist) {
			//채팅방이 존재하면
		}else {
			System.out.println("false 입니다..");
			//채팅방이 존재하지 않는다면 생성
			int result = chatService.insertChatroom(c);
		}
		request.getRequestDispatcher("/WEB-INF/views/chat/chat.jsp")
		.forward(request, response);
	}
	
}
