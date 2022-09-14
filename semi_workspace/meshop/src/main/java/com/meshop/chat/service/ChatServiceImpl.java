package com.meshop.chat.service;

import static com.meshop.common.JdbcTemplate.*;
import static com.meshop.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.meshop.chat.dao.ChatDAO;
import com.meshop.chat.entity.Chatroom;
import com.meshop.chat.entity.Message;

public class ChatServiceImpl {
	ChatDAO chatDAO = new ChatDAO();
	public List<Message> findChat(int chatroomId){
		Connection conn = getConnection();
		List<Message> list = null;
		
		try {
			list = chatDAO.findChat(conn, chatroomId);
		}catch(Exception e) {
			
		}finally {
			close(conn);
		}
		return list;
	}
	public List<Chatroom> findChatroomList(String memberId){
		Connection conn = getConnection();
		List<Chatroom> list = null;
		
		try {
			list = chatDAO.findAllChatroom(conn,memberId);
		}catch(Exception e) {
			
		}finally {
			close(conn);
		}
		return list;
	}
	public int insertChat(Message m) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = chatDAO.insertChat(conn, m);
		}catch(Exception e) {
			
		}finally {
			close(conn);
		}
		return result;
	}
	public int updateCheckStatus(int chatroomId) {
		Connection conn = getConnection();
		int result =0;
		try {
			result = chatDAO.updateCheckStatus(conn, chatroomId);
		}catch(Exception e) {
			
		}finally {
			close(conn);
		}
		return result;
	}
	public boolean existChatroom(Chatroom c) {
		Connection conn = getConnection();
        boolean exist = false;
        try {
        	exist = chatDAO.existChatroom(conn, c);
        	
        }catch(Exception e) {
			
		}finally {
			close(conn);
		}
        return exist;
	}
	public int insertChatroom(Chatroom c) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = chatDAO.insertChatroom(conn, c);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			// 오류 던져야함...
		}finally {
			close(conn);
		}
		return result;
	}
}
