package com.meshop.report.entity;

import com.meshop.product.entity.Product;

public class Report {
	private int no;
	private String memberId; 	//신고자 아이디
	private int productId;		//게시글 번호
	private String content;		//신고 내용
	private String category;	//신고 유형
	private Product product;	//상품 정보
	private int count;			//신고 갯수
	public Report(int no, String memberId, int productId, String content, String category) {
		super();
		this.no = no;
		this.memberId = memberId;
		this.productId = productId;
		this.content = content;
		this.category = category;
	}
	public Report() {
		
	}
	
	@Override
	public String toString() {
		return "Report [no=" + no + ", memberId=" + memberId + ", productId=" + productId + ", content=" + content
				+ ", category=" + category + ", product=" + product + ", count=" + count + "]";
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
