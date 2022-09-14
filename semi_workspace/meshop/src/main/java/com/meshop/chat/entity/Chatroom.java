package com.meshop.chat.entity;

import java.sql.Date;

public class Chatroom {
	private int no;
	private String sellerId;
	private String buyerId;
	private int productId;
	private String title;
	private String storeName;
	private CheckStatus checkstatus;
	private Date createDate;
	
	
	@Override
	public String toString() {
		return "Chatroom [no=" + no + ", sellerId=" + sellerId + ", buyerId=" + buyerId + ", productId=" + productId
				+ ", title=" + title + ", storeName=" + storeName + ", checkstatus=" + checkstatus + ", createDate="
				+ createDate + "]";
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public CheckStatus getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(CheckStatus checkstatus) {
		this.checkstatus = checkstatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
