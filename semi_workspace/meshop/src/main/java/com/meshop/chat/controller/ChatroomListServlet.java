package com.meshop.chat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.meshop.chat.entity.Chatroom;
import com.meshop.chat.service.ChatServiceImpl;

@WebServlet(name="chatroomListServlet", urlPatterns = "/chat/chatlist")
public class ChatroomListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ChatServiceImpl chatService = new ChatServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. view단 입력 쳐리
		String memberId = request.getParameter("memberId");
		
		List<Chatroom> list = chatService.findChatroomList(memberId);
		
		// 3. 응답처리 json
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(new Gson().toJson(list));
	}
	
}
