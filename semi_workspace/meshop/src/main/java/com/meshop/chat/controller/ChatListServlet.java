package com.meshop.chat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.meshop.chat.entity.Message;
import com.meshop.chat.service.ChatServiceImpl;
@WebServlet(name = "chatListServlet", urlPatterns = "/chat/content")
public class ChatListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	ChatServiceImpl chatService = new ChatServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 값 처리
//		String senderId = request.getParameter("senderId");
//		String receiverId = request.getParameter("receiverId");
//		int productId = Integer.parseInt(request.getParameter("productId"));
		int chatroomId = Integer.parseInt(request.getParameter("chatroomId"));
		
		// 2. 업무로직
		List<Message> messageList = chatService.findChat(chatroomId);
		
		//채팅방 읽음 상태 변경
		int result = chatService.updateCheckStatus(chatroomId);
		
		// 3. 응답처리 json
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(new Gson().toJson(messageList));
	}
	
}
