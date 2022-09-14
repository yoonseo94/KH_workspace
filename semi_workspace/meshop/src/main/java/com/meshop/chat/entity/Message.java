package com.meshop.chat.entity;

import java.sql.Date;

public class Message {
	private int no;
	private String senderId;
	private String receiverId;
	private int productId;
	private String message;
	private Date sendDate;
	
	public Message() {
		
	}
	
	
	public Message(int no, String senderId, String receiverId, int productId, String message, Date sendDate) {
		this.no = no;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.productId = productId;
		this.message = message;
		this.sendDate = sendDate;
	}

	@Override
	public String toString() {
		return "Message [no=" + no + ", senderId=" + senderId + ", receiverId=" + receiverId + ", productId="
				+ productId + ", message=" + message + ", sendDate=" + sendDate + "]";
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}
