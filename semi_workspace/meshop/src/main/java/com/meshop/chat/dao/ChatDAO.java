package com.meshop.chat.dao;

import static com.meshop.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.meshop.chat.entity.Chatroom;
import com.meshop.chat.entity.CheckStatus;
import com.meshop.chat.entity.Message;


public class ChatDAO {
    private Properties properties = new Properties();
	public ChatDAO() {
		//생성됨과 동시에 쿼리 설정 파일 가져오기
		String filename = ChatDAO.class.getResource("/sql/chat-query.properties").getPath();
        try{
            properties.load(new FileReader(filename));

        }catch (IOException e){
            String message = e.getMessage();
        	System.out.println(message);
        }
        System.out.println("filename = " + filename);
	}
	
	public List<Message> findChat(Connection conn, int chatroomId) {
		//준비
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> messageList = new ArrayList<>();
        
        // SQL
        // select * from chat where chatroom_id = ? order by send_date asc
        String sql = properties.getProperty("findChat");
        
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, chatroomId);
        	
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
        		Message m = new Message();
        		m.setNo(rs.getInt("no"));
        		m.setSenderId(rs.getString("sender_id"));
        		m.setReceiverId(rs.getString("receiver_id"));
        		m.setMessage(rs.getString("message"));
        		m.setSendDate(rs.getDate("send_date"));
        		
        		messageList.add(m);
        	}
        }catch(SQLException e) {
        	e.printStackTrace();
        }finally {
        	close(rs);
        	close(pstmt);
        }
		return messageList;
	}
	public List<Chatroom> findAllChatroom(Connection conn, String memberId) {
		// 회원 아이디에 맞는 채팅방 목록 가져오기
		//준비
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Chatroom> list = new ArrayList<>();
        
        // SQL
        //select * from v1 where (seller_id = ? or buyer_id = ?) and member_id != ?
        String sql = properties.getProperty("findAllChat");
        
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, memberId);
        	pstmt.setString(2, memberId);
        	pstmt.setString(3, memberId);
        	
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
        		Chatroom c = new Chatroom();
		
        		c.setNo(rs.getInt("no"));
        		c.setSellerId(rs.getString("seller_id"));
        		c.setBuyerId(rs.getString("buyer_id"));
        		c.setProductId(rs.getInt("product_id"));
        		c.setTitle(rs.getString("title"));
        		c.setStoreName(rs.getString("store_name"));
        		if(rs.getString("check_status").equals("C")) {
        			c.setCheckstatus(CheckStatus.C);
        		}else {
        			c.setCheckstatus(CheckStatus.N);
        		}
        		c.setCreateDate(rs.getDate("create_date"));
        		
        		System.out.println(c);
        		list.add(c);
        	}
        }catch(SQLException e) {
        	e.printStackTrace();
        }finally {
        	close(rs);
        	close(pstmt);
        }
		return list;
	}
	public int insertChat(Connection conn, Message message) {
		//준비
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = properties.getProperty("insertChat");
        System.out.println(sql);
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, message.getProductId());
        	pstmt.setString(2, message.getSenderId());
        	pstmt.setString(3, message.getReceiverId());
        	pstmt.setString(4,  message.getMessage());
        	
        	result = pstmt.executeUpdate();
        }catch(SQLException e) {
        	e.printStackTrace();
        	//에러 던지기!
        }finally {
        	close(pstmt);
        }
		
        return result;
	}
	public int updateCheckStatus(Connection conn, int chatroomId) {
		//준비
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = properties.getProperty("updateCheckStatus");
        System.out.println(sql);
        
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, chatroomId);
        	
        	result = pstmt.executeUpdate();
        }catch(SQLException e) {
        	e.printStackTrace();
        	//에러 던지기!
        }finally {
        	close(pstmt);
        }
        
        return result;
	}
	public boolean existChatroom(Connection conn, Chatroom c) {
		//준비
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        boolean exist = false;
        //select count(*) as exist from chatroom where (seller_id=? and buyer_id=?) and product_id=?
        String sql = properties.getProperty("existChatroom");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getSellerId());
			pstmt.setString(2, c.getBuyerId());
			pstmt.setInt(3, c.getProductId());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int count = rs.getInt("exist");
				
				if(count == 0) {
					//채팅방이 존재하지 않음.
					exist = false;
				}else {
					// 채팅방이 존재함.
					exist = true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return exist;
	}
	public int insertChatroom(Connection conn, Chatroom c) {
		//준비
        PreparedStatement pstmt = null;
        int result = 0;
        // insert into chatroom values(seq_chat_no.nextval, ?, ?, ?, default, default)
        String sql = properties.getProperty("insertChatroom");
        System.out.println(sql);
        
        try {
        	pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getSellerId());
			pstmt.setString(2, c.getBuyerId());
			pstmt.setInt(3, c.getProductId());
			
			result = pstmt.executeUpdate();
			
        }catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
		}
        return result;
	}
}
