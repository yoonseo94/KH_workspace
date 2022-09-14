package com.meshop.chat.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.meshop.chat.entity.Message;
import com.meshop.chat.service.ChatServiceImpl;
import com.meshop.chat.ws.MeshopWebSocket;
@WebServlet(name = "chatInsertServlet", urlPatterns = "/chat/add")
public class ChatInsertServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	ChatServiceImpl chatService = new ChatServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 값 처리
		String dm = request.getParameter("dm");
        Map<String, Object> dmMap = new Gson().fromJson(dm, Map.class);
        System.out.println(dmMap.toString());
        String senderId = (String) dmMap.get("senderId");
        String receiverId = (String) dmMap.get("receiverId");
        int chatroomId = Integer.parseInt((String) dmMap.get("chatroomId"));
        String chat = (String) dmMap.get("chat");
        
        Session sess = MeshopWebSocket.clientsMap.get(receiverId);
        
        if(sess != null) {
            // 특정 사용자에게 메시지 전송
            Basic basic = sess.getBasicRemote();
            basic.sendText(dm);
        }
//		String senderId = request.getParameter("senderId");
//		String receiverId = request.getParameter("receiverId");
//		int chatroomId = Integer.parseInt(request.getParameter("chatroomId"));
//		String chat = request.getParameter("chat");
//		System.out.println(senderId + receiverId + chatroomId);
		
		Message m = new Message();
		m.setSenderId(senderId);
		m.setReceiverId(receiverId);
		m.setProductId(chatroomId);
		m.setMessage(chat);
		
		// 2. 업무로직
		int result = chatService.insertChat(m);
		
		// 3. 응답처리 json
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(new Gson().toJson(result));
	}
}
