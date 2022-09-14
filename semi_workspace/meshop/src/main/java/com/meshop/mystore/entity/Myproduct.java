package com.meshop.mystore.entity;

import java.util.List;

import com.meshop.product.entity.Attachment;

public class Myproduct {
	
	private Attachment attachment;	// 대표 이미지
	private String storeName; // 상품 제목 
	
	@Override
	public String toString() {
		return "Myproduct [attachment=" + attachment + ", storeName=" + storeName + "]";
	}
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	

}
