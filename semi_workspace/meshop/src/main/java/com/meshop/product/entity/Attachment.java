package com.meshop.product.entity;

public class Attachment {
	private int no;			//첨부파일 번호
	private int productId;	// 상품 번호
	private String originalFilename;	
	private String renamedFilename;
	private AttachType attachtype;	//대표사진, 일반사진
	
	public Attachment(int no, int productId, String originalFilename, String renamedFilename, AttachType attachtype) {
		super();
		this.no = no;
		this.productId = productId;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.attachtype = attachtype;
	}
	
	public Attachment() {

	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public AttachType getAttachtype() {
		return attachtype;
	}
	public void setAttachtype(AttachType attachtype) {
		this.attachtype = attachtype;
	}
	
	
}
